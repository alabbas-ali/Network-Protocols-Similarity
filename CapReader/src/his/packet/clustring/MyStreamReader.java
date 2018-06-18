package his.packet.clustring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class MyStreamReader {

	protected final String[] files = { "rtp.txt", "sip.txt", "rtcp.txt", "sdp.txt", "http.txt" };
	protected final int hight = 5;

	protected int width;
	protected BufferedReader[][] readers;
	protected boolean[][] streamsEnd;

	public MyStreamReader(String[] folers) throws FileNotFoundException {
		width = folers.length;
		readers = new BufferedReader[width][hight];
		streamsEnd = new boolean[width][hight];

		for (int i = 0; i < width; i++) {
			String dirName = "resources/" + folers[i];
			for (int j = 0; j < hight; j++) {
				readers[i][j] = new BufferedReader(new FileReader(dirName + files[j]));
				streamsEnd[i][j] = false;
			}
		}
	}

}
