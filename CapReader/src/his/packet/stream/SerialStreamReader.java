package his.packet.stream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SerialStreamReader extends MyStreamReader implements ISerialStreamReader{

	private int currentHttpFolder = 0;
	private int currentRtpFolder = 0;
	private int currentSipFolder = 0;
	private int currentRtcpFolder = 0;
	private int currentSdpFolder = 0;

	public SerialStreamReader(String[] folers) throws FileNotFoundException {
		super(folers);
	}
	
	public String hasNextRtp() throws IOException {
		String line = "";
		if ((line = readers[currentRtpFolder][0].readLine()) != null) {
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
		if ((line = readers[currentSipFolder][1].readLine()) != null) {
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
		if ((line = readers[currentRtcpFolder][2].readLine()) != null) {
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
		if ((line = readers[currentSdpFolder][3].readLine()) != null) {
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
		if ((line = readers[currentHttpFolder][4].readLine()) != null) {
			return line;
		}
		currentHttpFolder ++;
		if(currentHttpFolder == width) {
			return null;
		}
		return hasNextHttp();
	}
	
}


