package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhenya on 24.10.15.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
