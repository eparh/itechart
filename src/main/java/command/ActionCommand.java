package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionCommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
