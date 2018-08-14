package his.pcap.reader.sip;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SIPHeaders {
	public static final String VIA = "Via";
    public static final String RECORD_ROUTE = "Record-Route";
    public static final String MAX_FORWARDS = "Max-Forwards";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CALL_ID = "Call-ID";
    public static final String FROM = "From";
    public static final String TO = "To";
    public static final String USER_AGENT = "User-Agent";

    private Map<String, String> headers;

    public SIPHeaders() {
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
}
