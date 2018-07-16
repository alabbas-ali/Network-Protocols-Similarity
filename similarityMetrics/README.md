
# HIS Similarity:

The purpose of this library is to implement a different method for string similarity and distance measures.
to be used in anomaly detection of network data flow of different
The algorithm includes:

- Cosine Similarity
- Jaccard Similarity
- Needleman Wunch Similarity
- NGram Similarity
- RBF Similarity
- Smith Waterman Similarity
- Mahalanobis Distance

## How to use it :

Please use the Similarity interface as the entry point to library, The interface has 4 basic methods:

- double distance(String s1, String s2);
- double similarity(String s1, String s2);
- double similarity(Map<String, Integer> profile1 , Map<String, Integer> profile2);
- double distance(Map<String, Integer> profile1 , Map<String, Integer> profile2);

Use this interface with the Similarities enum to istantiate any similarity measures avalable in Similarities enum.

example: 
```
Similarity cosines_imilarity = new Similarity.getInstance(Similarities.COSINE , 2);
```
