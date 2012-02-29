/**
 * Copyright
 */
package scldclng;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents MIME part
 */
public class MimePart {

    private List<MimeHeader> headers = new ArrayList<MimeHeader>();

    public void addHeader(MimeHeader header) {
        this.headers.add(header);
    }

    public List<MimeHeader> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "MimePart{" +
                "headers=" + headers +
                '}';
    }
}
