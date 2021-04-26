package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRelativeRanks(new int[]{10, 3, 8, 9, 4})));
    }
}
