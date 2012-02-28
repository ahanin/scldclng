/**
 * Copyright
 */
package scldclng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ViewServlet extends HttpServlet {

    private static final long serialVersionUID = -4826523783668881913L;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        final PrintWriter writer = resp.getWriter();
        writer.println("Sorry, not hosting files this time...");
        writer.close();
    }
}
