package his.CapReader.packet.http;

import io.pkts.buffer.Buffer;

public class InputData {
	private Buffer data;
    private Headers headers;

    public InputData(Buffer data, Headers headers) {
        this.data = data;
        this.headers = headers;
    }

    public Headers getHeaders() {
        return headers;
    }

    public Buffer getData() {
        return data;
    }

    public int getInputLength() {
        return data.getReadableBytes();
    }

    public int getContentLength() {
        return headers.getContentLength();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (headers != null) {
            builder.append("Headers...\n");
            for (String name : headers.getNames()) {
                builder.append(String.format("%s: %s\n", name, headers.getValue(name)));
            }
        }
        builder.append(String.format("Encoded string: %s\n", data.toString()));
        return builder.toString();
    }
}
