package his.similarity.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class KShingling {

	private static final int DEFAULT_K = 2;

	private final int k;

	private static final Pattern SPACE_REG = Pattern.compile("\\s+");

	public KShingling(final int k) {
		if (k <= 0) {
			throw new IllegalArgumentException("k should be positive!");
		}
		this.k = k;
	}

	public KShingling() {
		this(DEFAULT_K);
	}

	public final int getK() {
		return k;
	}

	protected final Map<String, Integer> getProfile(final String input) {
		HashMap<String, Integer> shingles = new HashMap<String, Integer>();

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

	protected final double norm(final Map<String, Integer> profile) {
		double agg = 0;

		for (Map.Entry<String, Integer> entry : profile.entrySet()) {
			agg += 1.0 * entry.getValue() * entry.getValue();
		}

		return Math.sqrt(agg);
	}

	protected final double dotProduct(final Map<String, Integer> profile1, final Map<String, Integer> profile2) {

		// Loop over the smallest map
		Map<String, Integer> small_profile = profile2;
		Map<String, Integer> large_profile = profile1;
		if (profile1.size() < profile2.size()) {
			small_profile = profile1;
			large_profile = profile2;
		}

		double agg = 0;
		for (Map.Entry<String, Integer> entry : small_profile.entrySet()) {
			Integer i = large_profile.get(entry.getKey());
			if (i == null) {
				continue;
			}
			agg += 1.0 * entry.getValue() * i;
		}

		return agg;
	}

	protected final Map<String, Integer> tokenize(final String input) {
		HashMap<String, Integer> shingles = new HashMap<String, Integer>();

		String string_no_space = SPACE_REG.matcher(input).replaceAll("");
		int postion = 0;
		final int length = string_no_space.length();
		while (postion <= length) {
			int start = postion;
			int end = postion + this.getK() < length ? postion + this.getK() : length;
			final String shingle = string_no_space.substring(start, end);
			Integer old = shingles.get(shingle);
			if (old != null) {
				shingles.put(shingle, old + 1);
			} else {
				shingles.put(shingle, 1);
			}
			postion += this.getK();
		}
		return Collections.unmodifiableMap(shingles);

	}

}
