/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> parentStack = new Stack<TreeNode>();
		parentStack.push(root);
		while (!parentStack.isEmpty()) {
			TreeNode curr = parentStack.pop();
			if (curr != null) {
				result.add(curr.val);
				parentStack.push(curr.right);
				parentStack.push(curr.left);
			}
		}
		return result;
	}
}

class Solution2 {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		doPreorderTraversal(root, result);
		return result;
	}

	private void doPreorderTraversal(TreeNode root, ArrayList<Integer> result) {
		if (root == null)
			return;

		result.add(root.val);
		doPreorderTraversal(root.left, result);
		doPreorderTraversal(root.right, result);
	}
}
