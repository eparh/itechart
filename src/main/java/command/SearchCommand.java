package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchCommand implements ActionCommand {
    private Logger logger = LogManager.getLogger(SearchCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Deleting attachments from session
        session.removeAttribute("attaches");
        logger.info("Moving on search page");
        return "/jsp/search.jsp";
    }
}
