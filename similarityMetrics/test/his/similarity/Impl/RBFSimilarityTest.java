package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RBFSimilarityTest {
	
	@Test
    public void testSimilarity() {
		RBFSimilarity instance = new RBFSimilarity(1);
		
		double result = instance.similarity("ABCD", "12346");
        assertEquals(0.1, result, 0.1);
        
        result = instance.similarity("ABECD", "ABEF");
        assertEquals(0.5, result, 0.1);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        
        instance = new RBFSimilarity(3);
        result = instance.similarity("ABC", "ABCE");
        assertEquals(0.6, result, 0.1);
        
        instance = new RBFSimilarity(5); 
        result = instance.similarity("Test String1", "Test String2");
		assertEquals(0.7, result, 0.1);
        
        NullTests.testSimilarity(instance);
	}
	
	@Test
    public void testDistance() {
		RBFSimilarity instance = new RBFSimilarity();
		double result = instance.distance("ABECD", "ABEF");
        assertEquals(0.5, result, 0.1);
        NullTests.testDistance(instance);
	}
}
