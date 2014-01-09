public class Solution {
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] S) {
		Arrays.sort(S);
		return subSubsets(S, 0);
	}

	private ArrayList<ArrayList<Integer>> subSubsets(int[] S, int start) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> emptyRow = new ArrayList<Integer>();
		result.add(emptyRow);

		if (start >= S.length)
			return result;

		for (int i = start; i < S.length; i++) {
			ArrayList<ArrayList<Integer>> subResult = subSubsets(S, i + 1);
			for (ArrayList<Integer> row : subResult) {
				row.add(S[i]);
				Collections.sort(row);
				result.add(row);
			}

			while (i < S.length - 1 && S[i] == S[i + 1])
				i++;
		}
		return result;
	}
}