package his.packet.stream;

import java.io.IOException;

public interface ISerialStreamReader {
	public String hasNextRtp() throws IOException;

	public String hasNextSip() throws IOException;

	public String hasNextRtcp() throws IOException;

	public String hasNextSdp() throws IOException;

	public String hasNextHttp() throws IOException;

}
