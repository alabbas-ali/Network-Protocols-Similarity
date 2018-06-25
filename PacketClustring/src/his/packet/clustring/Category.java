package his.packet.clustring;

import java.util.HashMap;
import java.util.Map;

public class Category extends HashMap<String, Feature>{

	private static final long serialVersionUID = 1L;
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public void addFeatures(Map<String, Integer> newFeatures) {
		for (Entry<String, Integer> entry : newFeatures.entrySet()) {
			if (this.containsKey(entry.getKey())) {
				this.get(entry.getKey()).values.add(entry.getValue());
			} else {
				Feature f = new Feature();
				f.values.add(entry.getValue());
				this.put(entry.getKey(),  f);
			}
		}
	}

	public String getName() {
		return name;
	}

	public Map<String,Integer> getProfile() {
		Map<String,Integer> prof = new HashMap<String,Integer>();
		for (Entry<String, Feature> entry : this.entrySet()) {
			prof.put(entry.getKey(), (int) Math.floor(entry.getValue().getAvagrageFrequency()));
		}
		return prof;
	}
	
}
