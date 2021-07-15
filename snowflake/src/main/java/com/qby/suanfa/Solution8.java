package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

import java.util.*;

public class Solution8 {
    public static void main(String[] args) {
        // "this apple is sweet"
        // "this apple is sour"
        System.out.println(uncommonFromSentences("this apple is sweet", "this apple is sour"));
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
        List<Integer> seq1 = new ArrayList<Integer>();
        if (root1 != null) {
            dfs(root1, seq1);
        }

        List<Integer> seq2 = new ArrayList<Integer>();
        if (root2 != null) {
            dfs(root2, seq2);
        }

        return seq1.equals(seq2);
    }

    public static void dfs(TreeNode treeNode, List<Integer> strb) {
        if (treeNode.left == null && treeNode.right == null) {
            strb.add(treeNode.val);
        }
        if (treeNode.left != null) {
            dfs(treeNode.left, strb);
        }
        if (treeNode.right != null) {
            dfs(treeNode.right, strb);
        }
    }

    /**
     * 876. 链表的中间结点
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast == null) {
            return slow;
        } else if (fast.next == null) {
            return slow.next;
        } else {
            return slow;
        }
    }

    public ListNode middleNode2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 883. 三维形体投影面积
     * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
     * <p>
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
     * <p>
     * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
     * <p>
     * 投影就像影子，将三维形体映射到一个二维平面上。
     * <p>
     * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
     * <p>
     * 返回所有三个投影的总面积。
     *
     * @param grid
     * @return
     */
    public int projectionArea(int[][] grid) {
        int N = grid.length;
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            int bestRow = 0;  // largest of grid[i][j]
            int bestCol = 0;  // largest of grid[j][i]
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] > 0) ans++;  // top shadow
                bestRow = Math.max(bestRow, grid[i][j]);
                bestCol = Math.max(bestCol, grid[j][i]);
            }
            ans += bestRow + bestCol;
        }

        return ans;
    }

    /**
     *
     * 884. 两句话中的不常见单词
     * 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
     *
     * 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
     *
     * 返回所有不常用单词的列表。
     *
     * 您可以按任何顺序返回列表。
     *
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String[] uncommonFromSentences(String s1, String s2) {
        List<String> rList = new ArrayList();
        String[] sz1 = s1.split(" ");
        String[] sz2 = s2.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String s : sz1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : sz2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        Set<String> strings = map.keySet();
        for (String key : strings) {
            if (map.get(key).intValue() == 1) {
                rList.add(key);
            }
        }

        return rList.toArray(new String[rList.size()]);
    }


    /**
     * 888. 公平的糖果棒交换
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
     *
     * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     *
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     *
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
     *
     *
     * @param aliceSizes
     * @param bobSizes
     * @return
     */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int[] ans = new int[2];

        return ans;
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     *
     * 如果按照列来计算的话，宽度一定是1了，我们再把每一列的雨水的高度求出来就可以了。
     * 双指针法
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            // 第一个柱子和最后一个柱子不接雨水
            if (i == 0 || i == height.length - 1) {
                continue;
            }
            // 在for循环中求左右两边最高柱子
            int lHeight = height[i];
            int rHeight = height[i];
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > rHeight) rHeight = height[j];
            }
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > lHeight) lHeight = height[j];
            }
            int h = Math.min(lHeight, rHeight) - height[i];
            if (h > 0) sum += h;
        }

        return sum;
    }

    /**
     * 动态规划法
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int sum = 0;
        if (height.length <= 2) return 0;
        int size = height.length;
        int[] maxLeft = new int[size];
        int[] maxRight = new int[size];

        // 记录每个柱子左边的最大高度
        maxLeft[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        // 记录每个柱子右边的最大高度
        maxRight[size - 1] = height[size - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        for (int i = 0; i < height.length; i++) {
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (count > 0) sum += count;
        }

        return sum;
    }
}
