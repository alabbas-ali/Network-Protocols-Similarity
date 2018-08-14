package his.pcap.readerframer;

import java.io.IOException;

import his.pcap.reader.sip.SIPHeaders;
import his.pcap.reader.sip.SIPPacket;
import his.pcap.reader.sip.SIPPacketImpl;
import io.pkts.buffer.Buffer;
import io.pkts.framer.FramingException;
import io.pkts.packet.TransportPacket;

public class SipFramer {
	
	
	public static final String RESPONSE_IDENTIFIER = "MESSAGE ";

	public boolean accept(Buffer data) throws IOException {
		String bytesAsString = data.toString();
		
		if (bytesAsString.indexOf("MESSAGE ") > -1) {
			return true;
		}
		return false;
	}
	
	public SIPPacket frame(TransportPacket parent, Buffer buffer) throws IOException, FramingException {
		if (parent == null) {
			throw new IllegalArgumentException("The parent frame cannot be null");
		}

		if (!accept(buffer)) {
			throw new IllegalArgumentException("The buffer should contain Http data");
		}
		
		String packetAsString = buffer.toString();
		SIPHeaders headers = new SIPHeaders();
		byte[] payload = null;
		String[] tokens = packetAsString.split("\\r?\\n");
	
		for (String token : tokens) {
			if (token.isEmpty()) {
				break;
			}

			if (token.contains("MESSAGE sip:")) {
				packetAsString = packetAsString.replace(token, "");
			}
			
			if (token.contains(": ")) {
				String[] values = token.split(": ");
				headers.addHeader(values[0], values[1]);
				packetAsString = packetAsString.replace(token, "");
			}
		}
		packetAsString = packetAsString.replaceAll("(?m)^[ \t]*\r?\n", "");
		
		if(packetAsString.trim().length() > 0) {
			payload = packetAsString.getBytes();
		}
		return new SIPPacketImpl(parent, headers, payload);
	}

}
