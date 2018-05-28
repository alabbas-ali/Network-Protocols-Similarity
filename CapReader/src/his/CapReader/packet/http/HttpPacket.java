package his.CapReader.packet.http;

import java.io.IOException;

import io.pkts.packet.impl.ApplicationPacket;

public interface HttpPacket extends ApplicationPacket {

	public Headers getHeaders();

	public byte[] getHttpPayload();

	boolean isCompressed();

	String contentdecoding() throws IOException;

	public int getContentLength();

	public int getPayloadLength();

	public String toString();
}
