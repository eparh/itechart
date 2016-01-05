package command;



import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.sun.tools.javac.file.SymbolArchive;
import persistence.model.Contact;
import persistence.model.SearchCriteria;
import persistence.model.ViewSettings;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    private HttpServletRequest request;
    private ViewSettings settings;
    private SearchCriteria criteria = new SearchCriteria();

    @Override
    public String execute(HttpServletRequest request) {
        this.request = request;
        HttpSession session = request.getSession();
        String mode = request.getParameter("mode");

        long total = contactService.countContacts(criteria);

        if(mode != null) {
            criteria =  getSearchCriteria(mode);
            settings =  getViewSettings(mode,total);
        } else {
            criteria = new SearchCriteria();
            settings = new ViewSettings();
            settings.countPages(total);
        }


        List<Contact> contacts= contactService.getShowContacts(criteria,settings);
        session.setAttribute("criteria",criteria);
        session.setAttribute("settings",settings);
        request.setAttribute("contacts", contacts);

        return "/jsp/show.jsp";
    }

    private ViewSettings getViewSettings(String mode,long total) {
        ViewSettings settings = new ViewSettings();
        HttpSession session = request.getSession();
        String requestCount = request.getParameter("countRecords");
        System.out.println("mode:"+ mode);


        //Смотрим, проводил ли пользователь действия на текущей странице
        if (requestCount == null) {
            ViewSettings sessSettings = (ViewSettings) session.getAttribute("settings");
            //Если нет, то проверяем не пришел ли пользователь с другой страницы сайта.
            if (sessSettings != null) {
                settings = sessSettings;
                settings.countPages(total);
                long pages = settings.getPages();
                switch (mode) {
                    case "edit":
                        settings.setPageNumber(sessSettings.getPageNumber());
                        break;
                    case "add":
                        settings.setPageNumber(pages);
                        break;
                    case  "search":
                        settings.setStandartView();
                        break;
                    case "delete":
                        long currPage = settings.getPageNumber();
                        if(currPage < pages){
                            settings.setPageNumber(currPage);
                        } else {
                            settings.setPageNumber(pages);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid mode: " + mode);
                }
            }
        } else {
            settings.setCount(Long.parseLong(requestCount));
            settings.setPageNumber(Long.parseLong(request.getParameter("pageNumber")));
            settings.countPages(total);
        }
        return settings;
    }

    private SearchCriteria getSearchCriteria(String mode) {
        SearchCriteria criteria = new SearchCriteria();
        HttpSession session = request.getSession();
        if( mode.equals("search")) {
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
        } else {
            criteria = (SearchCriteria) session.getAttribute("criteria");
        }
        return  criteria;
    }
}
