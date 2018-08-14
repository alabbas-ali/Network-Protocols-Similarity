package his.pcap.reader.sip;

import io.pkts.packet.impl.ApplicationPacket;

public interface SIPPacket extends ApplicationPacket {
	public SIPHeaders getHeaders();

	public byte[] getSipPayload();
	
	public int getContentLength();

	public int getPayloadLength();

	public String toString();
}
