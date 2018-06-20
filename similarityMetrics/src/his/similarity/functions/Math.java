package his.similarity.functions;


public final class Math {
	
	private Math() {
		// Utility class
	}

	public static float max(float a, float b, float c) {
		return java.lang.Math.max(java.lang.Math.max(a, b), c);
	}

	public static int max(final int a, final int b, final int c) {
		return java.lang.Math.max(java.lang.Math.max(a, b), c);
	}

	public static float max(float a, float b, float c, float d) {
		return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(a, b), c), d);
	}

	public static int max(final int a, final int b, final int c, final int d) {
		return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(a, b), c), d);
	}

	public static float min(float a, float b, float c) {
		return java.lang.Math.min(java.lang.Math.min(a, b), c);
	}

	public static int min(final int a, final int b, final int c) {
		return java.lang.Math.min(java.lang.Math.min(a, b), c);
	}

	public static float min(final float a, final float b, final float c, final float d) {
		return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(a, b), c), d);
	}

	public static int min(final int a, final int b, final int c, final int d) {
		return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(a, b), c), d);
	}

}
