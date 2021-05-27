package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

import java.util.*;
import java.util.regex.Pattern;

public class Solution7 {
    public static void main(String[] args) {
//        System.out.println(numJewelsInStones2("aA", "aAAbbbb"));
        String pattern = "[2569]";

        System.out.println(Pattern.matches(pattern, "2569"));
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

    /**
     * 720. 词典中最长的单词
     * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，
     * 该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，
     * 则返回答案中字典序最小的单词。
     * <p>
     * 若无答案，则返回空字符串。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * words = ["w","wo","wor","worl", "world"]
     * 输出："world"
     * 解释：
     * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
     * 示例 2：
     * <p>
     * 输入：
     * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * 输出："apple"
     * 解释：
     * "apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word : words) wordset.add(word);
        for (String word : words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }
        }
        return ans;
    }

    /**
     * 前缀树 + 深度优先搜索
     *
     * @param words
     * @return
     */
    public String longestWord2(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word : words) {
            trie.insert(word, ++index); //indexed by 1
        }
        trie.words = words;
        return trie.dfs();
    }

    class Node {
        char c;
        HashMap<Character, Node> children = new HashMap();
        int end;

        public Node(char c) {
            this.c = c;
        }
    }

    class Trie {
        Node root;
        String[] words;

        public Trie() {
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }

        public String dfs() {
            String ans = "";
            Stack<Node> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > ans.length() ||
                                word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (Node nei : node.children.values()) {
                        stack.push(nei);
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 728. 自除数
     * 自除数 是指可以被它包含的每一位数除尽的数。
     * <p>
     * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
     * <p>
     * 还有，自除数不允许包含 0 。
     * <p>
     * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * 上边界left = 1, 下边界right = 22
     * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     *
     * @param left
     * @param right
     * @return
     */
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> rList = new ArrayList<>();
        int l = left, r = right;
        while (l <= r) {
            if (l > 0) {
                if (l < 10) {
                    rList.add(l);
                } else if (l % 10 != 0) {
                    boolean flg = true;
                    int tmpL = l;
                    System.out.println(l);
                    while (tmpL > 0) {
                        int div = tmpL % 10;
                        if (div == 0 || l % div != 0) {
                            flg = false;
                            break;
                        }
                        tmpL /= 10;
                    }
                    if (flg) {
                        rList.add(l);
                    }
                }
            }
            l++;
        }

        return rList;
    }

    /**
     * 733. 图像渲染
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     * <p>
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，
     * 让你重新上色这幅图像。
     * <p>
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，
     * 重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     * <p>
     * 最后返回经过上色渲染后的图像。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * 输出: [[2,2,2],[2,2,0],[2,0,1]]
     * 解析:
     * 在图像的正中间，(坐标(sr,sc)=(1,1)),
     * 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2，
     * 因为它不是在上下左右四个方向上与初始点相连的像素点。
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor) {
            return image;
        }
        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }

    /**
     * 深度优先算法
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor) {
            return image;
        }
        dfsColor(image, sr, sc, currColor, newColor);
        return image;
    }

    public void dfsColor(int[][] image, int x, int y, int color, int newColor) {
        if (image[x][y] == color) {
            image[x][y] = newColor;
            for (int i = 0; i < 4; i++) {
                int xn = x + dx[i];
                int yn = y + dy[i];
                if (xn >= 0 && xn < image.length && yn >= 0 && yn < image[0].length) {
                    dfsColor(image, xn, yn, color, newColor);
                }
            }
        }
    }

    /**
     * 744. 寻找比目标字母大的最小字母
     * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。
     * 另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
     * <p>
     * 在比较时，字母是依序循环出现的。举个例子：
     * <p>
     * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "a"
     * 输出: "c"
     * <p>
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "c"
     * 输出: "f"
     * <p>
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "d"
     * 输出: "f"
     * <p>
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "g"
     * 输出: "j"
     * <p>
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "j"
     * 输出: "c"
     * <p>
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "k"
     * 输出: "c"
     *
     * @param letters
     * @param target
     * @return
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        if (letters[letters.length - 1] <= target) {
            return letters[0];
        }
        if (letters[0] > target) {
            return letters[0];
        }

        char a = '0';
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > target) {
                a = letters[i];
                break;
            }
        }
        return a;
    }

    /**
     * 二分法
     *
     * @param letters
     * @param target
     * @return
     */
    public static char nextGreatestLetter2(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] <= target) lo = mi + 1;
            else hi = mi;
        }
        return letters[lo % letters.length];
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * <p>
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     * <p>
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：cost = [10, 15, 20]
     * 输出：15
     * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
     * 示例 2：
     * <p>
     * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出：6
     * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    /**
     * 748. 最短补全词
     * 给定一个字符串牌照 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
     * <p>
     * 如果单词列表（words）中的一个单词包含牌照（licensePlate）中所有的字母，那么我们称之为 补全词 。
     * 在所有完整词中，最短的单词我们称之为 最短补全词 。
     * <p>
     * 单词在匹配牌照中的字母时要：
     * <p>
     * 忽略牌照中的数字和空格。
     * 不区分大小写，比如牌照中的 "P" 依然可以匹配单词中的 "p" 字母。
     * 如果某个字母在牌照中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
     * 例如：licensePlate = "aBc 12c"，那么它由字母 'a'、'b' （忽略大写）和两个 'c' 。
     * 可能的 补全词 是 "abccdef"、"caaacab" 以及 "cbca" 。
     * <p>
     * 题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取单词列表中最靠前的一个。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
     * 输出："steps"
     * 说明：最短补全词应该包括 "s"、"p"、"s" 以及 "t"。在匹配过程中我们忽略牌照中的大小写。
     * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
     * "steps" 包含 "t"、"p" 和两个 "s"。
     * "stripe" 缺一个 "s"。
     * "stepple" 缺一个 "s"。
     * 因此，"steps" 是唯一一个包含所有字母的单词，也是本样例的答案。
     * 示例 2：
     * <p>
     * 输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
     * 输出："pest"
     * 说明：存在 3 个包含字母 "s" 且有着最短长度的补全词，"pest"、"stew"、和 "show"
     * 三者长度相同，但我们返回最先出现的补全词 "pest" 。
     * 示例 3：
     * <p>
     * 输入：licensePlate = "Ah71752", words = ["suggest","letter","of","husband",
     * "easy","education","drug","prevent","writer","old"]
     * 输出："husband"
     * 示例 4：
     * <p>
     * 输入：licensePlate = "OgEu755", words = ["enough","these","play","wide",
     * "wonder","box","arrive","money","tax","thus"]
     * 输出："enough"
     * 示例 5：
     * <p>
     * 输入：licensePlate = "iMSlpe4", words = ["claim","consumer","student",
     * "camera","public","never","wonder","simple","thought","use"]
     * 输出："simple"
     *
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] target = count(licensePlate);
        String ans = "";
        for (String word : words) {
            if ((word.length() < ans.length() || ans.length() == 0) &&
                    dominates(count(word.toLowerCase()), target))
                ans = word;
        }
        return ans;
    }

    public boolean dominates(int[] count1, int[] count2) {
        for (int i = 0; i < 26; ++i)
            if (count1[i] < count2[i])
                return false;
        return true;
    }

    public int[] count(String word) {
        int[] ans = new int[26];
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            int idx = Character.toLowerCase(aChar) - 'a';
            if (idx >= 0 && idx < 26) {
                ans[idx]++;
            }
        }
        return ans;
    }


    /**
     * 762. 二进制表示中质数个计算置位
     * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
     * <p>
     * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
     * <p>
     * 示例 1:
     * <p>
     * 输入: L = 6, R = 10
     * 输出: 4
     * 解释:
     * 6 -> 110 (2 个计算置位，2 是质数)
     * 7 -> 111 (3 个计算置位，3 是质数)
     * 9 -> 1001 (2 个计算置位，2 是质数)
     * 10-> 1010 (2 个计算置位，2 是质数)
     * 示例 2:
     * <p>
     * 输入: L = 10, R = 15
     * 输出: 5
     * 解释:
     * 10 -> 1010 (2 个计算置位, 2 是质数)
     * 11 -> 1011 (3 个计算置位, 3 是质数)
     * 12 -> 1100 (2 个计算置位, 2 是质数)
     * 13 -> 1101 (3 个计算置位, 3 是质数)
     * 14 -> 1110 (3 个计算置位, 3 是质数)
     * 15 -> 1111 (4 个计算置位, 4 不是质数)
     *
     * @param left
     * @param right
     * @return
     */
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int tmp = Integer.bitCount(i);
            if (isSmallPrime(tmp)) {
                ans++;
            }
        }


        return ans;
    }

    public boolean isSmallPrime(int x) {
        return (x == 2 || x == 3 || x == 5 || x == 7 ||
                x == 11 || x == 13 || x == 17 || x == 19);
    }

    /**
     * 766. 托普利茨矩阵
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     * <p>
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     * 示例 2：
     * <p>
     * <p>
     * 输入：matrix = [[1,2],[2,2]]
     * 输出：false
     * 解释：
     * 对角线 "[1, 2]" 上的元素不同。
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 771. 宝石与石头
     * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
     * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * <p>
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     * <p>
     * 示例 1:
     * <p>
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     *
     * @param jewels
     * @param stones
     * @return
     */
    public static int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> hashSet = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            hashSet.add(c);
        }
        int count = 0;
        for (char c : stones.toCharArray()) {
            if (hashSet.contains(c)) {
                count++;
            }
        }

        return count;
    }

    /**
     * @param jewels
     * @param stones
     * @return
     */
    public static int numJewelsInStones2(String jewels, String stones) {
        int ans = 0;
        int[] sz = new int[128];
        for (char c : jewels.toCharArray()) {
            sz[c]++;
        }
        for (char c : stones.toCharArray()) {
            if (sz[c] != 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 783. 二叉搜索树节点最小距离
     * 给你一个二叉搜索树的根节点 root ，返回树中任意两不同节点值之间的最小差值 。
     * <p>
     * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     * 示例 2：
     * <p>
     * <p>
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfsMinDiff(root, list);
        int mVal = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            mVal = Math.min(list.get(i) - list.get(i - 1), mVal);
        }
        return mVal;
    }

    public void dfsMinDiff(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfsMinDiff(root.left, list);
        list.add(root.val);
        dfsMinDiff(root.right, list);
    }

    int pre;
    int ans;

    public int minDiffInBST2(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }

    /**
     * 788. 旋转数字
     * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，
     * 且和 X 不同的数。要求每位数字都要被旋转。
     * <p>
     * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1,
     * 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，
     * 换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
     * <p>
     * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入: 10
     * 输出: 4
     * 解释:
     * 在[1, 10]中有四个好数： 2, 5, 6, 9。
     * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
     *
     * @param N
     * @return
     */
    public int rotatedDigits(int N) {
        // Count how many n in [1, N] are good.
        int ans = 0;
        for (int n = 1; n <= N; ++n)
            if (good(n, false)) ans++;
        return ans;
    }

    // Return true if n is good.
    // The flag is true iff we have an occurrence of 2, 5, 6, 9.
    public boolean good(int n, boolean flag) {
        if (n == 0) return flag;

        int d = n % 10;
        if (d == 3 || d == 4 || d == 7) return false;
        if (d == 0 || d == 1 || d == 8) return good(n / 10, flag);
        return good(n / 10, true);
    }

    public int rotatedDigits2(int N) {
        char[] A = String.valueOf(N).toCharArray();
        int K = A.length;

        int[][][] memo = new int[K + 1][2][2];
        memo[K][0][1] = memo[K][1][1] = 1;
        for (int i = K - 1; i >= 0; --i) {
            for (int eqf = 0; eqf <= 1; ++eqf)
                for (int invf = 0; invf <= 1; ++invf) {
                    // We will compute ans = memo[i][eqf][invf],
                    // the number of good numbers with respect to N = A[i:].
                    // If eqf is true, we must stay below N, otherwise
                    // we can use any digits.
                    // Invf becomes true when we write a 2569, and it
                    // must be true by the end of our writing as all
                    // good numbers have a digit in 2569.
                    int ans = 0;
                    for (char d = '0'; d <= (eqf == 1 ? A[i] : '9'); ++d) {
                        if (d == '3' || d == '4' || d == '7') continue;
                        boolean invo = (d == '2' || d == '5' || d == '6' || d == '9');
                        ans += memo[i + 1][d == A[i] ? eqf : 0][invo ? 1 : invf];
                    }
                    memo[i][eqf][invf] = ans;
                }
        }

        return memo[0][1][0];
    }
}
