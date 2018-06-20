package his.similarity.functions;


/**
 * A gap function that calculates the gap penalty as
 * <code>A+(B * GapLegth)</code>.
 * 
 * <p>
 * This class is immutable and thread-safe.
 * 
 * @see Substitution
 * @see <a href="https://en.wikipedia.org/wiki/Gap_penalty">Wikipedia - Gap
 *      Penalty</a>
 *
 */
public class AffineGap implements Gap {

	private final float startValue;
	private final float gapValue;

	public AffineGap(float startValue, float gapValue) {
		this.startValue = startValue;
		this.gapValue = gapValue;
	}

	@Override
	public float value(int fromIndex, int toIndex) {

		return startValue + gapValue * (toIndex - fromIndex - 1);
	}

	@Override
	public final float max() {
		return startValue;
	}

	@Override
	public final float min() {
		return Float.NEGATIVE_INFINITY;
	}

	@Override
	public String toString() {
		return "AffineGap [startValue=" + startValue + ", gapValue=" + gapValue + "]";
	}

}


