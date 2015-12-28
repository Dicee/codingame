package unknownSource;

import static java.lang.Math.max;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Pb1 {
	public int solution(int[] arr) {
		int minimaxLeft  = arr[0];
		int minimaxRight = arr[arr.length - 1];
		
		int max = arr[0];
		for (int x : arr) max = max(max, x);
		return max(max - minimaxLeft, max - minimaxRight);
	}
	
	@Test
	public void test() {
		Pb1 sol = new Pb1();
		assertThat(sol.solution(new int[] { 1,3,-3      }), is(6));
		assertThat(sol.solution(new int[] { 4,3,2,5,1,1 }), is(4));
	}
}
