package his.pcap.reader.http;

import java.io.IOException;

import io.pkts.packet.impl.ApplicationPacket;

public interface HttpPacket extends ApplicationPacket {

	public HttpHeaders getHeaders();

	public byte[] getHttpPayload();

	boolean isCompressed();

	byte[] contentdecoding() throws IOException;

	public int getContentLength();

	public int getPayloadLength();

	public String toString();
}
