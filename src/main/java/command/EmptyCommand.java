package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhenya on 30.11.15.
 */
public class EmptyCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/menu.jsp";
        return page;
    }
}
