package his.similarity.functions;

public class MatchMismatch implements Substitution {

	private final float matchValue;
	private final float mismatchValue;

	public MatchMismatch(float matchValue, float mismatchValue) {
		super();

		this.matchValue = matchValue;
		this.mismatchValue = mismatchValue;
	}

	@Override
	public float compare(String a, int aIndex, String b, int bIndex) {
		return a.charAt(aIndex) == b.charAt(bIndex) ? matchValue : mismatchValue;
	}

	@Override
	public float max() {
		return matchValue;
	}

	@Override
	public float min() {
		return mismatchValue;
	}

	@Override
	public String toString() {
		return "MatchMismatch [matchCost=" + matchValue + ", mismatchCost=" + mismatchValue + "]";
	}

}
