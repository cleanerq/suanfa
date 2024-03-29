package com.qby.suanfa;

import com.qby.suanfa.basic.Employee;
import com.qby.suanfa.basic.Node;
import com.qby.suanfa.basic.TreeNode;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Solution6 {

    /**
     * 492. 构造矩形
     * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，
     * 你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
     * <p>
     * 1. 你设计的矩形页面必须等于给定的目标面积。
     * <p>
     * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
     * <p>
     * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
     * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
     * <p>
     * 示例：
     * <p>
     * 输入: 4
     * 输出: [2, 2]
     * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
     * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
     *
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        if (sqrt * sqrt == area) {
            return new int[]{sqrt, sqrt};
        }
        for (int i = sqrt; i >= 1; i--) {
            if (area % i == 0) {
                return new int[]{area / i, i};
            }
        }
        return null;
    }

    /**
     * 496. 下一个更大元素 I
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     * <p>
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * <p>
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。
     * 如果不存在，对应位置输出 -1 。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
     * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
     * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     * 示例 2:
     * <p>
     * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出: [3,-1]
     * 解释:
     * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
     * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     * <p>
     * 方法二：栈（单调栈）
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 先处理 nums2，把对应关系存入哈希表
        for (int i = 0; i < len2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                map.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }

        // 遍历 nums1 得到结果集
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    /**
     * 500. 键盘行
     * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
     * <p>
     * 美式键盘 中：
     * <p>
     * 第一行由字符 "qwertyuiop" 组成。
     * 第二行由字符 "asdfghjkl" 组成。
     * 第三行由字符 "zxcvbnm" 组成。
     * American keyboard
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：words = ["Hello","Alaska","Dad","Peace"]
     * 输出：["Alaska","Dad"]
     * 示例 2：
     * <p>
     * 输入：words = ["omk"]
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：words = ["adsdf","sfd"]
     * 输出：["adsdf","sfd"]
     *
     * @param words
     * @return
     */
    public static String[] findWords(String[] words) {
        String[] pad = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pad.length; i++) {
            char[] chars = pad[i].toCharArray();
            for (char aChar : chars) {
                map.put(aChar, i + 1);
            }
        }

        String[] res = new String[words.length];
        int j = 0;
        int line = 0;
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = Character.toLowerCase(chars[i]);
                Integer mline = map.get(c);

                if (i == 0) {
                    line = mline;
                }

                if (!map.containsKey(c) || line != mline.intValue()) {
                    break;
                }

                if (i == chars.length - 1) {
                    res[j++] = word;
                }
            }
        }
        return Arrays.copyOf(res, j);
    }

    /**
     * 501. 二叉搜索树中的众数
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     * <p>
     * 假定 BST 有如下定义：
     * <p>
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     * 例如：
     * 给定 BST [1,null,2,2],
     * <p>
     * 1
     * \
     * 2
     * /
     * 2
     * 返回[2].
     *
     * @param root
     * @return
     */
    List<Integer> answer = new ArrayList<Integer>();
    int base, count, maxCount;

    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void dfs(TreeNode o) {
        if (o == null) {
            return;
        }
        dfs(o.left);
        update(o.val);
        dfs(o.right);
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

    /**
     * Morris 中序遍历的一个重要步骤就是寻找当前节点的前驱节点，并且 Morris 中序遍历寻找下一个点始终是通过转移到 \textit{right}right 指针指向的位置来完成的。
     * <p>
     * 如果当前节点没有左子树，则遍历这个点，然后跳转到当前节点的右子树。
     * 如果当前节点有左子树，那么它的前驱节点一定在左子树上，我们可以在左子树上一直向右行走，找到当前点的前驱节点。
     * 如果前驱节点没有右子树，就将前驱节点的 \textit{right}right 指针指向当前节点。这一步是为了在遍历完前驱节点后能找到前驱节点的后继，也就是当前节点。
     * 如果前驱节点的右子树为当前节点，说明前驱节点已经被遍历过并被修改了 \textit{right}right 指针，这个时候我们重新将前驱的右孩子设置为空，遍历当前的点，然后跳转到当前节点的右子树。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-zhong-shu-by-leetcode-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public int[] findMode2(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    /**
     * 504. 七进制数
     * 给定一个整数，将其转化为7进制，并以字符串形式输出。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 100
     * 输出: "202"
     * 示例 2:
     * <p>
     * 输入: -7
     * 输出: "-10"
     *
     * @param num
     * @return
     */
    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder st = new StringBuilder();
        boolean zs = num > 0 ? true : false;
        int mod = 0;
        while (num != 0) {
            mod = num % 7;
            st.append(Math.abs(mod));
            num = num / 7;
        }

        if (!zs) {
            st.append("-");
        }

        return st.reverse().toString();
    }

    public String convertToBase7_2(int num) {
        return num >= 0 ? Integer.toUnsignedString(num, 7) : "-" + Integer.toUnsignedString(num * -1, 7);
    }


    /**
     * 506. 相对名次
     * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌”
     * 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
     * <p>
     * (注：分数越高的选手，排名越靠前。)
     * <p>
     * 示例 1:
     * <p>
     * 输入: [5, 4, 3, 2, 1]
     * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
     * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
     * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
     * <p>
     * 首先寻找数组中最大的值（成绩最高的），创建一个 int[] array = new int[max + 1];
     * 的数组用来实现计数排序。array 数组的下标对应成绩，值为该成绩所在的原数组的下标。
     * 由于 array 数组的值默认为 0，所以在存储成绩的下标时，应对下标加 1，取时减 1 即可。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了89.32%的用户
     * <p>
     * 作者：yixingzhang
     * 链接：https://leetcode-cn.com/problems/relative-ranks/solution/san-chong-jie-fa-ji-shu-pai-xu-2-ms10000-ml6v/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static String[] findRelativeRanks(int[] nums) {
        int n = nums.length;
        String[] result = new String[n];
        int max = 0;
        // 找出找出最高的成绩
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
        }
        // 下标为成绩，值为成绩在 nums 数组的下标
        int[] array = new int[max + 1];
        for (int i = 0; i < n; i++) {
            array[nums[i]] = i + 1;
        }
        // 记录当前成绩的排名
        int count = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != 0) {
                // 根据排名进行赋值
                switch (count) {
                    case 1:
                        result[array[i] - 1] = "Gold Medal";
                        break;
                    case 2:
                        result[array[i] - 1] = "Silver Medal";
                        break;
                    case 3:
                        result[array[i] - 1] = "Bronze Medal";
                        break;
                    default:
                        result[array[i] - 1] = String.valueOf(count);
                }
                count++;
            }
        }
        return result;
    }

    /**
     * java api法
     *
     * @param nums
     * @return
     */
    public String[] findRelativeRanks2(int[] nums) {
        int n = nums.length;
        String[] result = new String[n];
        // key 为成绩，value 为成绩在数组中的下标，TreeMap 是按照升序进行排序的
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            // 计算成绩的排名
            int ranking = n - count++;
            switch (ranking) {
                case 1:
                    result[set.getValue()] = "Gold Medal";
                    break;
                case 2:
                    result[set.getValue()] = "Silver Medal";
                    break;
                case 3:
                    result[set.getValue()] = "Bronze Medal";
                    break;
                default:
                    result[set.getValue()] = String.valueOf(ranking);
            }
        }
        return result;
    }

    /**
     * 509. 斐波那契数
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * <p>
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     * <p>
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     * <p>
     * 输入：4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a1 = 0;
        int a2 = 1;
        int a3 = 0;
        for (int i = 2; i <= n; i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    /**
     * 520. 检测大写字母
     * 给定一个单词，你需要判断单词的大写使用是否正确。
     * <p>
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * <p>
     * 全部字母都是大写，比如"USA"。
     * 单词中所有字母都不是大写，比如"leetcode"。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     * 否则，我们定义这个单词没有正确使用大写字母。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "USA"
     * 输出: True
     * 示例 2:
     * <p>
     * 输入: "FlaG"
     * 输出: False
     * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
     *
     * @param word
     * @return
     */
    public static boolean detectCapitalUse(String word) {
        char[] check = new char[128];
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            check[chars[i]]++;
        }
        int count = 0;
        for (int i = 65; i <= 90; i++) {
            if (check[i] >= 1) {
                count += check[i];
            }
        }
        boolean flg = false;
        if (count == chars.length || count == 0) {
            flg = true;
        } else if (count == 1 && chars[0] >= 65 && chars[0] <= 90) {
            flg = true;
        }

        return flg;
    }

    public boolean detectCapitalUse2(String word) {
        boolean res = false;
        int count = 0;
        char x;
        for (int i = 0; i < word.length(); i++) {
            x = word.charAt(i);
            if (x >= 65 && x <= 90) {
                count++;
            }
        }
        if ((count == 1 && (word.charAt(0) >= 65 && word.charAt(0) <= 90)) || (count == word.length())) {
            res = true;
        } else if (count == 0) {
            res = true;
        }
        return res;
    }

    /**
     * 521. 最长特殊序列 Ⅰ
     * 给你两个字符串，请你从这两个字符串中找出最长的特殊序列。
     * <p>
     * 「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     * <p>
     * 子序列 可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     * <p>
     * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: "aba", "cdc"
     * 输出: 3
     * 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
     * 示例 2：
     * <p>
     * 输入：a = "aaa", b = "bbb"
     * 输出：3
     * 示例 3：
     * <p>
     * 输入：a = "aaa", b = "aaa"
     * 输出：-1
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：
     * <p>
     * 1
     * \
     * 3
     * /
     * 2
     * <p>
     * 输出：
     * 1
     * <p>
     * 解释：
     * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     *
     * @param root
     * @return
     */
    int pre;
    int ans;

    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs2(root);
        return ans;
    }

    public void dfs2(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs2(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs2(root.right);
    }


    /**
     * 541. 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     * <p>
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * <p>
     * <p>
     * 示例:
     * <p>
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacdfeg"
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }

    /**
     * 543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
     * 这条路径可能穿过也可能不穿过根结点。
     * <p>
     * <p>
     * <p>
     * 示例 :
     * 给定二叉树
     * <p>
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

    /**
     * 551. 学生出勤记录 I
     * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
     * <p>
     * 'A' : Absent，缺勤
     * 'L' : Late，迟到
     * 'P' : Present，到场
     * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
     * <p>
     * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "PPALLP"
     * 输出: True
     * 示例 2:
     * <p>
     * 输入: "PPALLL"
     * 输出: False
     *
     * @param s
     * @return
     */
    public static boolean checkRecord(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++)
            if (chars[i] == 'A')
                count++;
        return count < 2 && s.indexOf("LLL") < 0;
    }

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            j = i;
            if (Character.isWhitespace(chars[i])) {
                continue;
            } else {
                // 不是空格时
                while (j < chars.length && !Character.isWhitespace(chars[j])) {
                    j++;
                }
                int l = i, h = j - 1;
                i = j - 1;
                while (l < h) {
                    char tmp = chars[l];
                    chars[l++] = chars[h];
                    chars[h--] = tmp;
                }
            }
        }
        return new String(chars);
    }

    public String reverseWords2(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    /**
     * 559. N 叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     * <p>
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * <p>
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：3
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：5
     * 迭代方法
     *
     * @param root
     * @return
     */
    public int maxDepth2(Node root) {
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.poll();
            root = current.getKey();
            int currentDepth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                for (Node c : root.children) {
                    stack.add(new Pair(c, currentDepth + 1));
                }
            }
        }
        return depth;
    }

    /**
     * 递归方法
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> heights = new LinkedList<>();
            for (Node item : root.children) {
                heights.add(maxDepth(item));
            }
            return Collections.max(heights) + 1;
        }
    }

    /**
     * 561. 数组拆分 I
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 返回该 最大总和 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,4,3,2]
     * 输出：4
     * 解释：所有可能的分法（忽略元素顺序）为：
     * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * 所以最大总和为 4
     * 示例 2：
     * <p>
     * 输入：nums = [6,2,6,5,1,2]
     * 输出：9
     * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
     *
     * @param nums
     * @return
     */
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum = sum + nums[i];
        }
        return sum;
    }

    /**
     * 563. 二叉树的坡度
     * 给定一个二叉树，计算 整个树 的坡度 。
     * <p>
     * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
     * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
     * <p>
     * 整个树 的坡度就是其所有节点的坡度之和。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [1,2,3]
     * 输出：1
     * 解释：
     * 节点 2 的坡度：|0-0| = 0（没有子节点）
     * 节点 3 的坡度：|0-0| = 0（没有子节点）
     * 节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
     * 坡度总和：0 + 0 + 1 = 1
     * 示例 2：
     * <p>
     * <p>
     * 输入：root = [4,2,9,3,5,null,7]
     * 输出：15
     * 解释：
     * 节点 3 的坡度：|0-0| = 0（没有子节点）
     * 节点 5 的坡度：|0-0| = 0（没有子节点）
     * 节点 7 的坡度：|0-0| = 0（没有子节点）
     * 节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
     * 节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
     * 节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，
     * 和是 16 ）
     * 坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
     * 示例 3：
     * <p>
     * <p>
     * 输入：root = [21,7,14,1,1,2,2,3,3]
     * 输出：9
     *
     * @param root
     * @return
     */
    int tilt = 0;

    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }

    public int traverse(TreeNode root) {
        if (root == null)
            return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt += Math.abs(left - right);
        return left + right + root.val;
    }

    /**
     * 566. 重塑矩阵
     * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     * <p>
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     * <p>
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     * <p>
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * nums =
     * [[1,2],
     * [3,4]]
     * r = 1, c = 4
     * 输出:
     * [[1,2,3,4]]
     * 解释:
     * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
     * 示例 2:
     * <p>
     * 输入:
     * nums =
     * [[1,2],
     * [3,4]]
     * r = 2, c = 4
     * 输出:
     * [[1,2],
     * [3,4]]
     * 解释:
     * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
     *
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int newM = r * c;
        int oriM = mat[0].length * mat.length;
        if (newM != oriM) {
            return mat;
        }

        // 转换后的新矩阵
        int[][] newH = new int[r][c];
        int h = 0;
        int l = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (h < r && l < c) {
                    newH[h][l] = mat[i][j];
                }
                l++;
                if (l >= c) {
                    // 如果列大于列数 则行数加1
                    h++;
                    l = 0;
                }
            }
        }

        for (int i = 0; i < newH.length; i++) {
            for (int j = 0; j < newH[i].length; j++) {
                System.out.println(newH[i][j]);
            }
        }
        return newH;
    }

    /**
     * 对于一个行数为 mm，列数为 nn，行列下标都从 00 开始编号的二维数组，我们可以通过下面的方式，
     * 将其中的每个元素 (i, j)(i,j) 映射到整数域内，并且它们按照行优先的顺序一一对应着 [0, mn)[0,mn)
     * 中的每一个整数。形象化地来说，我们把这个二维数组「排扁」成了一个一维数组。如果读者对机器学习有一定了解，
     * 可以知道这就是 flatten 操作。
     * <p>
     * 这样的映射即为：
     * <p>
     * (i, j) \to i \times n+j
     * (i,j)→i×n+j
     * <p>
     * 同样地，我们可以将整数 xx 映射回其在矩阵中的下标，即
     * <p>
     * \begin{cases} i = x ~/~ n \\ j = x ~\%~ n \end{cases}
     * {
     * i=x/n
     * j=x%n
     * <p>
     * <p>
     * <p>
     * 其中 // 表示整数除法，\%% 表示取模运算。
     * <p>
     * 那么题目需要我们做的事情相当于：
     * <p>
     * 将二维数组 \textit{nums}nums 映射成一个一维数组；
     * <p>
     * 将这个一维数组映射回 rr 行 cc 列的二维数组。
     * <p>
     * 我们当然可以直接使用一个一维数组进行过渡，但我们也可以直接从二维数组 \textit{nums}nums 得到 rr 行 cc 列的重塑矩阵：
     * <p>
     * 设 \textit{nums}nums 本身为 mm 行 nn 列，如果 mn \neq rcmn
     * 
     * <p>
     * =rc，那么二者包含的元素个数不相同，因此无法进行重塑；
     * <p>
     * 否则，对于 x \in [0, mn)x∈[0,mn)，第 xx 个元素在 \textit{nums}nums 中对应的下标为 (x ~/~ n, x~\%~ n)(x/n,x%n)，而在新的重塑矩阵中对应的下标为 (x ~/~ c, x~\%~ c)(x/c,x%c)。我们直接进行赋值即可。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reshape-the-matrix/solution/zhong-su-ju-zhen-by-leetcode-solution-gt0g/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 行数为 m，列数为 n
     * (i,j)→i×n+j
     * <p>
     * i=x / n
     * j=x % n
     * <p>
     * 其中 / 表示整数除法，\% 表示取模运算。
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }
        // x = i×n+j
        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = nums[x / n][x % n];
        }
        return ans;
    }

    /**
     * 572. 另一个树的子树
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     * <p>
     * 示例 1:
     * 给定的树 s:
     * <p>
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * 给定的树 t：
     * <p>
     * 4
     * / \
     * 1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     * <p>
     * 示例 2:
     * 给定的树 s：
     * <p>
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * /
     * 0
     * 给定的树 t：
     * <p>
     * 4
     * / \
     * 1   2
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    public boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }


    List<Integer> sOrder = new ArrayList<Integer>();
    List<Integer> tOrder = new ArrayList<Integer>();
    int maxElement, lNull, rNull;

    /**
     * 深度优先搜索序列上做串匹配
     * 假设 ss 由两个点组成，11 是根，22 是 11 的左孩子；tt 也由两个点组成，11 是根，22 是 11 的右孩子。
     * 这样一来 ss 和 tt 的深度优先搜索序列相同，可是 tt 并不是 ss 的某一棵子树。
     * 由此可见「ss 的深度优先搜索序列包含 tt 的深度优先搜索序列」是「tt 是 ss 子树」的必要不充分条件，
     * 所以单纯这样做是不正确的。
     * <p>
     * 为了解决这个问题，我们可以引入两个空值 lNull 和 rNull，当一个节点的左孩子或者右孩子为空的时候，
     * 就插入这两个空值，这样深度优先搜索序列就唯一对应一棵树。处理完之后，
     * 就可以通过判断「ss 的深度优先搜索序列包含 tt 的深度优先搜索序列」来判断答案。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        maxElement = Integer.MIN_VALUE;
        getMaxElement(s);
        getMaxElement(t);
        lNull = maxElement + 1;
        rNull = maxElement + 2;

        getDfsOrder(s, sOrder);
        getDfsOrder(t, tOrder);

        return kmp();
    }

    public void getMaxElement(TreeNode t) {
        if (t == null) {
            return;
        }
        maxElement = Math.max(maxElement, t.val);
        getMaxElement(t.left);
        getMaxElement(t.right);
    }

    public void getDfsOrder(TreeNode t, List<Integer> tar) {
        if (t == null) {
            return;
        }
        tar.add(t.val);
        if (t.left != null) {
            getDfsOrder(t.left, tar);
        } else {
            tar.add(lNull);
        }
        if (t.right != null) {
            getDfsOrder(t.right, tar);
        } else {
            tar.add(rNull);
        }
    }

    public boolean kmp() {
        int sLen = sOrder.size(), tLen = tOrder.size();
        int[] fail = new int[tOrder.size()];
        Arrays.fill(fail, -1);
        for (int i = 1, j = -1; i < tLen; ++i) {
            while (j != -1 && !(tOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (tOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            fail[i] = j;
        }
        for (int i = 0, j = -1; i < sLen; ++i) {
            while (j != -1 && !(sOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (sOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            if (j == tLen - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 575. 分糖果
     * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。
     * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: candies = [1,1,2,2,3,3]
     * 输出: 3
     * 解析: 一共有三种种类的糖果，每一种都有两个。
     * 最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
     * 示例 2 :
     * <p>
     * 输入: candies = [1,1,2,3]
     * 输出: 2
     * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。
     * 这样使得妹妹可以获得的糖果种类数最多。
     * <p>
     * 排序法
     *
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        int m = 0;
        for (int i = 0; i < candyType.length; i++) {
            if (i == 0) {
                m++;
            } else if (candyType[i] != candyType[i - 1]) {
                m++;
            }
            if (m >= candyType.length / 2) {
                break;
            }
        }
        return m;
    }

    /**
     * 找到唯一元素数量的另一种方法是遍历给定 candiescandies 数组的所有元素，并继续将元素放入集合中。
     * 通过集合的属性，它将只包含唯一的元素。最后，我们可以计算集合中元素的数量，例如 countcount。
     * 要返回的值将再次由 \text{min}(count, n/2)min(count,n/2) 给出，如前面的方法所述。
     * 其中 nn 表示 candiescandies 数组的大小。
     *
     * @param candies
     * @return
     */
    public int distributeCandies2(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(), candies.length / 2);
    }


    /**
     * 589. N 叉树的前序遍历
     * 给定一个 N 叉树，返回其节点值的 前序遍历 。
     * <p>
     * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * <p>
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 递归法很简单，你可以使用迭代法完成此题吗?
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    /**
     * 深度优先前序遍历
     *
     * @param root
     * @param list
     */
    public void preorder(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.children != null && !root.children.isEmpty()) {
            for (Node child : root.children) {
                preorder(child, list);
            }
        }
    }

    /**
     * 使用迭代法 广度优先遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }

    /**
     * 590. N 叉树的后序遍历
     * 给定一个 N 叉树，返回其节点值的 后序遍历 。
     * <p>
     * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * <p>
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 递归法很简单，你可以使用迭代法完成此题吗?
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorderR(root, list);
        return list;
    }

    public void postorderR(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.children != null && !root.children.isEmpty()) {
            for (Node child : root.children) {
                postorderR(child, list);
            }
        }
        list.add(root.val);
    }

    /**
     * 迭代后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorder2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return output;
    }

    /**
     * 594. 最长和谐子序列
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     * <p>
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     * <p>
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,3,2,2,5,2,3,7]
     * 输出：5
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：2
     * 示例 3：
     * <p>
     * 输入：nums = [1,1,1,1]
     * 输出：0
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i])
                    count++;
                else if (nums[j] + 1 == nums[i]) {
                    count++;
                    flag = true;
                }
            }
            if (flag)
                res = Math.max(count, res);
        }
        return res;
    }

    /**
     * 方法二：哈希映射
     * 在方法一中，我们枚举了 x 后，遍历数组找出所有的 x 和 x + 1。我们也可以用一个哈希映射（HashMap）
     * 来存储每个数出现的次数，这样就能在 O(1)O(1) 的时间内得到 x 和 x + 1 出现的次数。
     * <p>
     * 我们首先遍历一遍数组，得到哈希映射。随后遍历哈希映射，设当前遍历到的键值对为 (x, value)，
     * 那么我们就查询 x + 1 在哈希映射中对应的值，就得到了 x 和 x + 1 出现的次数。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence/solution/zui-chang-he-xie-zi-xu-lie-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findLHS2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1))
                res = Math.max(res, map.get(key) + map.get(key + 1));
        }
        return res;
    }

    /**
     * 方法三：哈希映射 + 单次扫描
     * 在方法二中，我们需要对数组进行一次扫描，再对哈希映射进行一次扫描。事实上，
     * 我们也可以设计出只进行一次扫描的算法。
     * <p>
     * 我们扫描一次数组，当扫描到元素 x 时，我们首先将 x 加入哈希映射，随后获取哈希映射中 x - 1,
     * x, x + 1 三者出现的次数 u, v, w，那么 u + v 即为 x - 1, x 组成的和谐子序列的长度，v + w
     * 即为 x, x + 1 组成的和谐子序列的长度。假设数组中最长的和谐子序列的最后一个元素在数组中的位置为 i，
     * 那么在扫描到 nums[i] 时，u + v 和 v + w 中一定有一个就是答案。因此这种方法可以找到最长的和谐子序列的长度。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence/solution/zui-chang-he-xie-zi-xu-lie-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findLHS3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num + 1))
                res = Math.max(res, map.get(num) + map.get(num + 1));
            if (map.containsKey(num - 1))
                res = Math.max(res, map.get(num) + map.get(num - 1));
        }
        return res;
    }


    /**
     * 598. 范围求和 II
     * 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
     * <p>
     * 操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，
     * 含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。
     * <p>
     * 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * m = 3, n = 3
     * operations = [[2,2],[3,3]]
     * 输出: 4
     * 解释:
     * 初始状态, M =
     * [[0, 0, 0],
     * [0, 0, 0],
     * [0, 0, 0]]
     * <p>
     * 执行完操作 [2,2] 后, M =
     * [[1, 1, 0],
     * [1, 1, 0],
     * [0, 0, 0]]
     * <p>
     * 执行完操作 [3,3] 后, M =
     * [[2, 2, 1],
     * [2, 2, 1],
     * [1, 1, 1]]
     * <p>
     * M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }

    /**
     * 599. 两个列表的最小索引总和
     * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
     * <p>
     * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
     * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
     * 输出: ["Shogun"]
     * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
     * 示例 2:
     * <p>
     * 输入:
     * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
     * ["KFC", "Shogun", "Burger King"]
     * 输出: ["Shogun"]
     * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    if (!map.containsKey(i + j))
                        map.put(i + j, new ArrayList<String>());
                    map.get(i + j).add(list1[i]);
                }
            }
        }
        int min_index_sum = Integer.MAX_VALUE;
        for (int key : map.keySet())
            min_index_sum = Math.min(min_index_sum, key);
        String[] res = new String[map.get(min_index_sum).size()];
        return map.get(min_index_sum).toArray(res);
    }

    /**
     * 这个方法中我们换一种方法使用哈希表。首先我们遍历 list1list1 一遍并为每个元素在哈希表 mapmap 中创建一个条目，格式为 (list[i], i)(list[i],i)。这里 ii 是第 ii 个元素的下标，list[i]list[i] 就是第 ii 个元素本身。这样我们就创建了一个从 list1list1 中元素到它们下标的映射表。
     * <p>
     * 现在我们遍历 list2list2，对于每一个元素 list2[j]list2[j]，我们检查在 mapmap 中是否已经存在相同元素的键。如果已经存在，说明这一元素在 list1list1 和 list2list2 中都存在。这样我们就知道了这一元素在 list1list1 和 list2list2 中的下标，将它们求和 sum = map.get(list[j]) + jsum=map.get(list[j])+j，如果这一 sumsum 比之前记录的最小值要小，我们更新返回的结果列表 resres，里面只保存 list2[j]list2[j] 作为里面唯一的条目。
     * <p>
     * 如果 suMsuM 与之前获得的最小值相等，那么我们将 list2[j]list2[j] 放入结果列表 resres。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/solution/liang-ge-lie-biao-de-zui-xiao-suo-yin-zong-he-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant2(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < list1.length; i++)
            map.put(list1[i], i);
        List<String> res = new ArrayList<>();

        int min_sum = Integer.MAX_VALUE, sum;

        for (int j = 0; j < list2.length && j <= min_sum; j++) {
            if (map.containsKey(list2[j])) {
                sum = j + map.get(list2[j]);
                if (sum < min_sum) {
                    res.clear();
                    res.add(list2[j]);
                    min_sum = sum;
                } else if (sum == min_sum)
                    res.add(list2[j]);
            }
        }
        return res.toArray(new String[res.size()]);
    }

    /**
     * 606. 根据二叉树创建字符串
     * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
     * <p>
     * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 二叉树: [1,2,3,4]
     * 1
     * /   \
     * 2     3
     * /
     * 4
     * <p>
     * 输出: "1(2(4))(3)"
     * <p>
     * 解释: 原本将是“1(2(4)())(3())”，
     * 在你省略所有不必要的空括号对之后，
     * 它将是“1(2(4))(3)”。
     * 示例 2:
     * <p>
     * 输入: 二叉树: [1,2,3,null,4]
     * 1
     * /   \
     * 2     3
     * \
     * 4
     * <p>
     * 输出: "1(2()(4))(3)"
     * <p>
     * 解释: 和第一个示例相似，
     * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
     *
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        if (t.left == null && t.right == null)
            return t.val + "";
        if (t.right == null)
            return t.val + "(" + tree2str(t.left) + ")";
        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
    }

    /**
     * 迭代法
     *
     * @param t
     * @return
     */
    public String tree2str2(TreeNode t) {
        if (t == null)
            return "";
        Stack<TreeNode> stack = new Stack<>();
        stack.push(t);
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }

    /**
     * 617. 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * <p>
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
     * 否则不为 NULL 的节点将直接作为新二叉树的节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * Tree 1                     Tree 2
     * 1                         2
     * / \                       / \
     * 3   2                     1   3
     * /                           \   \
     * 5                             4   7
     * 输出:
     * 合并后的树:
     * 3
     * / \
     * 4   5
     * / \   \
     * 5   4   7
     * 注意: 合并必须从两个树的根节点开始。
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }

    /**
     * 广度优先
     * 也可以使用广度优先搜索合并两个二叉树。首先判断两个二叉树是否为空，如果两个二叉树都为空，则合并后的二叉树也为空，
     * 如果只有一个二叉树为空，则合并后的二叉树为另一个非空的二叉树。
     * <p>
     * 如果两个二叉树都不为空，则首先计算合并后的根节点的值，
     * 然后从合并后的二叉树与两个原始二叉树的根节点开始广度优先搜索，从根节点开始同时遍历每个二叉树，
     * 并将对应的节点进行合并。
     * <p>
     * 使用三个队列分别存储合并后的二叉树的节点以及两个原始二叉树的节点。初始时将每个二叉树的根节点分别加入相应的队列。
     * 每次从每个队列中取出一个节点，判断两个原始二叉树的节点的左右子节点是否为空。
     * 如果两个原始二叉树的当前节点中至少有一个节点的左子节点不为空，则合并后的二叉树的对应节点的左子节点也不为空。
     * 对于右子节点同理。
     * <p>
     * 如果合并后的二叉树的左子节点不为空，则需要根据两个原始二叉树的左子节点计算合并后的二叉树的左子节点以及整个左子树。
     * 考虑以下两种情况：
     * <p>
     * 如果两个原始二叉树的左子节点都不为空，则合并后的二叉树的左子节点的值为两个原始二叉树的左子节点的值之和，
     * 在创建合并后的二叉树的左子节点之后，将每个二叉树中的左子节点都加入相应的队列；
     * <p>
     * 如果两个原始二叉树的左子节点有一个为空，即有一个原始二叉树的左子树为空，
     * 则合并后的二叉树的左子树即为另一个原始二叉树的左子树，此时也不需要对非空左子树继续遍历，
     * 因此不需要将左子节点加入队列。
     * <p>
     * 对于右子节点和右子树，处理方法与左子节点和左子树相同。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees/solution/he-bing-er-cha-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue.offer(merged);
        queue1.offer(t1);
        queue2.offer(t2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    // 新节点的左子树直接指向了 不为空节点的左子树 所以不需要加入队列了
                    node.left = left1;
                } else if (left2 != null) {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return merged;
    }

    /**
     * 628. 三个数的最大乘积
     * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，
     * 并输出这个乘积。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：6
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：24
     * 示例 3：
     * <p>
     * 输入：nums = [-1,-2,-3]
     * 输出：-6
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1],
                nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    /**
     * 线性扫描
     *
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1,
                max1 * max2 * max3);
    }

    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 输出：[3, 14.5, 11]
     * 解释：
     * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<Double>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }

    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }

    public List<Double> averageOfLevels3(TreeNode root) {
        List<Double> averages = new ArrayList<Double>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     * <p>
     * 案例 1:
     * <p>
     * 输入:
     * 5
     * / \
     * 3   6
     * / \   \
     * 2   4   7
     * <p>
     * Target = 9
     * <p>
     * 输出: True
     * <p>
     * <p>
     * 案例 2:
     * <p>
     * 输入:
     * 5
     * / \
     * 3   6
     * / \   \
     * 2   4   7
     * <p>
     * Target = 28
     * <p>
     * 输出: False
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> hashSet = new HashSet<>();
        return findTargetDsp(root, hashSet, k);
    }

    public boolean findTargetDsp(TreeNode root, HashSet<Integer> hashSet, int k) {
        if (root == null) {
            return false;
        }

        int val = root.val;
        if (hashSet.contains(k - val)) {
            return true;
        } else {
            hashSet.add(val);
            return findTargetDsp(root.left, hashSet, k) ||
                    findTargetDsp(root.right, hashSet, k);
        }
    }

    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                queue.add(node.right);
                queue.add(node.left);
            } else
                queue.remove();
        }
        return false;
    }

    /**
     * 先中序遍历
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget3(TreeNode root, int k) {
        List<Integer> list = new ArrayList();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k)
                return true;
            if (sum < k)
                l++;
            else
                r--;
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 657. 机器人能否返回原点
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     * <p>
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     * <p>
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
     * 示例 2:
     * <p>
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        char[] chars = moves.toCharArray();
        int uC = 0;
        int dC = 0;
        int lC = 0;
        int rC = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'U') {
                uC++;
            } else if (c == 'D') {
                dC++;
            } else if (c == 'L') {
                lC++;
            } else if (c == 'R') {
                rC++;
            }
        }

        return (uC == dC) && (lC == rC);
    }

    /**
     * 官方解法
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle2(String moves) {
        int x = 0, y = 0;
        int length = moves.length();
        for (int i = 0; i < length; i++) {
            char move = moves.charAt(i);
            if (move == 'U') {
                y--;
            } else if (move == 'D') {
                y++;
            } else if (move == 'L') {
                x--;
            } else if (move == 'R') {
                x++;
            }
        }
        return x == 0 && y == 0;
    }

    /**
     * 661. 图片平滑器
     * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [[1,1,1],
     * [1,0,1],
     * [1,1,1]]
     * 输出:
     * [[0, 0, 0],
     * [0, 0, 0],
     * [0, 0, 0]]
     * 解释:
     * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
     * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
     * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother(int[][] M) {
        int R = M.length, C = M[0].length;
        int[][] ans = new int[R][C];

        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r - 1; nr <= r + 1; ++nr)
                    for (int nc = c - 1; nc <= c + 1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                ans[r][c] /= count;
            }
        return ans;
    }

    /**
     * 671. 二叉树中第二小的节点
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
     * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * <p>
     * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
     * <p>
     * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [2,2,5,null,null,5,7]
     * 输出：5
     * 解释：最小的值是 2 ，第二小的值是 5 。
     * 示例 2：
     * <p>
     * <p>
     * 输入：root = [2,2,2]
     * 输出：-1
     * 解释：最小的值是 2, 但是不存在第二小的值。
     *
     * @param root
     * @return
     */
    public static int findSecondMinimumValue(TreeNode root) {
        int r = -1;
        if (root == null) {
            return r;
        }
        int min1 = root.val;
        int min2 = Integer.MAX_VALUE;
        boolean flg = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int val = node.val;

            if (node.left != null) {
                int lVal = node.left.val;
                if (lVal == val) {
                    queue.offer(node.left);
                } else {
                    if (min1 < lVal && lVal <= min2) {
                        min2 = lVal;
                        flg = true;
                    }
                }
            }
            if (node.right != null) {
                int rVal = node.right.val;
                if (rVal == val) {
                    queue.offer(node.right);
                } else if (rVal > val) {
                    if (min1 < rVal && rVal <= min2) {
                        min2 = rVal;
                        flg = true;
                    }
                }
            }
        }

        if (min1 == min2) {
            return -1;
        } else if (min2 == Integer.MAX_VALUE && flg) {
            return min2;
        } else if (min2 == Integer.MAX_VALUE && !flg) {
            return -1;
        } else {
            return min2;
        }
    }

    int res = -1;

    public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) {
            return res;
        }
        //如果存在子树并且值不相等，那么较大的值就有可能是第二小的
        if (root.left != null && root.left.val != root.right.val) {
            //获取左右子树中将较大的值
            int bigger = root.left.val > root.right.val ? root.left.val : root.right.val;
            //如果返回值没有被更改过，则bigger有可能就是第二小的，如果返回值被更改过，则比较当前的res和bigger哪个更小
            res = res == -1 ? bigger : Math.min(res, bigger);
            //将左右子树中值更小的树进行递归，查找是否有更小的值（即为了上一步判断）
            findSecondMinimumValue2(root.left.val > root.right.val ? root.right : root.left);
        }
        //如果左右子树相等或为空，分别递归
        else {
            findSecondMinimumValue2(root.left);
            findSecondMinimumValue2(root.right);
        }
        return res;
    }

    /**
     * 674. 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * <p>
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
     * 都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]
     * 就是连续递增子序列。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     * 示例 2：
     * <p>
     * 输入：nums = [2,2,2,2,2]
     * 输出：1
     * 解释：最长连续递增序列是 [2], 长度为1。
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        int count = 1;
        int mCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
                mCount = Math.max(count, mCount);
            } else {
                count = 1;
            }
        }

        return mCount;
    }

    public int findLengthOfLCIS2(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    /**
     * 682. 棒球比赛
     * 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
     * <p>
     * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，
     * ops 遵循下述规则：
     * <p>
     * 整数 x - 表示本回合新获得分数 x
     * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
     * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * 请你返回记录中所有得分的总和。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：ops = ["5","2","C","D","+"]
     * 输出：30
     * 解释：
     * "5" - 记录加 5 ，记录现在是 [5]
     * "2" - 记录加 2 ，记录现在是 [5, 2]
     * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
     * "D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
     * "+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
     * 所有得分的总和 5 + 10 + 15 = 30
     * 示例 2：
     * <p>
     * 输入：ops = ["5","-2","4","C","D","9","+","+"]
     * 输出：27
     * 解释：
     * "5" - 记录加 5 ，记录现在是 [5]
     * "-2" - 记录加 -2 ，记录现在是 [5, -2]
     * "4" - 记录加 4 ，记录现在是 [5, -2, 4]
     * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
     * "D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
     * "9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
     * "+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
     * "+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
     * 所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
     * 示例 3：
     * <p>
     * 输入：ops = ["1"]
     * 输出：1
     *
     * @param ops
     * @return
     */
    public static int calPoints(String[] ops) {
        int ans = 0;
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            if ("+".equals(op)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b);
                stack.push(a);
                stack.push(a + b);
            } else if ("C".equals(op)) {
                stack.pop();
            } else if ("D".equals(op)) {
                Integer a = stack.pop();
                stack.push(a);
                stack.push(a * 2);
            } else {
                stack.push(Integer.parseInt(op));
            }
        }
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public int calPoints2(String[] ops) {
        Stack<Integer> s = new Stack<>();
        int top = 0, tmp = 0, sum = 0;
        for (String str : ops) {
            if (str.equals("C")) {
                sum -= s.pop();
            } else if (str.equals("D")) {
                sum += s.push(2 * s.peek());
            } else if (str.equals("+")) {
                top = s.pop();
                tmp = top + s.peek();
                s.push(top);
                sum += s.push(tmp);
            } else {
                s.push(Integer.parseInt(str));
                sum += Integer.parseInt(str);
            }
        }
        return sum;
    }

    /**
     * 690. 员工的重要性
     * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
     *
     * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。
     * 那么员工 1 的数据结构是 [1, 15, [2]] ，
     * 员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。
     * 注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。
     *
     * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
     *
     *
     *
     * 示例：
     *
     * 输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * 输出：11
     * 解释：
     * 员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。
     * 因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
     * @param employees
     * @param id
     * @return
     */
    Map<Integer, Employee> employeeMap = new HashMap<>();
    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            employeeMap.put(employee.id, employee);
        }
        return dfsEmployee(id);
    }

    public int dfsEmployee(int id) {
        Employee employee = employeeMap.get(id);
        int total = employee.importance;
        for (Integer subordinate : employee.subordinates) {
            total += dfsEmployee(subordinate);
        }
        return total;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(2);
        root.left = left;

        TreeNode right = new TreeNode(Integer.MAX_VALUE);
//        TreeNode left1 = new TreeNode(5);
//        TreeNode right2 = new TreeNode(7);
//        right.left = left1;
//        right.right = right2;

        root.right = right;

        System.out.println(calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));
    }
}
