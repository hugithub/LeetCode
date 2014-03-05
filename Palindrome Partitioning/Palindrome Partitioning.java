public class Solution {
	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (s.isEmpty()) {
			result.add(new ArrayList<String>());
			return result;
		}

		for (int i = 1; i <= s.length(); i++) {
			String head = s.substring(0, i);
			StringBuilder buffer = new StringBuilder(head);
			String reversed = buffer.reverse().toString();
			if (head.equals(reversed)) {
				ArrayList<ArrayList<String>> subResult = partition(s.substring(i, s.length()));
				for (int j = 0; j < subResult.size(); j++) {
					ArrayList<String> newPartition = new ArrayList<String>();
					newPartition.add(head);
					newPartition.addAll(subResult.get(j));
					result.add(newPartition);
				}
			}
		}
		return result;
	}
}

class Solution2 {
	public ArrayList<ArrayList<String>> partition(String s) {
		boolean[][] memo = new boolean[s.length()][s.length()];
		memo[s.length() - 1][s.length() - 1] = true;
		for (int i = 0; i < s.length() - 1; i++) {
			memo[i][i] = true;
			memo[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
		}
		for (int i = 2; i < s.length(); i++) {
			for (int j = i - 2; j >= 0; j--) {
				memo[j][i] = (s.charAt(j) == s.charAt(i)) && memo[j + 1][i - 1];
			}
		}

		return doPartition(s, 0, memo);
	}

	private ArrayList<ArrayList<String>> doPartition(String s, int start, boolean[][] memo) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (start >= s.length()) {
			result.add(new ArrayList<String>());
			return result;
		}

		for (int i = start; i < s.length(); i++) {
			if (memo[start][i]) {
				ArrayList<ArrayList<String>> subResult = doPartition(s, i + 1, memo);
				for (int j = 0; j < subResult.size(); j++) {
					ArrayList<String> newPartition = new ArrayList<String>();
					newPartition.add(s.substring(start, i + 1));
					newPartition.addAll(subResult.get(j));
					result.add(newPartition);
				}
			}
		}
		return result;
	}
}
