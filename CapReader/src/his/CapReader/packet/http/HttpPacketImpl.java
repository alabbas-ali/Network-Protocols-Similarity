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
	
	private final TransportPacket parent;

    /**
     * All the RTP headers as one buffer.
     */
    private final Buffer headers;

    /**
     * The raw payload of the RTP packet. Is most likely audio or video.
     */
    private final Buffer payload;
    
    /**
     * 
     */
    public HttpPacketImpl(final TransportPacket parent, final Buffer headers, final Buffer payload) {
        super(Protocol.TCP, parent, payload);
        this.parent = parent;
        this.headers = headers;
        this.payload = payload;
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
    
}
