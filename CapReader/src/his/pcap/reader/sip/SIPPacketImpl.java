package his.pcap.reader.sip;

import java.io.IOException;
import java.io.OutputStream;

import io.pkts.buffer.Buffer;
import io.pkts.packet.Packet;
import io.pkts.packet.PacketParseException;
import io.pkts.packet.TransportPacket;
import io.pkts.packet.impl.AbstractPacket;
import io.pkts.protocol.Protocol;

public class SIPPacketImpl extends AbstractPacket implements SIPPacket{
	
	private SIPHeaders headers;
	private byte[] payload;
	
	public SIPPacketImpl(final TransportPacket parent, final SIPHeaders headers, final byte[] payload) {
		super(Protocol.TCP, parent, null);
		this.headers = headers;
		this.payload = payload;
	}

	public SIPHeaders getHeaders() {
		return this.headers;
	}

	public byte[] getSipPayload() {
		return payload;
	}

	public int getContentLength() {
		return headers.getContentLength();
	}

	public int getPayloadLength() {
		return payload.length;
	}

	public Packet clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public long getArrivalTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void write(OutputStream out, Buffer payload) throws IOException {
		// TODO Auto-generated method stub
	}

	public Packet getNextPacket() throws IOException, PacketParseException {
		return null;
	}
	
}
