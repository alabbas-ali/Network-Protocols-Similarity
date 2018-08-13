package his.packet.comparing;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import his.packet.stream.SerialStreamReader;
import his.pcap.reader.CapReader;
import his.similarity.metrics.Similarities;
import his.similarity.metrics.Similarity;

public class MultiComparing {
	
	public static Similarity cosine = Similarity.getInstance(Similarities.COSINE, 2);
	public static Similarity jaccard = Similarity.getInstance(Similarities.JACCARD, 2);
	public static Similarity rbf = Similarity.getInstance(Similarities.RBF, 2);
	public static Similarity ngram = Similarity.getInstance(Similarities.NGRAM, 2);
	public static Similarity need = Similarity.getInstance(Similarities.NEEDLEMAN, 2);
	public static Similarity smith = Similarity.getInstance(Similarities.SMITHWATERMAN, 2);
	
	public static ExcelOperation eOperation;
	
	public static int experment;
	public static int rownum = 0;
	
	public static void main(String[] args) throws IOException {
		
		eOperation = new ExcelOperation("resources/output.xlsx");
		
		for (int j = 1; j < 4 ; j++) {
			String[] files = new String[50];
			for (int i = 0; i < 50 ; i++) {
				files[i] = "traffic" + (i + 1) + ".pcap";
			}
			
			CapReader reader = new CapReader();
			reader.readFiles("../scripts/" + j + "/", files);
		}
		
		String[] files = { "/sip.txt", "/http.txt", "/ftp.txt" };
		Map<String, Integer>  order = new HashMap<String, Integer>();
		order.put("sip", 0);
		order.put("http", 1);
		order.put("ftp", 2);
		
		for (int i = 1; i < 51 ; i++) {
			
			String[] stream1_folder = { "../scripts/1/trafic" + i + "_paylod" };
			String[] stream2_folder = { "../scripts/2/trafic" + i + "_paylod" };
			String[] stream3_folder = { "../scripts/3/trafic" + i + "_paylod" };
			Map<String, String> stream1 = readStream(stream1_folder, files, order);
			Map<String, String> stream2 = readStream(stream2_folder, files, order);
			Map<String, String> stream3 = readStream(stream3_folder, files, order);
			
			eOperation.createSheet(i);
			rownum = 0;
			experment = i;
			
			System.out.println("::: Comparing the same stream :::");
			printTable(stream1, stream1);
			
			System.out.println("::: Comparing the 50% same stream :::");
			printTable(stream1, stream2);
			
			System.out.println("::: Comparing the tow different streams :::");
			printTable(stream1, stream3);
			
		}
		
		eOperation.save();
	}
	
	public static void printTable(
			Map<String, String> stream1,
			Map<String, String> stream2
	) {
		rownum ++;
		eOperation.createHeadLine(rownum);
		printRow("http", "http", stream1, stream2);
		printRow("sip", "sip", stream1, stream2);
		printRow("ftp", "ftp", stream1, stream2);
		printRow("http", "sip", stream1, stream2);
		printRow("http", "ftp", stream1, stream2);
		printRow("sip", "ftp", stream1, stream2);
		
	}
	
	public static void printRow(
			String protocol1,
			String protocol2,
			Map<String, String> stream1,
			Map<String, String> stream2
	) {
		NumberFormat formatter = new DecimalFormat("#0.0000");
		rownum ++;
		String[] out = new String[6];
		out[0] = protocol1 + "/" + protocol2;
		System.out.print(" Compare number :" + experment + ", protocol " + protocol1 + " with " + protocol2 + " ");
		out[1] = formatter.format(cosine.similarity(stream1.get(protocol1), stream2.get(protocol2)));
		System.out.print(".");
		out[2] = formatter.format(jaccard.similarity(stream1.get(protocol1), stream2.get(protocol2)));
		System.out.print(".");
		out[3] = formatter.format(rbf.similarity(stream1.get(protocol1), stream2.get(protocol2)));
		System.out.print(".");
		out[4] = formatter.format(ngram.similarity(stream1.get(protocol1), stream2.get(protocol2)));
		System.out.print(".");
		out[5] = formatter.format(need.similarity(stream1.get(protocol1), stream2.get(protocol2)));
		System.out.print(".");
		//out[6] = formatter.format(smith.similarity(stream1.get(protocol1), stream1.get(protocol2)));
		System.out.print(".");
		System.out.println();
		eOperation.addRowInfo( out , rownum);
	}
	
	public static Map<String, String> readStream(String[] folders, String[] files, Map<String, Integer> order) {
		
		Map<String, String> stream = new HashMap<String, String>();
		
		try {
			SerialStreamReader serialStreams = new SerialStreamReader(folders, files, order);
			
			String sipbacket = "";
			while ((sipbacket = serialStreams.hasNextSip()) != null) {
				if (stream.containsKey("sip")) {
					stream.put("sip", stream.get("sip") + sipbacket);
				} else {
					stream.put("sip", sipbacket);
				}
			}
			
			String httpbacket = "";
			while ((httpbacket = serialStreams.hasNextHttp()) != null) {
				if (stream.containsKey("http")) {
					stream.put("http", stream.get("http") + httpbacket);
				} else {
					stream.put("http", httpbacket);
				}
			}
			
			String ftpbacket = "";
			while ((ftpbacket = serialStreams.hasNextFtp()) != null) {
				if (stream.containsKey("ftp")) {
					stream.put("ftp", stream.get("ftp") + ftpbacket);
				} else {
					stream.put("ftp", ftpbacket);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stream;
	}
}
