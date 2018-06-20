package his.similarity.Impl;

import java.util.Map;

import his.similarity.functions.KShingling;
import his.similarity.metrics.Similarity;

public class CosineSimilarity extends KShingling implements Similarity {

	public CosineSimilarity(final int k) {
		super(k);
	}

	public CosineSimilarity() {
		super();
	}

	public final double distance(final String s1, final String s2) {
		return 1.0 - similarity(s1, s2);
	}

	public final double similarity(final String s1, final String s2) {
		if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}

		if (s1.equals(s2)) {
			return 1;
		}

		if (s1.length() < getK() || s2.length() < getK()) {
			return 0;
		}

		Map<String, Integer> profile1 = getProfile(s1);
		Map<String, Integer> profile2 = getProfile(s2);

		return dotProduct(profile1, profile2) / (norm(profile1) * norm(profile2));
	}

	
}
