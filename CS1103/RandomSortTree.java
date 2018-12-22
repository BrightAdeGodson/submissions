/**  
 * Random binary sort tree contained 1023 random real numbers.
 * Outputs:
 * Number of leaves:         348
 * Average depth of leaves:  12.350574712643677
 * Maximum depth of leaves:  21
 * 
 */
public class RandomSortTree {
	// Pointer to the binary sort tree.
	static TreeNode root;

	/**
	 * TreeNode represents one node in a binary tree of real numbers.
	 */
	static class TreeNode {
		double item;
		TreeNode left;
		TreeNode right;
		TreeNode(double x) {
			item = x;
		}
	} 

	/**
	 * This method is copied from Section 9.4.2 in the textbook.
	 * Add x to the binary sort tree to which the global variable "root" refers.
	 */
	static void treeInsert(double x) 
	{
		if ( root == null ) 
		{
			// The tree is empty.  Set root to point to a new node 
			// containing the new item.
			root = new TreeNode( x );
			return;
		}
		TreeNode runner; // Runs down the tree to find a place for newItem.
		runner = root;   // Start at the root.
		while (true) {
			if ( x < runner.item ) 
			{
				// Since the new item is less than the item in runner,
				// it belongs in the left subtree of runner.  If there
				// is an open space at runner.left, add a node there.
				// Otherwise, advance runner down one level to the left.
				if ( runner.left == null ) {
					runner.left = new TreeNode( x );
					return;  // New item has been added to the tree.
				}
				else
					runner = runner.left;
			}
			else 
			{
				// Since the new item is greater than or equal to the 
				// item in runner, it belongs in the right subtree of
				// runner.  If there is an open space at runner.right, 
				// add a new node there.  Otherwise, advance runner
				// down one level to the right.
				if ( runner.right == null ) 
				{
					runner.right = new TreeNode( x );
					return;  // New item has been added to the tree.
				}
				else
					runner = runner.right;
			}
		} 
		// end while
	} 
	// end treeInsert()


	/**
	 * countLeaves returns number of leaves in the tree to which node points.
	 * @return number of leaves.
	 */
	static int countLeaves(TreeNode node) {
		if (node == null) {
			return 0;
		} else if (node.left == null && node.right == null) {
			// Node is a leaf.
			return 1;
		} else { 
			// Recurse with leaves.
			return countLeaves(node.left) + countLeaves(node.right);
		}
	}

	/**
	 * sumOfLeafDepths returns number of leaf depths.
	 * @param TreeNode and current depth. main() calls it with root, 0 as the beginning of tree.
	 * @return sum of leaf depths.
	 */   
	static int sumOfLeafDepths(TreeNode node, int depth) {
		if (node == null) {
			return 0;
		} else if (node.left == null && node.right == null) {
			// The node is a leaf, and there are no subtrees of node.
			// Return the depths of this node.
			return depth;
		} else {
			// The node is not a leaf.
			// Return the leaves in the subtrees.
			return sumOfLeafDepths(node.left, depth + 1) + sumOfLeafDepths(node.right, depth + 1);
		}
	}


	/**
	 * maximumLeafDepth returns maximum number of leaf depth.
	 * @param TreeNode and current depth. main() calls it with root, 0 as the beginning of tree.
	 * @return maximum number of leaf depth.
	 */
	static int maximumLeafDepth(TreeNode node, int depth) {
		if (node == null) {
			return 0;
		} else if (node.left == null && node.right == null) {
			// The node is a leaf, and there are no subtrees of node.
			// Return the depth of this node as maximum depth.
			return depth;
		} else {
			// Get the maximum depths for the two subtrees of this node.
			// Return the larger of the two values which represents the maximum in the tree overall.
			int leftMax = maximumLeafDepth(node.left, depth + 1);
			int rightMax =  maximumLeafDepth(node.right, depth + 1);
			if (leftMax > rightMax) {
				return leftMax;
			} else {
				return rightMax;
			}
		}
	}

	public static void main(String[] args) {
		root = null;

		// Insert 1023 random items.
		for (int i = 0; i < 1023; i++) {
			treeInsert(Math.random()); 
		}

		int leafCount = countLeaves(root);
		int depthSum = sumOfLeafDepths(root, 0);
		int depthMax = maximumLeafDepth(root, 0);
		double averageDepth = ((double)depthSum) / leafCount;

		System.out.println("Number of leaves:         " + leafCount);
		System.out.println("Average depth of leaves:  " + averageDepth);
		System.out.println("Maximum depth of leaves:  " + depthMax);
	}
}