package his.packet.clustring;

import java.util.ArrayList;
import java.util.List;

public class Feature {
	
	// the list of values 
	List<Integer> values = new ArrayList<Integer>();
	
	public double getAvagrageFrequency() {
		return (values.stream().mapToInt(Integer::intValue).sum()) / values.size();
	}
	
	public double getStandDeviation() {
		double avarage = this.getAvagrageFrequency();
		int sumSquareDifferences = 0; 
		for( int val :  this.values) {
			sumSquareDifferences += Math.pow(( val - avarage), 2);
		}
		return Math.sqrt((1 / values.size()) * sumSquareDifferences) ;
	}
}
