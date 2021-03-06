public class Solution {
	public int singleNumber(int[] A) {
		int lowBits = 0;
		int highBits = 0;
		for (int i = 0; i < A.length; i++) {
			/*
			 * only when both the high bit and input are '1' result should change, from 0x11 to 0x00
			 * all other cases are straight plus
			 */
			int flip = ~(highBits & A[i]);
			int carry = lowBits & A[i];
			lowBits = (lowBits ^ A[i]) & flip;
			highBits = (highBits ^ carry) & flip;
		}
		return lowBits;
	}
}

class Solution2 {
	public int singleNumber(int[] A) {
		int[] digits = new int[32];
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < A.length; j++) {
				digits[i] += (A[j] >> i) & 1;
			}
		}

		int result = 0;
		for (int i = 31; i >= 0; i--) {
			if (digits[i] % 3 != 0)
				result += 1;

			if (i != 0)
				result <<= 1;
		}
		return result;
	}
}
