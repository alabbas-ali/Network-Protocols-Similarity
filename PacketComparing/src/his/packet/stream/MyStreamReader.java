package his.packet.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class MyStreamReader {

	protected int hight;
	protected int width;
	protected BufferedReader[][] readers;
	protected boolean[][] streamsEnd;
	
	//  String[] files =  {"/rtp.txt", "/sip.txt", "/rtcp.txt", "/sdp.txt", "/http.txt", "/ftp.txt"}
	//  Please keep this order , you can remove one or moor but you have to keep the order 

	public MyStreamReader(String[] folers, String[] files) throws FileNotFoundException {
		width = folers.length;
		hight = files.length;
		readers = new BufferedReader[width][hight];
		streamsEnd = new boolean[width][hight];

		for (int i = 0; i < width; i++) {
			String dirName = folers[i];
			for (int j = 0; j < hight; j++) {
				readers[i][j] = new BufferedReader(new FileReader(dirName + files[j]));
				streamsEnd[i][j] = false;
			}
		}
	}

}
