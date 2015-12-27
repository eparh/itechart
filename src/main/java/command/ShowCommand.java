package command;



import persistence.model.Contact;
import persistence.model.SearchCriteria;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    private HttpServletRequest request;

    @Override
    public String execute(HttpServletRequest request) {
        this.request = request;
        HttpSession session = request.getSession();
        SearchCriteria criteria = getSearchCriteria();

        List<Contact> contacts= contactService.getShowContacts(criteria);
        long total = contactService.countContacts(criteria);

        session.setAttribute("countRecords", criteria.getCount());
        session.setAttribute("pageNumber", criteria.getPageNumber());
        session.setAttribute("pages", criteria.getPages(total));
        request.setAttribute("contacts", contacts);
        return "/jsp/main.jsp";
    }

    private SearchCriteria getSearchCriteria() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setName(request.getParameter("s_name"));
        criteria.setSurname(request.getParameter("s_surname"));
        criteria.setMidName(request.getParameter("s_middname"));
        criteria.setGender(request.getParameter("s_gender"));
        criteria.setNationality(request.getParameter("s_national"));
        criteria.setBirthday_from(contactService.stringToDate(request.getParameter("s_birthdayFrom")));
        criteria.setBirthday_to(contactService.stringToDate(request.getParameter("s_birthdayTO")));
        criteria.setMaritStatus(request.getParameter("s_maritStatus"));
        criteria.setCountry(request.getParameter("s_country"));
        criteria.setCity(request.getParameter("s_city"));
        criteria.setAddress(request.getParameter("s_address"));
        criteria.setIndex(request.getParameter("s_index"));

        HttpSession session = request.getSession();
        String requestCount = request.getParameter("countRecords");

        //Смотрим, проводил ли пользователь действия на текущей странице
        if (requestCount == null) {
            Long sessionCount = (Long) session.getAttribute("countRecords");
            //Если нет, то проверяем не пришел ли пользователь с другой страницы сайта.
            if (sessionCount != null) {
                criteria.setCount(sessionCount);
                if (request.getParameter("mode") == null) {
                    criteria.setPageNumber((Long) session.getAttribute("pageNumber"));
                } else {

                }
            }
        } else {
            criteria.setCount(Long.parseLong(requestCount));
            criteria.setPageNumber(Long.parseLong(request.getParameter("pageNumber")));
        }

        //Чтобы при paging-e, редактировании и добавлении контакта
        // не сбивались параметры поиска, их кидаю в атрибуты и храню как hidden-параметры формы на главной странице
        session.setAttribute("s_name",criteria.getName());
        session.setAttribute("s_surname",criteria.getSurname());
        session.setAttribute("s_midname",criteria.getMidName());
        session.setAttribute("s_gender",criteria.getGender());
        session.setAttribute("s_national",criteria.getNationality());
        session.setAttribute("s_maritStatus",criteria.getMaritStatus());
        session.setAttribute("s_country",criteria.getCountry());
        session.setAttribute("s_city",criteria.getCity());
        session.setAttribute("s_address",criteria.getAddress());
        session.setAttribute("s_index",criteria.getIndex());
        session.setAttribute("s_birthdayFrom",criteria.getBirthday_from());
        session.setAttribute("s_birthdayTO",criteria.getBirthday_to());
        return criteria;
    }
}
