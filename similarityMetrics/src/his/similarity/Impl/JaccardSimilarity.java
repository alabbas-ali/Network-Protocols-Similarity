package his.similarity.Impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import his.similarity.functions.KShingling;
import his.similarity.metrics.Similarity;

public class JaccardSimilarity extends KShingling implements Similarity {

	public JaccardSimilarity(final int k) {
		super(k);
	}

	public JaccardSimilarity() {
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

		Map<String, Integer> profile1 = getProfile(s1);
		Map<String, Integer> profile2 = getProfile(s2);

		Set<String> union = new HashSet<String>();
		union.addAll(profile1.keySet());
		union.addAll(profile2.keySet());

		int inter = profile1.keySet().size() 
				+ profile2.keySet().size() 
				- union.size();

		return 1.0 * inter / union.size();
	}

}
