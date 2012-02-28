/**
 * Copyright
 */
package scldclng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostServlet extends HttpServlet {

    private static final long serialVersionUID = 2832468466575613676L;

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("message") == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            req.getRequestDispatcher("/META-INF/post-review.jsp").forward(req, resp);
        }
    }

}
