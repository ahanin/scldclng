/**
 * Copyright
 */
package scldclng;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UploadServletTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private UploadServlet uploadServlet;

    @Before
    public void setUp() throws Exception {
        uploadServlet = new UploadServlet();
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
    }

    @Test
    public void testGetShouldRejectWithNoUploadId() throws Exception {
        when(req.getPathInfo()).thenReturn("/");

        uploadServlet.doGet(req, resp);

        verify(resp).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void testPostShouldRejectWithNoUploadId() throws Exception {
        when(req.getPathInfo()).thenReturn("/");

        uploadServlet.doPost(req, resp);

        verify(resp).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void testGetShouldRejectWithInvalidUploadId() throws Exception {
        when(req.getPathInfo()).thenReturn("/john/does/blues");

        uploadServlet.doGet(req, resp);

        verify(resp).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void testPostShouldRejectWithInvalidUploadId() throws Exception {
        when(req.getPathInfo()).thenReturn("/john/does/blues");

        uploadServlet.doPost(req, resp);

        verify(resp).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void testShouldRespond404OnUnknownUploadId() throws Exception {
        when(req.getPathInfo()).thenReturn("/19268176912034829346928");

        uploadServlet.doGet(req, resp);

        verify(resp).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void testPostShouldRejectNonMultipartRequests() throws Exception {
        when(req.getPathInfo()).thenReturn("/19268176912034829346928");

        uploadServlet.doGet(req, resp);

        verify(resp).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void testPostShouldRedirectToUploadCompleteWhenFinished() throws Exception {
        when(req.getPathInfo()).thenReturn("/19268176912034829346928");
        when(req.getHeader("Content-Length")).thenReturn("10403");
        when(req.getContentType()).thenReturn("multipart/form-data");
        when(req.getInputStream()).thenReturn(new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return -1; // empty one
            }
        });

        final RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/META-INF/upload-complete.jsp")).thenReturn(requestDispatcher);

        uploadServlet.doPost(req, resp);

        verify(requestDispatcher).forward(req, resp);
    }

}