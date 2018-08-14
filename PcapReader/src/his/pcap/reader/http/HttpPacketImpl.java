package his.pcap.reader.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import io.pkts.buffer.Buffer;
import io.pkts.packet.Packet;
import io.pkts.packet.PacketParseException;
import io.pkts.packet.TransportPacket;
import io.pkts.packet.impl.AbstractPacket;
import io.pkts.protocol.Protocol;

public class HttpPacketImpl extends AbstractPacket implements HttpPacket {

	private HttpHeaders headers;
	private byte[] payload;

	public HttpPacketImpl(final TransportPacket parent, final HttpHeaders headers, final byte[] payload) {
		super(Protocol.TCP, parent, null);
		this.headers = headers;
		this.payload = payload;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public byte[] getHttpPayload() {
		return payload;
	}

	public boolean isCompressed() {
		return this.getHeaders().getValue("Content-Encoding") != null
				&& this.getHeaders().getValue("Content-Encoding").equals("gzip");
	}

	public byte[] contentdecoding() throws IOException {

		byte[] reply = this.getHttpPayload();
		int i;
		for (i = 0; i < reply.length-3; i++) {
			// Finding Raw Response by two new line bytes
			if (reply[i] == 13) {
				if (reply[i + 1] == 10) {
					if (reply[i + 2] == 13) {
						if (reply[i + 3] == 10) {
							break;
						}
					}
				}
			}
		}
		// Creating new Bytes to parse it in GZIPInputStream
		byte[] newb = new byte[4096];
		int y = 0;
		for (int st = i + 4; st < reply.length; st++) {
			newb[y++] = reply[st];
		}
		GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(newb));
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int length;
	    while ((length = gzip.read(buffer)) > 0) {
	        outStream.write(buffer, 0, length);
	    }
	    return outStream.toByteArray();
	}

	public int getContentLength() {
		return headers.getContentLength();
	}

	public int getPayloadLength() {
		return payload.length;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (headers != null) {
			builder.append("Headers...\n");
			for (String name : headers.getNames()) {
				builder.append(String.format("%s: %s\n", name, headers.getValue(name)));
			}
		}
		builder.append(String.format("Encoded string: %s\n", payload.toString()));
		return builder.toString();
	}
	
	public long getArrivalTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void write(OutputStream out, Buffer payload) throws IOException {
		// TODO Auto-generated method stub
	}

	public Packet getNextPacket() throws IOException, PacketParseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Packet clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
