package his.similarity.functions;

public interface Gap {
	public float value(int fromIndex, int toIndex);
	public float max();
	public float min();
}
