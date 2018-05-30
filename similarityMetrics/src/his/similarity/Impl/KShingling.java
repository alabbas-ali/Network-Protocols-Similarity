package his.similarity.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class KShingling {

	private static final int DEFAULT_K = 1;

    private final int k;


    private static final Pattern SPACE_REG = Pattern.compile("\\s+");

    public KShingling(final int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k should be positive!");
        }
        this.k = k;
    }


    KShingling() {
        this(DEFAULT_K);
    }

    public final int getK() {
        return k;
    }

    /**
     * Compute and return the profile of s, as defined by Ukkonen "Approximate
     * string-matching with q-grams and maximal matches".
     * https://www.cs.helsinki.fi/u/ukkonen/TCS92.pdf The profile is the number
     * of occurrences of k-shingles, and is used to compute q-gram similarity,
     * Jaccard index, etc. Pay attention: the memory requirement of the profile
     * can be up to k * size of the string
     *
     */
    public final Map<String, Integer> getProfile(final String string) {
        HashMap<String, Integer> shingles = new HashMap<String, Integer>();

        String string_no_space = SPACE_REG.matcher(string).replaceAll(" ");
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
