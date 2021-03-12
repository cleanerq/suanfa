package com.qby.suanfa;

import java.util.*;
import java.util.stream.Collectors;

public class Solution4 {

    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，
     * 请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * 示例 2：
     * <p>
     * 输入：head = [], val = 1
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     * <p>
     * 可以通过哨兵节点去解决它，哨兵节点广泛应用于树和链表中，如伪头、伪尾、标记等，它们是纯功能的，
     * 通常不保存任何数据，其主要目的是使链表标准化，如使链表永不为空、永不无头、简化插入和删除。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements/solution/yi-chu-lian-biao-yuan-su-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode();
        sentinel.next = head;

        ListNode pre = sentinel;
        ListNode cur = pre.next;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
        }

        return sentinel.next;
    }

    /**
     * 哨兵节点 leetcode 解法
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }

    /**
     * 204. 计数质数
     * 统计所有小于非负整数 n 的质数的数量。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     * <p>
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     * <p>
     * 输入：n = 1
     * 输出：0
     * <p>
     * 方法一：枚举
     * 考虑到如果 yy 是 xx 的因数，那么 \frac{x}{y}
     * y
     * x
     * ​
     * 也必然是 xx 的因数，因此我们只要校验 yy 或者 \frac{x}{y}
     * y
     * x
     * ​
     * 即可。而如果我们每次选择校验两者中的较小数，则不难发现较小数一定落在 [2,\sqrt{x}][2,
     * x
     * ​
     * ] 的区间中，因此我们只需要枚举 [2,\sqrt{x}][2,
     * x
     * ​
     * ] 中的所有数即可，这样单次检查的时间复杂度从 O(n)O(n) 降低至了 O(\sqrt{n})O(
     * n
     * ​
     * )。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    /**
     * 枚举方法判断质数
     *
     * @param x
     * @return
     */
    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：埃氏筛
     * 如果 xx 是质数，那么大于 xx 的 xx 的倍数 2x,3x,… 一定不是质数
     * <p>
     * 我们设isPrime[i] 表示数 ii 是不是质数，如果是质数则为 1，否则为 0。
     * 从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），
     * 即 0，这样在运行结束的时候我们即能知道质数的个数。
     *
     * @param n
     * @return
     */
    public int countPrimes2(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 方法三：线性筛
     *
     * @param n
     * @return
     */
    public int countPrimes3(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }


    /**
     * 205. 同构字符串
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * <p>
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * <p>
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
     * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     * <p>
     * 需要我们判断 ss 和 tt 每个位置上的字符是否都一一对应，即 ss 的任意一个字符被 tt 中唯一的字符对应，
     * 同时 tt 的任意一个字符被 ss 中唯一的字符对应。这也被称为「双射」的关系。
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入：s = "egg", t = "add"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "foo", t = "bar"
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：s = "paper", t = "title"
     * 输出：true
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2tMap = new HashMap<>();
        Map<Character, Character> t2sMap = new HashMap<>();

        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();

        for (int i = 0; i < charS.length; i++) {
            if (!s2tMap.containsKey(charS[i])) {
                s2tMap.put(charS[i], charT[i]);
            } else {
                Character tChar = s2tMap.get(charS[i]);
                if (!tChar.equals(charT[i])) {
                    return false;
                }
            }
            if (!t2sMap.containsKey(charT[i])) {
                t2sMap.put(charT[i], charS[i]);
            } else {
                Character sChar = t2sMap.get(charT[i]);
                if (!sChar.equals(charS[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 算法同上 写法不一样
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();

        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

    /**
     * 665. 非递减数列
     * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     * <p>
     * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     * 示例 2:
     * <p>
     * 输入: nums = [4,2,1]
     * 输出: false
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     *
     * @param nums
     * @return
     */
    public static boolean checkPossibility(int[] nums) {
        int count = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
                if (++count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * LCP 18. 早餐组合
     * <p>
     * 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，
     * 一维整型数组 drinks 中记录了每种饮料的价格。小扣的计划选择一份主食和一款饮料，
     * 且花费不超过 x 元。请返回小扣共有多少种购买方案。
     * <p>
     * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/2vYnGI
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 双指针法
     * <p>
     * 一个i从头开始一个j从后开始，只要j符合了，j前面的全都符合
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        // 一个i从头开始一个j从后开始，只要j符合了，j前面的全都符合
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int cnt = 0;
        int m = staple.length, n = drinks.length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (staple[i] + drinks[j] <= x) {
                cnt = (cnt + j + 1) % 1000000007;
                i++;
            } else {
                j--;
            }
        }
        return cnt % 1000000007;
    }

    /**
     * 二分法
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber2(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int cnt = 0;
        int m = staple.length, n = drinks.length;
        for (int i = 0; i < m; i++) {
            int temp = x - staple[i];
            int idx = binarySearch(drinks, temp);
            if (idx == 0) {
                break;
            }
            cnt = (cnt + idx) % 1000000007;
        }
        return cnt % 1000000007;
    }

    public int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] > target) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }


    /**
     * 插入排序
     *
     * @param arr
     */
    public static void charupaixu(int[] arr) {
        int currentItem = 0;
        int position = 0;
        // 遍历数组
        for (int i = 0; i < arr.length; i++) {
            // 取当前值
            currentItem = arr[i];
            position = i;
            // 取当前值向前查找，如果查找完毕或者找到小于等于当前值的值，停止查找
            while (position > 0 && arr[position - 1] > currentItem) {
                // 将查找的值后移
                arr[position] = arr[position - 1];
                position--;
            }
            // 查找停止后，插入
            arr[position] = currentItem;

            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 859. 亲密字符串
     * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，
     * 就返回 true ；否则返回 false 。
     * <p>
     * 交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。
     * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入： A = "ab", B = "ba"
     * 输出： true
     * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。
     * 示例 2：
     * <p>
     * 输入： A = "ab", B = "ab"
     * 输出： false
     * 解释： 你只能交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 不相等。
     * 示例 3:
     * <p>
     * 输入： A = "aa", B = "aa"
     * 输出： true
     * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'a' 生成 "aa"，此时 A 和 B 相等。
     * 示例 4：
     * <p>
     * 输入： A = "aaaaaaabc", B = "aaaaaaacb"
     * 输出： true
     * 示例 5：
     * <p>
     * 输入： A = "", B = "aa"
     * 输出： false
     *
     * @param A
     * @param B
     * @return
     */
    public static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i)
                count[A.charAt(i) - 'a']++;

            for (int c : count)
                if (c > 1) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && A.charAt(first) == B.charAt(second) &&
                    A.charAt(second) == B.charAt(first));
        }
    }

    /**
     * 小扣注意到秋日市集上有一个创作黑白方格画的摊位。摊主给每个顾客提供一个固定在墙上的白色画板，
     * 画板不能转动。画板上有 n * n 的网格。绘画规则为，小扣可以选择任意多行以及任意多列的格子涂成黑色，
     * 所选行数、列数均可为 0。
     * <p>
     * 小扣希望最终的成品上需要有 k 个黑色格子，请返回小扣共有多少种涂色方案。
     * <p>
     * 注意：两个方案中任意一个相同位置的格子颜色不同，就视为不同的方案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ccw6C7
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2, k = 2
     * <p>
     * 输出：4
     * <p>
     * 解释：一共有四种不同的方案：
     * 第一种方案：涂第一列；
     * 第二种方案：涂第二列；
     * 第三种方案：涂第一行；
     * 第四种方案：涂第二行。
     * <p>
     * 示例 2：
     * <p>
     * 输入：n = 2, k = 1
     * <p>
     * 输出：0
     * <p>
     * 解释：不可行，因为第一次涂色至少会涂两个黑格。
     * <p>
     * 示例 3：
     * <p>
     * 输入：n = 2, k = 4
     * <p>
     * 输出：1
     * <p>
     * 解释：共有 2*2=4 个格子，仅有一种涂色方案。
     *
     * @param n
     * @param k
     * @return
     */
    public int paintingPlan(int n, int k) {

        if (k == n * n) return 1;

        int ans = 0;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                int sum = a * n + b * n - a * b;
                if (sum == k) {
                    int x = combination(n, a);
                    int y = combination(n, b);
                    ans += x * y;
                }
            }
        }

        return ans;
    }

    int combination(int n, int m) {
        int ans = 1;
        for (int i = n; i > m; i--) ans *= i;
        for (int i = n - m; i > 0; i--) ans /= i;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(buddyStrings("aab", "aab"));
    }

}
