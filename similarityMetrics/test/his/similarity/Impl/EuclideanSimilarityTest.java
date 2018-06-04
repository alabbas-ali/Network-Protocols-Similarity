package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EuclideanSimilarityTest {
	
	@Test
    public void testSimilarity() {
		EuclideanSimilarity instance = new EuclideanSimilarity();
		
		double result = instance.similarity("ABCD", "123");
        assertEquals(0.4, result, 0.1);
        
        result = instance.similarity("ABCD", "ABEF");
        assertEquals(0.6, result, 0.1);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        
        instance = new EuclideanSimilarity(3);
        result = instance.similarity("ABC", "ABCE");
        assertEquals(0.5, result, 0.1);
        
        instance = new EuclideanSimilarity(5); 
        result = instance.similarity("Test String1", "Test String2");
		assertEquals(0.6, result, 0.1);
        
        NullTests.testSimilarity(instance);
	}
	
	@Test
    public void testDistance() {
		EuclideanSimilarity instance = new EuclideanSimilarity();
		double result = instance.distance("ABECD", "ABEF");
        assertEquals(1.41, result, 0.01);
        NullTests.testDistance(instance);
	}
}
