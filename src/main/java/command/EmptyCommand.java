package command;

import javax.servlet.http.HttpServletRequest;


public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/show.jsp";
        return page;
    }
}
