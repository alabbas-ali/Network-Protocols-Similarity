package his.similarity.functions;

public interface Substitution {

	public float compare(String a, int aIndex, String b, int bIndex);

	public float max();

	public float min();
}
