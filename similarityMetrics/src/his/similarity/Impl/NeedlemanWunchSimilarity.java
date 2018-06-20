package his.similarity.Impl;

import his.similarity.functions.MatchMismatch;
import his.similarity.functions.Substitution;
import his.similarity.metrics.Similarity;
import his.similarity.functions.Math;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Objects;

public class NeedlemanWunchSimilarity implements Similarity {

	private static final Substitution MATCH_0_MISMATCH_1 = new MatchMismatch(0.0f, -1.0f);

	private final Substitution substitution;
	private final float gapValue;

	public NeedlemanWunchSimilarity() {
		this(-2.0f, MATCH_0_MISMATCH_1);
	}

	public NeedlemanWunchSimilarity(float gapValue, Substitution substitution) {
		this.gapValue = gapValue;
		this.substitution = substitution;
	}

	@Override
	public double distance(String s1, String s2) {
		return (1 - similarity(s1, s2));
	}

	@Override
	public double similarity(String s1, String s2) {

		if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}
		
		if (s1.isEmpty() && s2.isEmpty()) {
			return 1;
		}

		if (s1.equals(s2)) {
			return 1;
		}

		float maxDistance = max(s1.length(), s2.length()) * max(substitution.max(), gapValue);
		float minDistance = max(s1.length(), s2.length()) * min(substitution.min(), gapValue);

		return (-needlemanWunch(s1, s2) - minDistance) / (maxDistance - minDistance);
	}

	private float needlemanWunch(final String s, final String t) {

		if (Objects.equals(s, t)) {
			return 0;
		}

		if (s.isEmpty()) {
			return -gapValue * t.length();
		}
		if (t.isEmpty()) {
			return -gapValue * s.length();
		}

		final int n = s.length();
		final int m = t.length();

		// We're only interested in the alignment penalty between s and t
		// and not their actual alignment. This means we don't have to backtrack
		// through the n-by-m matrix and can safe some space by reusing v0 for
		// row i-1.
		float[] v0 = new float[m + 1];
		float[] v1 = new float[m + 1];

		for (int j = 0; j <= m; j++) {
			v0[j] = j;
		}

		for (int i = 1; i <= n; i++) {
			v1[0] = i;

			for (int j = 1; j <= m; j++) {
				v1[j] = Math.min(v0[j] - gapValue, v1[j - 1] - gapValue,
						v0[j - 1] - substitution.compare(s, i - 1, t, j - 1));
			}

			final float[] swap = v0;
			v0 = v1;
			v1 = swap;

		}

		// Because we swapped the results are in v0.
		return v0[m];
	}

}
