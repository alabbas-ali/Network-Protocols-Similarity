package his.CapReader.framer;

import java.io.IOException;

import his.CapReader.packet.http.HttpPacket;
import io.pkts.buffer.Buffer;
import io.pkts.framer.Framer;
import io.pkts.framer.FramingException;
import io.pkts.packet.TransportPacket;
import io.pkts.protocol.Protocol;

public final class HttpFramer implements Framer<TransportPacket, HttpPacket>{

	@Override
    public Protocol getProtocol() {
        return null;
    }

	@Override
	public boolean accept(Buffer data) throws IOException {
		
		return false;
	}
	
	@Override
	public HttpPacket frame(TransportPacket parent, Buffer buffer) throws IOException, FramingException {
		
		return null;
	}
}
