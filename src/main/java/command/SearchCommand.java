package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Deleting attachments from session and temporary files
        session.removeAttribute("temp_path_avatar");
        session.removeAttribute("attaches");
        return "/jsp/search.jsp";
    }
}
