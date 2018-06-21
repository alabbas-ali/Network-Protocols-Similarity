package his.packet.clustring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Cluster {

	private Map<String, Feature> features = new HashMap<String, Feature>();
	private String name;

	public Cluster(String name) {
		this.name = name;
	}

	public void addFeatures(Map<String, Integer> newFeatures) {
		for (Entry<String, Integer> entry : newFeatures.entrySet()) {
			if (this.features.containsKey(entry.getKey())) {
				this.features.get(entry.getKey()).values.add(entry.getValue());
			} else {
				Feature f = new Feature();
				f.values.add(entry.getValue());
				this.features.put(entry.getKey(),  f);
			}
		}
	}

	public Map<String, Feature> getFeatures() {
		return features;
	}

	public String getName() {
		return name;
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
