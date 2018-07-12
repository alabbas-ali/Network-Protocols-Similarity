package his.packet.clustring;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import his.similarity.metrics.Similarities;
import his.similarity.metrics.Similarity;


public class Categorizer {

	private String category = "unknown";
	
	private List<Category> categories;
	
	//private double[][] similarityResults;
	private int k;
	
	private Similarity similarity;
	
	public Categorizer(Similarities type, List<Category> categories, int k) {
		this.categories = categories;
		this.k = k;
		//this.similarityResults = new double[categories.size()][5];
		this.similarity = Similarity.getInstance(type, k);
	}

	public String categorize(String text) {
		Map<String,Integer> prof = getProfile(text, k);
		double minDistance = Integer.MAX_VALUE;
		for (Category cat : categories) {
			Map<String,Integer> catProf = cat.getProfile();
			double distance = this.similarity.distance(catProf, prof);
			if (distance < minDistance) {
				minDistance = distance;
				this.category = cat.getName();
			}
		}
		return this.category;
	}
	
	public static final Map<String, Integer> getProfile(final String input, int k) {
		HashMap<String, Integer> shingles = new HashMap<String, Integer>();
		Pattern SPACE_REG = Pattern.compile("\\s+");

		String string_no_space = SPACE_REG.matcher(input).replaceAll("");
		for (int i = 0; i < (string_no_space.length() - k + 1); i++) {
			String shingle = string_no_space.substring(i, i + k);
			Integer old = shingles.get(shingle);
			if (old != null) {
				shingles.put(shingle, old + 1);
			} else {
				shingles.put(shingle, 1);
			}
		}
		return Collections.unmodifiableMap(shingles);
	}

}
