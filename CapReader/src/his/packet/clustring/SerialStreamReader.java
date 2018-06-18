package his.packet.clustring;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SerialStreamReader extends MyStreamReader {

	private int currentFolder = 0;
	private int currentHttpFolder = 0;
	private int currentFile = 0;

	public SerialStreamReader(String[] folers) throws FileNotFoundException {
		super(folers);
	}

	public String hasNext() throws IOException {
		String line = "";

		if ((line = readers[currentFolder][currentFile].readLine()) != null) {
			return line;
		}

		currentFile++;

		if (currentFile == hight && currentFolder == width) {
			return null;
		}

		if (currentFile == hight) {
			currentFile = 0;
			currentFolder++;
		}

		return hasNext();
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
