package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution7 {
    public static void main(String[] args) {
        System.out.println(isOneBitCharacter3(new int[]{1, 0, 0}));
    }

    /**
     * 693. 交替位二进制数
     * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：
     * 换句话说，就是二进制表示中相邻两位的数字永不相同。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 5
     * 输出：true
     * 解释：5 的二进制表示是：101
     * 示例 2：
     * <p>
     * 输入：n = 7
     * 输出：false
     * 解释：7 的二进制表示是：111.
     * 示例 3：
     * <p>
     * 输入：n = 11
     * 输出：false
     * 解释：11 的二进制表示是：1011.
     * 示例 4：
     * <p>
     * 输入：n = 10
     * 输出：true
     * 解释：10 的二进制表示是：1010.
     * 示例 5：
     * <p>
     * 输入：n = 3
     * 输出：false
     *
     * @param n
     * @return
     */
    public static boolean hasAlternatingBits(int n) {
        while (n > 0) {
            int pop = n % 2;
            n /= 2;
            if (pop == n % 2) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasAlternatingBits2(int n) {
        String str = String.valueOf(Integer.toBinaryString(n));
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = i + 1;
            if (j < chars.length) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 696. 计数二进制子串
     * 给定一个字符串 s，计算具有相同数量 0 和 1 的非空（连续）子字符串的数量，
     * 并且这些子字符串中的所有 0 和所有 1 都是连续的。
     * <p>
     * 重复出现的子串要计算它们出现的次数。
     * <p>
     * <p>
     * <p>
     * 示例 1 :
     * <p>
     * 输入: "00110011" {2,2,2,2} "111000111000" {3333} = 9
     * 输出: 6
     * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     * <p>
     * 请注意，一些重复出现的子串要计算它们出现的次数。
     * <p>
     * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
     * 示例 2 :
     * <p>
     * 输入: "10101" 11111
     * 输出: 4
     * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        List<Integer> counts = new ArrayList<Integer>();
        int ptr = 0, n = s.length();
        char[] chars = s.toCharArray();
        // 11001100
        while (ptr < n) {
            char c = chars[ptr];
            int count = 0;
            while (ptr < n && chars[ptr] == c) {
                ++ptr;
                ++count;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); ++i) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }
        return ans;
    }

    /**
     * 比上面省去了一个 list空间长度
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings2(String s) {
        int ptr = 0, n = s.length(), last = 0, ans = 0;
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            ans += Math.min(count, last);
            last = count;
        }
        return ans;
    }

    /**
     * 697. 数组的度
     * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     * <p>
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2：
     * <p>
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     *
     * @param nums
     * @return
     */
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
     * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     * <p>
     * 例如，
     * <p>
     * 给定二叉搜索树:
     * <p>
     * 4
     * / \
     * 2   7
     * / \
     * 1   3
     * <p>
     * 和值: 2
     * 你应该返回如下子树:
     * <p>
     * 2
     * / \
     * 1   3
     * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        int nodeV = root.val;
        if (nodeV == val) {
            return root;
        }
        if (val < nodeV) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    /**
     * 迭代法
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }

    /**
     * 704. 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例 2:
     * <p>
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    /**
     * 709. 转换成小写字母
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，
     * 之后返回新的字符串。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: "Hello"
     * 输出: "hello"
     * 示例 2：
     * <p>
     * 输入: "here"
     * 输出: "here"
     * 示例 3：
     * <p>
     * 输入: "LOVELY"
     * 输出: "lovely"
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar >= 65 && aChar <= 90) {
                chars[i] = (char) (aChar + 32);
            }
        }
        return new String(chars);
    }

    public static String toLowerCase2(String str) {
        char[] chars = str.toCharArray();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar >= 'A' && aChar <= 'Z') {
                stb.append((char) (aChar - 'A' + 'a'));
            } else {
                stb.append(aChar);
            }
        }
        return stb.toString();
    }

    /**
     * 717. 1比特与2比特字符
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     * <p>
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * bits = [1, 0, 0]
     * 输出: True
     * 解释:
     * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
     * 示例 2:
     * <p>
     * 输入:
     * bits = [1, 1, 1, 0]
     * 输出: False
     * 解释:
     * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
     *
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter(int[] bits) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits.length; i++) {
            if (sb.toString().equals("10")
                    || sb.toString().equals("11")
                    || "0".equals(sb.toString())) {
                sb = new StringBuilder();
            }
            sb.append(bits[i]);
        }
        return "0".equals(sb.toString());
    }

    /**
     * 线性扫描
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter2(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            i += bits[i] + 1;
        }
        return i == bits.length - 1;
    }

    /**
     * 三种字符分别为 0，10 和 11，那么 \mathrm{bits}bits 数组中出现的所有 0
     * 都表示一个字符的结束位置（无论其为一比特还是两比特）。因此最后一位是否为一比特字符，
     * 只和他左侧出现的连续的 1 的个数（即它与倒数第二个 0 出现的位置之间的 1 的个数，
     * 如果 \mathrm{bits}bits 数组中只有 1 个 0，那么就是整个数组的长度减一）有关。
     * 如果 1 的个数为偶数个，那么最后一位是一比特字符，如果 1 的个数为奇数个，那么最后一位不是一比特字符
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/solution/1bi-te-yu-2bi-te-zi-fu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter3(int[] bits) {
        int i = bits.length - 2;
        while (i >= 0 && bits[i] > 0) i--;
        return (bits.length - i) % 2 == 0;
    }
}
