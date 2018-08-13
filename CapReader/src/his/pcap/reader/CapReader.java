package his.pcap.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import io.pkts.Pcap;

public class CapReader {

	public void readFiles(String folder, String[] files) throws IOException {

		for (int i = 0; i < files.length; i++) {

			String file = folder + files[i];
			Pcap pcap = Pcap.openStream(file);
			pcap.loop(new MyPacketHandler(folder, "trafic" + (i + 1)));
			pcap.close();

		}

	}

	public void readFtp(String folder, String[] files) {
		Process p;

		for (int i = 0; i < files.length; i++) {

			try {
				System.out.println(folder + files[i]);

				p = Runtime.getRuntime().exec("C:/Program Files/Wireshark/tshark.exe -r " + folder + files[i]
						+ " -Y ftp-data -T fields -e tcp.stream ");
				p.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

				String line = "";
				String flowNumber = null;
				while ((line = reader.readLine()) != null) {
					// int num = Integer.parseInt(line);
					if (StringUtils.isNumeric(line)) {
						flowNumber = line;
					}
				}

				if (flowNumber != null) {
					
					p = Runtime.getRuntime().exec("C:/Program Files/Wireshark/tshark.exe -nr " + folder + files[i] + " -q -z follow,tcp,raw," + flowNumber);
					p.waitFor();
					System.out.println("finish");
					reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

					line = "";
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		//ProcessBuilder builder = new ProcessBuilder(commands);
		//builder.redirectOutput(new File(folder +"trafic" + i + "_paylod/ftp.txt"));
		//builder.redirectError(new File(folder +"trafic" + i + "_paylod/err.txt"));
		//
	}

}
