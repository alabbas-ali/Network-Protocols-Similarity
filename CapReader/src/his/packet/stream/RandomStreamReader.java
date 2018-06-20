package his.packet.stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class RandomStreamReader extends MyStreamReader implements IRandomStreamReader{

	private Random randomNum = new Random();

	public RandomStreamReader(String[] folers) throws FileNotFoundException {
		super(folers);
	}

	public String hasNext() throws IOException {

		if (allAtreamsEnd()) {
			return null;
		}

		int randfolder = randomNum.nextInt(width);
		int randfile = randomNum.nextInt(hight);

		while (this.streamsEnd[randfolder][randfile]) {
			randfolder = randomNum.nextInt(width);
			randfile = randomNum.nextInt(hight);
		}

		String line = "";

		if ((line = readers[randfolder][randfile].readLine()) != null) {
			return line;
		}

		streamsEnd[randfolder][randfile] = true;

		return hasNext();
	}

	private boolean allAtreamsEnd() {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < hight; j++) {
				if (!streamsEnd[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

}
