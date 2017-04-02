package leetcode.algorithms.heap;

public class Super_Ugly_Number {
	
	public static void main(String[] args) {
		Super_Ugly_Number sun = new Super_Ugly_Number();
		int[] primes = {2, 3, 5};
		System.out.println(sun.nthSuperUglyNumber(2, primes));
	}
	
    public int nthSuperUglyNumber(int n, int[] primes) {
        
    	int[] ugly = new int[n];
    	int[] indexForNext = new int[primes.length];
    	ugly[0] = 1;
    	for(int i=1; i<n; i++) {
    		ugly[i] = Integer.MAX_VALUE;
    		for(int j=0; j<primes.length; j++) {
    			ugly[i] = Math.min(ugly[i], primes[j]*ugly[indexForNext[j]]);
    		}
    		
    		for(int j=0; j<primes.length; j++) {
    			if(primes[j]*ugly[indexForNext[j]] <= ugly[i]) indexForNext[j]++;
    		}
    	}
    	return ugly[n-1];
    }
    
    
}
