/**
 * Copyright
 */
package scldclng;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FileUploader {

    private ProgressListener progressListener;

    private HttpServletRequest req;

    public FileUploader(final HttpServletRequest req) {
        this.req = req;
    }

    public void setProgressListener(final ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public void upload() throws IOException {
        final ServletInputStream in = req.getInputStream();
        final byte[] buff = new byte[8192];

        long bytesReadTotal = 0, bytesRead;
        while ((bytesRead = in.read(buff)) > 0) {
            bytesReadTotal += bytesRead;

            if (progressListener != null) {
                progressListener.onBytesRead(bytesReadTotal);
            }

            Thread.yield();

        }
    }
}
