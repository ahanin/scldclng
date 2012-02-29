/**
 * Copyright
 */
package scldclng;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MultipartTest {

    @Test
    public void testParse() throws Exception {
        final byte[] payload = (
                "--frontier\n" +
                        "\r\n" +
                        "Content-Type: text/plain\r\n" +
                        "\n" +
                        "This is the body of the message.\n\r" +
                        "--frontier\n" +
                        "Content-Type: application/octet-stream\n" +
                        "Content-Transfer-Encoding: base64\r" +
                        "\n" +
                        "PGh0bWw+CiAgPGhlYWQ+CiAgPC9oZWFkPgogIDxib2R5PgogICAgPHA+VGhpcyBpcyB0aGUg\n" +
                        "Ym9keSBvZiB0aGUgbWVzc2FnZS48L3A+CiAgPC9ib2R5Pgo8L2h0bWw+Cg=\n" +
                        "--frontier--").getBytes();

        final Multipart multipart = new Multipart(new ByteArrayInputStream(payload), "frontier");
        multipart.parse();

        final List<MimePart> parts = multipart.getParts();
        assertThat(parts.size(), equalTo(2));

        assertThat(parts.get(0).getHeaders(), equalTo(
                Arrays.asList(new MimeHeader("Content-Type: text/plain"
                ))));

        assertThat(parts.get(1).getHeaders(), equalTo(Arrays.asList(
                new MimeHeader("Content-Type: application/octet-stream"),
                new MimeHeader("Content-Transfer-Encoding: base64")
        )));
    }

}
