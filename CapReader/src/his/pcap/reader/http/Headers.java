package his.pcap.reader.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Headers {
	public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String GZIP = "gzip";
    public static final String DICT = "dict";
    public static final String DEFLATE = "deflate";

    private Map<String, String> headers;
    private CompressionType compressionType;

    public Headers() {
        headers = new HashMap<String, String>();
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public Set<String> getNames() {
        return headers.keySet();
    }

    public String getValue(String name) {
        return headers.get(name);
    }

    public boolean hasHeader(String key) {
        return headers.keySet().contains(key);
    }

    public boolean checkIfExistsWithNonEmptyValue(String tag) {
        if (hasHeader(tag)) {
            String value = getValue(tag);
            return !value.isEmpty();
        }
        return false;
    }

    public String getIfExistsWithNonEmptyValue(String tag) {
        if (hasHeader(tag)) {
            String value = getValue(tag);
            if (!value.isEmpty()) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return headers.toString();
    }

    public int getContentLength() {
        String contentLength = getValue(CONTENT_LENGTH);
        if (
        	contentLength != null && 
        	contentLength.matches("-?\\d+(\\.\\d+)?")
        ) {
            return Integer.parseInt(contentLength);
        }
        return 0;
    }

    public CompressionType getCompressionType() {
        String contentEncoding = getValue(Headers.CONTENT_ENCODING);
        if (!contentEncoding.isEmpty() && CompressionType.isValid(contentEncoding)) {
            compressionType = CompressionType.valueOf(contentEncoding);
        }
        return compressionType;
    }
}
