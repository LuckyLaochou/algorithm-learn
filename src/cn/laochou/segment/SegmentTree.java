package cn.laochou.segment;

/**
 * 线段树模板
 * @author laochou
 */
public class SegmentTree {

    TreeNode root;

    // 线段树模板
    public SegmentTree(int n) {
        root = new TreeNode();
    }

    public int query(int l, int r, TreeNode node, int s, int t) {
        if(l <= s && r >= t) {
            return node.val;
        }
        int mid = (s + t) / 2;
        int result = 0;
        pushDown(node, mid - s + 1, t - mid);
        if(r > mid) {
            result += query(l, r, node.right, mid + 1, t);
        }
        if(l <= mid) {
            result += query(l, r, node.left, s, mid);
        }
        return result;
    }

    public void update(int l, int r, TreeNode node, int s, int t, int val) {
        // l,r 为更新区间
        if(l <= s && r >= t) {
            node.val = (s - t + 1) * val;
            node.lazy += val;
            return;
        }
        int mid = (s + t) / 2;
        pushDown(node, mid - s + 1, t - mid);
        if(r > mid) {
            update(l, r, node.right, mid + 1, t, val);
        }
        if(l <= mid) {
            update(l, r, node.left, s, mid, val);
        }
        pushUp(node);
    }


    public void pushDown(TreeNode node, int leftNum, int rightNum) {
        if(node.left == null) node.left = new TreeNode();
        if(node.right == null) node.right = new TreeNode();
        if(node.lazy == 0) return;
        node.left.val += (leftNum) * node.lazy;
        node.right.val += (rightNum) * node.lazy;
        node.left.lazy = node.lazy;
        node.right.lazy = node.lazy;
        node.lazy = 0;
    }

    public void pushUp(TreeNode node) {
        node.val = node.left.val + node.right.val;
    }

}


class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    int lazy;

    TreeNode() {
    }

}
