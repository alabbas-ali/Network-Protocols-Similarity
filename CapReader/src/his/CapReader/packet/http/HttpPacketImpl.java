package his.CapReader.packet.http;

import java.io.IOException;
import java.io.OutputStream;

import io.pkts.buffer.Buffer;
import io.pkts.packet.Packet;
import io.pkts.packet.PacketParseException;
import io.pkts.packet.TransportPacket;
import io.pkts.packet.impl.AbstractPacket;
import io.pkts.protocol.Protocol;

public class HttpPacketImpl extends AbstractPacket implements HttpPacket{
	
	private Headers headers;
    
    public HttpPacketImpl(final TransportPacket parent, final Headers headers, final Buffer payload) {
        super(Protocol.TCP, parent, payload);
        this.headers = headers;
    }

	@Override
	public long getArrivalTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void write(OutputStream out, Buffer payload) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Packet getNextPacket() throws IOException, PacketParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet clone() {
		// TODO Auto-generated method stub
		return null;
	}
    
	public Headers getHeaders() {
		return headers;
	}
}
