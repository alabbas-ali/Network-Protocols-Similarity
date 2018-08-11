package his.packet.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class MyStreamReader {

	protected int hight;
	protected int width;
	protected BufferedReader[][] readers;
	protected boolean[][] streamsEnd;

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
