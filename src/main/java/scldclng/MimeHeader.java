/**
 * Copyright
 */
package scldclng;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MimeHeader {

    // matches MIME header
    public static final Pattern HEADER_PATTERN =
            Pattern.compile("^([^\\:]+)\\:\\s*([^;]+);?\\s*\\;?\\s*(.*)\\s*$");

    // matches header parameters a=b and a="b"
    public static final Pattern PARAM_PATTERN =
            Pattern.compile("^([^=]+)\\s*=\\s*(\"?)([^\"]*)(\\2)$");

    private String name;
    private String type;
    private Map<String, String> parameters = new HashMap<String, String>();

    public MimeHeader(String header) {
        final Matcher matcher = HEADER_PATTERN.matcher(header);
        if (matcher.matches()) {
            this.name = matcher.group(1);
            this.type = matcher.group(2);

            final String paramString = matcher.group(3);
            if (paramString.length() > 0) {
                final String[] pairs = paramString.split("\\s*;\\s*");
                for (String pair : pairs) {
                    final Matcher paramMatcher = PARAM_PATTERN.matcher(pair);
                    if (paramMatcher.matches()) {
                        parameters.put(paramMatcher.group(1).toLowerCase(), paramMatcher.group(3));
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getParameter(String param) {
        return parameters.get(param.toLowerCase());
    }

    @Override
    public String toString() {
        return "MimeHeader{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MimeHeader that = (MimeHeader) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }
}
