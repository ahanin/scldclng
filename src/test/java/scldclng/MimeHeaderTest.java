/**
 * Copyright
 */
package scldclng;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MimeHeaderTest {

    @Test
    public void testShouldParseSimpleHeader() throws Exception {
        final MimeHeader header = new MimeHeader("Content-Type: text/plain");
        assertThat(header.getName(), equalTo("Content-Type"));
        assertThat(header.getType(), equalTo("text/plain"));
    }

    @Test
    public void testShouldParseParameters() throws Exception {
        final MimeHeader header = new MimeHeader("Content-Type: multipart/form-data; bOuNdAry=digest");
        assertThat(header.getName(), equalTo("Content-Type"));
        assertThat(header.getType(), equalTo("multipart/form-data"));
        assertThat(header.getParameter("boundary"), equalTo("digest"));
    }

}
