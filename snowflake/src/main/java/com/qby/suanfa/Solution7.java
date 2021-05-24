package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

import java.util.*;

public class Solution7 {
    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(66, 708));
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
}
