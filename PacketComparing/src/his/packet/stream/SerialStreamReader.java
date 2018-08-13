package his.packet.stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class SerialStreamReader extends MyStreamReader implements ISerialStreamReader{

	private int currentHttpFolder = 0;
	private int currentRtpFolder = 0;
	private int currentSipFolder = 0;
	private int currentRtcpFolder = 0;
	private int currentSdpFolder = 0;
	private int currentFTPFolder = 0;
	
	private Map<String, Integer>  order;
	
	public SerialStreamReader(String[] folers, String[] files, Map<String, Integer>  order) throws FileNotFoundException {
		super(folers, files);
		this.order = order;
	}
	
	public String hasNextRtp() throws IOException {
		String line = "";
		if ((line = readers[currentRtpFolder][order.get("rtp")].readLine()) != null) {
			return line;
		}
		currentRtpFolder ++;
		if(currentRtpFolder == width) {
			return null;
		}
		return hasNextRtp();
	}

	public String hasNextSip() throws IOException {
		String line = "";
		if ((line = readers[currentSipFolder][order.get("sip")].readLine()) != null) {
			return line;
		}
		currentSipFolder ++;
		if(currentSipFolder == width) {
			return null;
		}
		return hasNextSip();
	}

	public String hasNextRtcp() throws IOException {
		String line = "";
		if ((line = readers[currentRtcpFolder][order.get("rtcp")].readLine()) != null) {
			return line;
		}
		currentRtcpFolder ++;
		if(currentRtcpFolder == width) {
			return null;
		}
		return hasNextRtcp();
	}
	
	public String hasNextSdp() throws IOException {
		String line = "";
		if ((line = readers[currentSdpFolder][order.get("sdp")].readLine()) != null) {
			return line;
		}
		currentSdpFolder ++;
		if(currentSdpFolder == width) {
			return null;
		}
		return hasNextSdp();
	}
	
	public String hasNextHttp() throws IOException {
		String line = "";
		if ((line = readers[currentHttpFolder][order.get("http")].readLine()) != null) {
			return line;
		}
		currentHttpFolder ++;
		if(currentHttpFolder == width) {
			return null;
		}
		return hasNextHttp();
	}
	
	public String hasNextFtp() throws IOException {
		String line = "";
		if ((line = readers[currentFTPFolder][order.get("ftp")].readLine()) != null) {
			return line;
		}
		currentFTPFolder ++;
		if(currentFTPFolder == width) {
			return null;
		}
		return hasNextFtp();
	}
	
}


