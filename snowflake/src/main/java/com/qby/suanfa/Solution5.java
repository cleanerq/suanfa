package com.qby.suanfa;

import com.qby.suanfa.basic.TreeNode;

import java.util.*;
import java.util.function.IntBinaryOperator;

public class Solution5 {

    /**
     * 234. 回文链表
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * <p>
     * <p>
     * 快慢指针找到中间节点
     * 然后翻转后半部分的链表
     * 然后比较两个子链表
     * 最后在反转后半部分链表-恢复链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    /**
     * 翻转链表
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 双指针法
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     * <p>
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * <p>
     * <p>
     * <p>
     * 一次遍历
     * <p>
     * 这个是二叉搜索树
     * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
     * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
     * 二叉搜索树作为一种经典的数据结构，它既有链表的快速插入与删除操作的特点，又有数组快速查找的优势；
     * 所以应用十分广泛，例如在文件系统和数据库系统一般会采用这种数据结构进行高效率的排序与检索操作
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    /**
     * 二次遍历
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }


    /**
     * 237. 删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
     * <p>
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     * <p>
     * 示例 1：
     * <p>
     * 输入：head = [4,5,1,9], node = 5
     * 输出：[4,1,9]
     * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2：
     * <p>
     * 输入：head = [4,5,1,9], node = 1
     * 输出：[4,5,9]
     * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 242. 有效的字母异位词
     * 有效的字母异位词:如果两个单词字符串包含相同的字符及对应数量,只是字符顺序不同,则这两个单词就是有效的字母异位词。
     * <p>
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: s = "rat", t = "car"
     * 输出: false
     * <p>
     * 排序后比较
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    /**
     * 从另一个角度考虑，tt 是 ss 的异位词等价于「两个字符串中字符出现的种类和次数均相等」。
     * 由于字符串只包含 2626 个小写字母，因此我们可以维护一个长度为 2626 的频次数组 \textit{table}table，
     * 先遍历记录字符串 ss 中字符出现的频次，然后遍历字符串 tt，减去 \textit{table}table 中对应的频次，
     * 如果出现 \textit{table}[i]<0table[i]<0，则说明 tt 包含一个不在 ss 中的额外字符，
     * 返回 \text{false}false 即可。
     *
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        for (char aChar : charS) {
            table[aChar - 'a']++;
        }

        for (char tChar : charT) {
            table[tChar - 'a']--;
            if (table[tChar - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * <p>
     * 1
     * /   \
     * 2     3
     * \
     * 5
     * <p>
     * 输出: ["1->2->5", "1->3"]
     * <p>
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     * <p>
     * 深度优先
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuilder st = new StringBuilder(path);
            st.append(root.val);
            if (root.left == null && root.right == null) {
                paths.add(st.toString());
            } else {
                st.append("->");
                constructPaths(root.left, st.toString(), paths);
                constructPaths(root.right, st.toString(), paths);
            }
        }
    }

    /**
     * 广度优先
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode treeNode = nodeQueue.poll();
            String path = pathQueue.poll();

            if (treeNode.left == null && treeNode.right == null) {
                paths.add(path);
            } else {
                if (treeNode.left != null) {
                    nodeQueue.offer(treeNode.left);
                    pathQueue.offer(new StringBuffer(path)
                            .append("->").append(treeNode.left.val).toString());
                }
                if (treeNode.right != null) {
                    nodeQueue.offer(treeNode.right);
                    pathQueue.offer(new StringBuffer(path)
                            .append("->").append(treeNode.right.val).toString());
                }
            }
        }

        return paths;
    }

    /**
     * 258. 各位相加
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     * <p>
     * 示例:
     * <p>
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     *
     * @param num
     * @return
     */
    public static int addDigits(int num) {
        if (num < 10) {
            return num;
        }

        int res = 0;
        while (num >= 10) {
            res = 0;
            String n = Integer.toString(num);
            int size = n.length();
            for (int i = 0; i < size; i++) {
                res = res + num % 10;
                num = num / 10;
            }
            num = res;
        }

        return res;
    }

    /**
     * 能够被9整除的整数，各位上的数字加起来也必然能被9整除，
     * 所以，连续累加起来，最终必然就是9。
     * 不能被9整除的整数，各位上的数字加起来，结果对9取模，和初始数对9取摸，是一样的，所以，
     * 连续累加起来，最终必然就是初始数对9取摸。
     *
     * @param num
     * @return
     */
    public static int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }

    /**
     * 263. 丑数
     * 编写一个程序判断给定的数是否为丑数。
     * <p>
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 6
     * 输出: true
     * 解释: 6 = 2 × 3
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: true
     * 解释: 8 = 2 × 2 × 2
     * 示例 3:
     * <p>
     * 输入: 14
     * 输出: false
     * 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
     *
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) n = n / 2;
        while (n % 3 == 0) n = n / 3;
        while (n % 5 == 0) n = n / 5;
        return n == 1;
    }

    /**
     * 268. 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * <p>
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     * <p>
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 4：
     * <p>
     * 输入：nums = [0]
     * 输出：1
     * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
     * <p>
     * 排序
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        // 判断 n 是否出现在末位
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }
        // 判断 0 是否出现在首位
        else if (nums[0] != 0) {
            return 0;
        }

        // 此时缺失的数字一定在 (0, n) 中
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i - 1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }

        // 未缺失任何数字（保证函数有返回值）
        return -1;
    }

    /**
     * 哈希法
     *
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    /**
     * 异或法
     *
     * @param nums
     * @return
     */
    public int missingNumber3(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    /**
     * 数学方法
     *
     * @param nums
     * @return
     */
    public int missingNumber4(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 290. 单词规律
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     * <p>
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的
     * 每个非空单词之间存在着双向连接的对应规律。
     * <p>
     * 示例1:
     * <p>
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     * 示例 3:
     * <p>
     * 输入: pattern = "aaaa", str = "dog cat cat dog"
     * 输出: false
     * 示例 4:
     * <p>
     * 输入: pattern = "abba", str = "dog dog dog dog"
     * 输出: false
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

    public static boolean wordPattern2(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();

        String[] sz = str.split("\\s+");

        if (sz.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (str2ch.containsKey(sz[i]) && str2ch.get(sz[i]) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !sz[i].equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(sz[i], ch);
            ch2str.put(ch, sz[i]);
        }

        return true;
    }


    /**
     * 292. Nim 游戏
     * 你和你的朋友，两个人一起玩 Nim 游戏：
     * <p>
     * 桌子上有一堆石头。
     * 你们轮流进行自己的回合，你作为先手。
     * 每一回合，轮到的人拿掉 1 - 3 块石头。
     * 拿掉最后一块石头的人就是获胜者。
     * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n
     * 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 4
     * 输出：false
     * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     * 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：n = 2
     * 输出：true
     * <p>
     * 要想获胜，在你的回合中，必须避免石头堆中的石子数为 4 的情况。不能被4整除
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }


    /**
     * 326. 3的幂
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 27
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：n = 0
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：n = 9
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：n = 45
     * 输出：false
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;
    }

    /**
     * 方法四：整数限制
     * 在 Java 中说明了该变量是四个字节，他的最大值为 2147483647。有三种方法可以计算出该最大值。
     * <p>
     * <p>
     * 知道了 n 的限制，我们现在可以推断出 n 的最大值，也就是 3 的幂，是 1162261467。我们计算如下：
     * <p>
     * 因此，我们应该返回 true 的 n 的可能值是 3^03
     * 0
     * ，3^13
     * 1
     * …3 ^ {19}3
     * 19
     * 。因为 3 是质数，所以 3^{19}3
     * 19
     * 的除数只有 3^03
     * 0
     * ，3^13
     * 1
     * …3 ^{19}3
     * 19
     * ，因此我们只需要将 3^{19}3
     * 19
     * 除以 n。若余数为 0 意味着 n 是 3^{19}3
     * 19
     * 的除数，因此是 3 的幂。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 一个重要的信息可以从函数名中推导出来。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 342. 4的幂
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 16
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：n = 5
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：n = 1
     * 输出：true
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        if (n < 1) return false;
        while (n % 4 == 0) n = n / 4;
        return n == 1;
    }

    /**
     * 位运算
     * 我们首先检查 num 是否为 2 的幂：x > 0 and x & (x - 1) == 0。
     * <p>
     * 现在的问题是区分 2 的偶数幂（当 xx 是 4 的幂时）和 2 的奇数幂（当 xx 不是 4 的幂时）。
     * 在二进制表示中，这两种情况都只有一位为 1，其余为 0。
     * <p>
     * 有什么区别？在第一种情况下（4 的幂），1 处于偶数位置：第 0 位、第 2 位、第 4 位等；
     * 在第二种情况下，1 处于奇数位置。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/power-of-four/solution/4de-mi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour2(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
    }

    /**
     * 若 xx 为 2 的幂且 x%3 == 1，则 xx 为 4 的幂。
     * 我们首先检车 xx 是否为 2 的幂：x > 0 and x & (x - 1) == 0。然后可以确定 x = 2^ax=2
     * a
     * ，若 xx 为 4 的幂则 aa 为偶数。
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour3(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && (num % 3 == 1);
    }


    /**
     * 345. 反转字符串中的元音字母
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     * <p>
     * 示例 1：
     * <p>
     * 输入："hello"
     * 输出："holle"
     * 示例 2：
     * <p>
     * 输入："leetcode"
     * 输出："leotcede"
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        HashSet<Character> vowels = new HashSet<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A',
                        'E', 'I', 'O', 'U'));

        if (s == null) return null;
        int low = 0, high = s.length() - 1;
        char[] c = new char[s.length()];
        while (low <= high) {
            char cl = s.charAt(low);
            char ch = s.charAt(high);
            if (!vowels.contains(cl)) {
                c[low++] = cl;
            } else if (!vowels.contains(ch)) {
                c[high--] = ch;
            } else {
                c[low++] = ch;
                c[high--] = cl;
            }
        }
        return new String(c);
    }

    /**
     * 349. 两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     * <p>
     * 说明：
     * <p>
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     * <p>
     * 方法一：两个集合
     * <p>
     * 如果使用哈希集合存储元素，则可以在 O(1)的时间内判断一个元素是否在集合中，从而降低时间复杂度。
     * <p>
     * 首先使用两个集合分别存储两个数组中的元素，然后遍历较小的集合，判断其中的每个元素是否在另一个集合中，
     * 如果元素也在另一个集合中，则将该元素添加到返回值。该方法的时间复杂度可以降低到 O(m+n)。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays/solution/liang-ge-shu-zu-de-jiao-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<Integer>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }

    /**
     * 方法二：排序 + 双指针
     * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     * <p>
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，
     * 则将指向较小数字的指针右移一位，如果两个数字相等，且该数字不等于 \textit{pre}pre ，
     * 将该数字添加到答案并更新 \textit{pre}pre 变量，同时将两个指针都右移一位。
     * 当至少有一个指针超出数组范围时，遍历结束。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays/solution/liang-ge-shu-zu-de-jiao-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 350. 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * <p>
     * <p>
     * 说明：
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     * 进阶：
     * <p>
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


    /**
     * 383. 赎金信
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom
     * 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * <p>
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。
     * 杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * 示例 2：
     * <p>
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     * <p>
     * <p>
     * 提示：
     * <p>
     * 你可以假设两个字符串均只含有小写字母。
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0) return false;
        }
        return true;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * s = "leetcode"
     * 返回 0
     * <p>
     * s = "loveleetcode"
     * 返回 2
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (cnt[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * <p>
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * <p>
     * 请找出在 t 中被添加的字母。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "abcd", t = "abcde"
     * 输出："e"
     * 解释：'e' 是那个被添加的字母。
     * 示例 2：
     * <p>
     * 输入：s = "", t = "y"
     * 输出："y"
     * 示例 3：
     * <p>
     * 输入：s = "a", t = "aa"
     * 输出："a"
     * 示例 4：
     * <p>
     * 输入：s = "ae", t = "aea"
     * 输出："a"
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }

        char[] charsT = t.toCharArray();
        char c = 0;
        for (int i = 0; i < charsT.length; i++) {
            c = charsT[i];
            cnt[c - 'a']--;
            if (cnt[c - 'a'] == -1) {
                break;
            }
        }
        return c;
    }

    /**
     * 两个字符串的每个字符求异或 最后结果就是多出来的字符
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }

    /**
     * 对每个字符串分别求和 然后想减最后结果就是多出来的字符
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference3(String s, String t) {
        int as = 0, at = 0;
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            at += t.charAt(i);
        }
        return (char) (at - as);
    }


    /**
     * 392. 判断子序列
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 进阶：
     * <p>
     * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
     * 在这种情况下，你会怎样改变代码？
     * <p>
     * 致谢：
     * <p>
     * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "abc", t = "ahbgdc"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "axc", t = "ahbgdc"
     * 输出：false
     * <p>
     * 双指针法
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (charS[i] == charT[j]) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    /**
     * 401. 二进制手表
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
     * <p>
     * 每个 LED 代表一个 0 或 1，最低位在右侧。
     * <p>
     * <p>
     * <p>
     * （图源：WikiMedia - Binary clock samui moon.jpg ，
     * 许可协议：Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ）
     * <p>
     * 例如，上面的二进制手表读取 “3:25”。
     * <p>
     * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入: n = 1
     * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     *
     * @param turnedOn
     * @return
     */
    static int[] hours = new int[]{1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
    static int[] minutes = new int[]{0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
    static List<String> res = new ArrayList<>();

    public static List<String> readBinaryWatch(int num) {
        backtrack(num, 0, 0, 0);
        return res;
    }

    public static void backtrack(int num, int index, int hour, int minute) {
        if (hour > 11 || minute > 59)
            return;
        if (num == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(':');
            if (minute < 10) {
                sb.append('0');
            }
            sb.append(minute);
            res.add(sb.toString());
            return;
        }
        for (int i = index; i < 10; i++) {
            backtrack(num - 1, i + 1, hour + hours[i], minute + minutes[i]);
        }
    }


    /**
     * 404. 左叶子之和
     * 计算给定二叉树的所有左叶子之和。
     * <p>
     * 示例：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (isLeafNode(node.left)) {
                    ans += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null) {
                if (!isLeafNode(node.right)) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }

    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t.left != null) {
                if (isLeafNode(t.left)) {
                    sum += t.left.val;
                } else {
                    queue.offer(t.left);
                }
            }

            if (t.right != null) {
                if (!isLeafNode(t.right)) {
                    queue.offer(t.right);
                }
            }
        }
        return sum;
    }

    /**
     * 405. 数字转换为十六进制数
     * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     * <p>
     * 注意:
     * <p>
     * 十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；
     * 对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
     * 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
     * 示例 1：
     * <p>
     * 输入:
     * 26
     * <p>
     * 输出:
     * "1a"
     * 示例 2：
     * <p>
     * 输入:
     * -1
     * <p>
     * 输出:
     * "ffffffff"
     *
     * @param num
     * @return
     */
    public static String toHex(int num) {
        // 0特殊处理
        if (num == 0) {
            return "0";
        }
        // 获取32位二进制位
        int[] bits = new int[32];
        for (int i = 31; i >= 0; i--) {
            bits[i] = num & 0x1;
            num >>>= 1;
        }
        // 从左到右每4位转成1位十六进制
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= 28; i += 4) {
            int sum = 8 * bits[i] + 4 * bits[i + 1] +
                    2 * bits[i + 2] + 1 * bits[i + 3];
            if (sum <= 9) {
                ans.append((char) (sum + '0'));
            } else if (sum <= 15) {
                ans.append((char) (sum - 10 + 'a'));
            }
        }
        // 去掉前导0，这就是为什么0要特殊处理的原因
        // 因为0的十六进制还是全为0，用这个循环会越界访问
        while (ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }
        return ans.toString();
    }

    public static String toHex2(int num) {
        if (num == 0) {
            return "0";
        }   // 0特殊处理
        char[] hex = "0123456789abcdef".toCharArray();  // 相当于映射关系
        StringBuilder ans = new StringBuilder();
        while (num != 0) {
            int temp = num & 0xf;   // 取低4位的十进制值
            ans.append(hex[temp]);  // 映射对应字符
            num >>>= 4;             // 逻辑右移4位
        }
        // while的循环条件保证了不会出现前导0
        // 但是从低位开始转换多了一步reverse反转
        return ans.reverse().toString();
    }


    /**
     * 409. 最长回文串
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     * <p>
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * <p>
     * 注意:
     * 假设字符串的长度不会超过 1010。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * "abccccdd"
     * <p>
     * 输出:
     * 7
     * <p>
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     *
     * @param s
     * @return
     */
    public static int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                Integer orDefault = map.getOrDefault(chars[i], 0);
                map.put(chars[i], orDefault.intValue() + 1);
            } else {
                map.put(chars[i], 1);
            }
        }

        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        int countOne = 0;
        Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            Integer value = next.getValue();
            if (value % 2 == 1) {
                countOne++;
            }
        }

        if (countOne > 0) {
            countOne--;
        }
        return s.length() - countOne;
    }

    /**
     * 如果有任何一个字符 ch 的出现次数 v 为奇数（即 v % 2 == 1），那么可以将这个字符作为回文中心，
     * 注意只能最多有一个字符作为回文中心。在代码中，我们用 ans 存储回文串的长度，由于在遍历字符时，ans
     * 每次会增加 v / 2 * 2，因此 ans 一直为偶数。但在发现了第一个出现次数为奇数的字符后，我们将 ans 增加 1，
     * 这样 ans 变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变 ans 的值了。
     * <p>
     * JavaPython3C++
     * <p>
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-palindrome/solution/zui-chang-hui-wen-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int longestPalindrome2(String s) {
        int[] count = new int[128];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans += v % 2;
        }
        return ans == 0 ? s.length() : s.length() - ans + 1;
    }

    /**
     * 直接统计奇数个数
     *
     * @param s
     * @return
     */
    public int longestPalindrome3(String s) {
        int[] count = new int[128];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 412. Fizz Buzz
     * 写一个程序，输出从 1 到 n 数字的字符串表示。
     * <p>
     * 1. 如果 n 是3的倍数，输出“Fizz”；
     * <p>
     * 2. 如果 n 是5的倍数，输出“Buzz”；
     * <p>
     * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     * <p>
     * 示例：
     * <p>
     * n = 15,
     * <p>
     * 返回:
     * [
     * "1",
     * "2",
     * "Fizz",
     * "4",
     * "Buzz",
     * "Fizz",
     * "7",
     * "8",
     * "Fizz",
     * "Buzz",
     * "11",
     * "Fizz",
     * "13",
     * "14",
     * "FizzBuzz"
     * ]
     *
     * @param n
     * @return
     */
    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(Integer.toString(i));
            }
        }

        return list;
    }

    public List<String> fizzBuzz2(int n) {
        // ans list
        List<String> ans = new ArrayList<String>();

        for (int num = 1; num <= n; num++) {

            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);

            String numAnsStr = "";

            if (divisibleBy3) {
                // Divides by 3, add Fizz
                numAnsStr += "Fizz";
            }

            if (divisibleBy5) {
                // Divides by 5, add Buzz
                numAnsStr += "Buzz";
            }

            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }

            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }

        return ans;
    }

    public List<String> fizzBuzz3(int n) {

        // ans list
        List<String> ans = new ArrayList<String>();

        // Hash map to store all fizzbuzz mappings.
        HashMap<Integer, String> fizzBizzDict =
                new HashMap<Integer, String>() {
                    {
                        put(3, "Fizz");
                        put(5, "Buzz");
                    }
                };

        for (int num = 1; num <= n; num++) {

            String numAnsStr = "";

            for (Integer key : fizzBizzDict.keySet()) {

                // If the num is divisible by key,
                // then add the corresponding string mapping to current numAnsStr
                if (num % key == 0) {
                    numAnsStr += fizzBizzDict.get(key);
                }
            }

            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }

            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }

        return ans;
    }

    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     * <p>
     * <p>
     * 提示：
     * <p>
     * num1 和num2 的长度都小于 5100
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder st = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();
        while (i >= 0 || j >= 0) {
            int intI = i >= 0 ? char1[i] - '0' : 0;
            int intJ = j >= 0 ? char2[j] - '0' : 0;
            int res = intI + intJ + add;
            st.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        if (add != 0) {
            st.append(add);
        }
        return st.reverse().toString();
    }

    /**
     * 448. 找到所有数组中消失的数字
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * <p>
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * <p>
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * <p>
     * 输出:
     * [5,6]
     * <p>
     * 我们可以用一个哈希表记录数组 \textit{nums}nums 中的数字，由于数字范围均在 [1,n][1,n] 中，记录数字后我们再利用哈希表检查 [1,n][1,n] 中的每一个数是否出现，从而找到缺失的数字。
     * <p>
     * 由于数字范围均在 [1,n][1,n] 中，我们也可以用一个长度为 nn 的数组来代替哈希表。这一做法的空间复杂度是 O(n)O(n) 的。我们的目标是优化空间复杂度到 O(1)O(1)。
     * <p>
     * 注意到 \textit{nums}nums 的长度恰好也为 nn，能否让 \textit{nums}nums 充当哈希表呢？
     * <p>
     * 由于 \textit{nums}nums 的数字范围均在 [1,n][1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
     * <p>
     * 具体来说，遍历 \textit{nums}nums，每遇到一个数 xx，就让 \textit{nums}[x-1]nums[x−1] 增加 nn。由于 \textit{nums}nums 中所有数均在 [1,n][1,n] 中，增加以后，这些数必然大于 nn。最后我们遍历 \textit{nums}nums，若 \textit{nums}[i]nums[i] 未大于 nn，就说明没有遇到过数 i+1i+1。这样我们就找到了缺失的数字。
     * <p>
     * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 nn 取模来还原出它本来的值。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-d-mabl/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> res = new LinkedList<>();
        int x;
        for (int i = 0; i < n; i++) {
            x = (nums[i] - 1) % n;//还原x，得到x的下标
            nums[x] += n;//此时x的位置上的值大于n，表明x出现过至少一次
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {//
                res.add(i + 1);//i位置上的值i+1未曾出现过
            } else {
                nums[i] %= n;//还原该位置上的值
            }
        }
        return res;
    }

    /**
     * 453. 最小操作次数使数组元素相等
     * 给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。
     * 找出让数组所有元素相等的最小操作次数。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：
     * [1,2,3]
     * 输出：
     * 3
     * 解释：
     * 只需要3次操作（注意每次操作会增加两个元素的值）：
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     * <p>
     * 排序方法
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }


    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = (moves + nums[i]) - nums[i - 1];
            nums[i] += moves;
            moves += diff;
        }
        return moves;
    }

    /**
     * 先找到最小值 然后用每个数减去最小值
     *
     * @param nums
     * @return
     */
    public int minMoves3(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - min;
        }
        return moves;
    }

    /**
     * 455. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * <p>
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
     * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
     * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     * 示例 2:
     * <p>
     * 输入: g = [1,2], s = [1,2,3]
     * 输出: 2
     * 解释:
     * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
     * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
     * 所以你应该输出2.
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int numOfChildren = g.length, numOfCookies = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {
            while (j < numOfCookies && g[i] > s[j]) {
                j++;
            }
            if (j < numOfCookies) {
                count++;
            }
        }
        return count;
    }

    /**
     * 459. 重复的子字符串
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
     * 给定的字符串只含有小写英文字母，并且长度不超过10000。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abab"
     * <p>
     * 输出: True
     * <p>
     * 解释: 可由子字符串 "ab" 重复两次构成。
     * 示例 2:
     * <p>
     * 输入: "aba"
     * <p>
     * 输出: False
     * 示例 3:
     * <p>
     * 输入: "abcabcabcabc"
     * <p>
     * 输出: True
     * <p>
     * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     *
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    /**
     * kmp 算法
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern2(String s) {
        return kmp(s + s, s);
    }

    public boolean kmp(String query, String pattern) {
        int n = query.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int match = -1;
        for (int i = 1; i < n - 1; ++i) {
            while (match != -1 && pattern.charAt(match + 1) != query.charAt(i)) {
                match = fail[match];
            }
            if (pattern.charAt(match + 1) == query.charAt(i)) {
                ++match;
                if (match == m - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 461. 汉明距离
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     * <p>
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     * <p>
     * 注意：
     * 0 ≤ x, y < 231.
     * <p>
     * 示例:
     * <p>
     * 输入: x = 1, y = 4
     * <p>
     * 输出: 2
     * <p>
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     * ↑   ↑
     * <p>
     * 上面的箭头指出了对应二进制位不同的位置。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 为了计算等于 1 的位数，可以将每个位移动到最左侧或最右侧，然后检查该位是否为 1。
     * <p>
     * 更准确的说，应该进行逻辑移位，移入零替换丢弃的位。
     * <p>
     * <p>
     * <p>
     * 这里采用右移位，每个位置都会被移动到最右边。移位后检查最右位的位是否为 1 即可。检查最右位是否为 1，
     * 可以使用取模运算（i % 2）或者 AND 操作（i & 1），这两个操作都会屏蔽最右位以外的其他位。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1)
                distance += 1;
            xor = xor >> 1;
        }
        return distance;
    }

    /**
     * 思路
     * <p>
     * 方法二是逐位移动，逐位比较边缘位置是否为 1。寻找一种更快的方法找出等于 1 的位数。
     * <p>
     * 是否可以像人类直观的计数比特为 1 的位数，跳过两个 1 之间的 0。例如：10001000。
     * <p>
     * 上面例子中，遇到最右边的 1 后，如果可以跳过中间的 0，直接跳到下一个 1，效率会高很多。
     * <p>
     * 这是布赖恩·克尼根位计数算法的基本思想。该算法使用特定比特位和算术运算移除等于 1 的最右比特位。
     * <p>
     * 当我们在 number 和 number-1 上做 AND 位运算时，原数字 number 的最右边等于 1 的比特会被移除。
     * <p>
     * <p>
     * <p>
     * 基于以上思路，通过 2 次迭代就可以知道 10001000 中 1 的位数，而不需要 8 次。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/hamming-distance/solution/yi-ming-ju-chi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance3(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance += 1;
            // remove the rightmost bit of '1'
            xor = xor & (xor - 1);
        }
        return distance;
    }

    /**
     * 463. 岛屿的周长
     * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
     * <p>
     * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，
     * 但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     * <p>
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。
     * 网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
     * 输出：16
     * 解释：它的周长是上面图片中的 16 个黄色的边
     * 示例 2：
     * <p>
     * 输入：grid = [[1]]
     * 输出：4
     * 示例 3：
     * <p>
     * 输入：grid = [[1,0]]
     * 输出：4
     *
     * @param grid
     * @return
     */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }

    public int islandPerimeter2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    ans += dfs(i, j, grid, n, m);
                }
            }
        }
        return ans;
    }

    public int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }

    /**
     * 476. 数字的补数
     * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = 5
     * 输出：2
     * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
     * 示例 2：
     * <p>
     * 输入：num = 1
     * 输出：0
     * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     *
     * @param num
     * @return
     */
    public static int findComplement(int num) {
        int maxBitNum = 0;
        int tmpNum = num;
        while (tmpNum > 0) {
            maxBitNum += 1;
            tmpNum >>= 1;
        }
        return num ^ ((1 << maxBitNum) - 1);
    }

    /**
     * 485. 最大连续 1 的个数
     * 给定一个二进制数组， 计算其中最大连续 1 的个数。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：[1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }


    public static void main(String[] args) {
        System.out.println(findComplement(33));
    }
}
