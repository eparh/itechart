package command;

import command.exception.CommandException;
import command.util.TemplateContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.GeneralUtil;
import persistence.model.Contact;
import persistence.model.SearchCriteria;
import persistence.model.ViewSettings;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    private HttpServletRequest request;
    private ViewSettings settings;
    private SearchCriteria criteria;

    private Logger logger = LogManager.getLogger(ShowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        HttpSession session = request.getSession();

        //Deleting attachments from session
        session.removeAttribute("attaches");

        //For paging
        String mode = request.getParameter("mode");
        long total;
        if(mode != null) {
            criteria =  getSearchCriteria(mode);
            total = contactService.countContacts(criteria);
            settings =  getViewSettings(mode,total);
        } else {
            criteria = new SearchCriteria();
            total = contactService.countContacts(criteria);
            settings = new ViewSettings();
            settings.countPages(total);
        }
        List<Contact> contacts= contactService.getShowContacts(criteria,settings);
        session.setAttribute("criteria",criteria);
        session.setAttribute("settings",settings);
        request.setAttribute("contacts", contacts);
        addEmailTemplates(session);
        logger.info("Contacts was shown");
        return "/jsp/show.jsp";
    }

    private void addEmailTemplates(HttpSession session) {
        session.setAttribute("templates", TemplateContainer.getInstance().getTemplates());
    }

    private ViewSettings getViewSettings(String mode,long total) {
        ViewSettings settings = new ViewSettings();
        HttpSession session = request.getSession();
        String requestCount = request.getParameter("countRecords");

        //Check for user's action on page
        if (requestCount == null) {
            ViewSettings sessSettings = (ViewSettings) session.getAttribute("settings");
            //If no action, then check for coming from another page of site
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
                        settings.setStandardView();
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
                        throw new CommandException("Invalid contact's mode: " + mode);
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
            criteria.setBirthdayFrom(GeneralUtil.stringToDate(request.getParameter("s_birthdayFrom")));
            criteria.setBirthdayTo(GeneralUtil.stringToDate(request.getParameter("s_birthdayTO")));
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
