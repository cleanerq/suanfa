package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

public class Solution8 {
    public static void main(String[] args) {
        //        System.out.println(binaryGap2(1));
        //        [3,5,1,6,2,9,8,null,null,7,14]
        //      [3,5,1,6,71,4,2,null,null,null,null,null,null,9,8]
        TreeNode root1 = TreeNode.makeTreeNode(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 14});
        TreeNode root2 = TreeNode.makeTreeNode(new Integer[]{3, 5, 1, 6, 71, 4, 2, null, null, null, null, null, null, 9, 8});
        System.out.println(leafSimilar(root1, root2));
    }

    /**
     * 868. 二进制间距
     * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。
     * 如果不存在两个相邻的 1，返回 0 。
     * <p>
     * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。
     * 两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。
     *
     * @param n
     * @return
     */
    public static int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        char[] charArray = s.toCharArray();
        int count = 0;
        int max = 0;

        char pre = '9';
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                max = Math.max(max, count);
                count = 1;
                pre = charArray[i];
            } else if (pre == '1') {
                if (charArray[i] == '0') {
                    count++;
                }
            } else if (pre == '9') {
                count = 0;
            }
        }
        return max;
    }

    /**
     * 移位法
     *
     * @param N
     * @return
     */
    public static int binaryGap2(int N) {
        int last = -1, ans = 0;
        for (int i = 0; i < 32; ++i)
            if (((N >> i) & 1) > 0) {
                if (last >= 0)
                    ans = Math.max(ans, i - last);
                last = i;
            }
        return ans;
    }

    /**
     * 872. 叶子相似的树
     * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
     * <p>
     * 举个例子，如上图所示，给定一棵叶值序列为(6, 7, 4, 9, 8)的树。
     * <p>
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是叶相似的。
     * <p>
     * 如果给定的两个根结点分别为root1 和root2的树是叶相似的，则返回true；否则返回 false 。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/leaf-similar-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * [3,5,1,6,2,9,8,null,null,7,14]
     * [3,5,1,6,71,4,2,null,null,null,null,null,null,9,8]
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder strb1 = new StringBuilder();
        StringBuilder strb2 = new StringBuilder();
        leafDfs(root1, strb1);
        leafDfs(root2, strb2);

        return strb1.toString().equals(strb2.toString());
    }

    public static void leafDfs(TreeNode treeNode, StringBuilder strb) {
        if (treeNode == null) {
            return;
        }
        leafDfs(treeNode.left, strb);
        leafDfs(treeNode.right, strb);
        if (treeNode.left == null && treeNode.right == null) {
            strb.append(treeNode.val);
        }
    }
}
