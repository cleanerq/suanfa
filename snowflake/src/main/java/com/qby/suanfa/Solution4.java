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


    /**
     * 605. 种花问题
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，
     * 它们会争夺水源，两者都会死去。
     * <p>
     * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1
     * 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：flowerbed = [1,0,0,0,1], n = 1
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：flowerbed = [1,0,0,0,1], n = 2
     * 输出：false
     * <p>
     * [1,0,0,0,0,1]
     * <p>
     * [1,0,0,0,0,0,1]
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count += (i - prev - 2) / 2;
                }
                if (count >= n) {
                    return true;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count += (m + 1) / 2;
        } else {
            count += (m - prev - 1) / 2;
        }
        return count >= n;
    }

    /**
     * 解法2
     * 数学归纳法，很简单推出来
     * <p>
     * 统计连续的0的区间，分别有多少个连续的0即可。对于每一段0区间，都可以根据公式直接算出可以种几朵花。
     * <p>
     * 公式可以通过数学归纳法推出来，很简单：
     * <p>
     * 1）对于中间的0区间：
     * <p>
     * 1~2个0：可种0朵；
     * <p>
     * 3~4个：可种1朵；
     * <p>
     * 5~6个：可种2朵；
     * <p>
     * ...
     * <p>
     * count个：可种 (count-1)/2 朵
     * <p>
     * 2）对于两头的0区间，由于左边、右边分别没有1的限制，可种花朵数稍有不同。
     * <p>
     * 为了代码流程的统一，可以在数组最左边、数组最右边分别补1个0，意味着花坛左边、右边没有花。
     * <p>
     * 这样公式就跟1）相同了。
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public static boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) return n == 0;

        int countOfZero = 1; // 当前全0区段中连续0的数量，刚开始预设1个0，因为开头花坛的最左边没有花，可以认为存在一个虚无的0
        int canPlace = 0; // 可以种的花的数量
        for (int bed : flowerbed) {
            if (bed == 0) { // 遇到0，连续0的数量+1
                countOfZero++;
            } else { // 遇到1，结算上一段连续的0区间，看能种下几盆花：(countOfZero-1)/2
                canPlace += (countOfZero - 1) / 2;
                if (canPlace >= n) return true;
                countOfZero = 0; // 0的数量清零，开始统计下一个全0分区
            }
        }
        // 最后一段0区还未结算：
        countOfZero++; // 最后再预设1个0，因为最后花坛的最右边没有花，可以认为存在一个虚无的0
        canPlace += (countOfZero - 1) / 2;

        return canPlace >= n;
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * <p>
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * 输出：1
     * 示例 2：
     * <p>
     * 输入：n = 5
     * 输出：5
     * <p>
     * 递归法：
     * 原理： 把 f(n)f(n) 问题的计算拆分成 f(n-1)f(n−1) 和 f(n-2)f(n−2) 两个子问题的计算，
     * 并递归，以 f(0)f(0) 和 f(1)f(1) 为终止条件。
     * 缺点： 大量重复的递归计算，例如 f(n)f(n) 和 f(n - 1)f(n−1) 两者向下递归需要
     * 各自计算 f(n - 2)f(n−2) 的值。
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        int res = 0;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        res = fib(n - 1) + fib(n - 2);

        return res % 1000000007;
    }

    /**
     * 记忆化递归法：
     * 原理： 在递归法的基础上，新建一个长度为 nn 的数组，用于在递归时存储 f(0)f(0) 至 f(n)f(n) 的数字值，
     * 重复遇到某数字则直接从数组取用，避免了重复的递归计算。
     * 缺点： 记忆化存储需要使用 O(N)O(N) 的额外空间。
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/solution/mian-shi-ti-10-i-fei-bo-na-qi-shu-lie-dong-tai-gui/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        int[] sz = new int[n + 1];
        int res = dtdgf(n, sz);

        return res % 1000000007;
    }

    public static int dtdgf(int n, int[] sz) {
        int res = 0;
        if (n == 0) {
            sz[0] = 0;
            return 0;
        } else if (n == 1) {
            sz[1] = 1;
            return 1;
        } else if (sz[n - 1] != 0) {
            return sz[n - 1];
        }

        res = fib(n - 1) + fib(n - 2);
        sz[n - 1] = res;

        return res;
    }

    /**
     * 动态规划法
     * 原理： 以斐波那契数列性质 f(n + 1) = f(n) + f(n - 1)f(n+1)=f(n)+f(n−1) 为转移方程。
     *
     * @param n
     * @return
     */
    public static int fib3(int n) {
        int a = 0, b = 1, sum = 0;

        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    /**
     * 414. 第三大的数
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，
     * 则返回数组中最大的数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[3, 2, 1]
     * 输出：1
     * 解释：第三大的数是 1 。
     * 示例 2：
     * <p>
     * 输入：[1, 2]
     * 输出：2
     * 解释：第三大的数不存在, 所以返回最大的数 2 。
     * 示例 3：
     * <p>
     * 输入：[2, 2, 3, 1]
     * 输出：1
     * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
     * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
     * <p>
     * 自己写的
     *
     * @param nums
     * @return
     */
    public static int thirdMax(int[] nums) {
        Arrays.sort(nums);

        int xh = 1;
        int pre = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (pre == nums[i]) {
                continue;
            } else {
                pre = nums[i];
                xh++;
            }
            if (xh == 3) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 三个数相比
     *
     * @param nums
     * @return
     */
    public static int thirdMax2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        long firM = Long.MIN_VALUE;
        long secM = Long.MIN_VALUE;
        long thiM = Long.MIN_VALUE;

        for (int i : nums) {
            if (i > firM) {
                thiM = secM;
                secM = firM;
                firM = i;
            } else if (firM == i) {
                continue;
            } else if (i > secM) {
                thiM = secM;
                secM = i;
            } else if (i == secM) {
                continue;
            } else if (i > thiM) {
                thiM = i;
            }
        }

        return thiM == Long.MIN_VALUE ? (int) firM : (int) thiM;
    }


    /**
     * 面试题 08.01. 三步问题
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     * <p>
     * 示例1:
     * <p>
     * 输入：n = 3
     * 输出：4
     * 说明: 有四种走法
     * 示例2:
     * <p>
     * 输入：n = 5
     * 输出：13
     * <p>
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int waysToStep(int n) {
        int f1 = 1;
        int f2 = 2;
        int f3 = 4;

        if (n == 1) {
            return f1;
        } else if (n == 2) {
            return f2;
        } else if (n == 3) {
            return f3;
        }

        int f4 = 0;
        for (int i = 4; i <= n; i++) {
            f4 = (f1 + (f2 + f3) % 1000000007) % 1000000007;
            f1 = f2;
            f2 = f3;
            f3 = f4;
        }

        return f4;
    }

    /**
     * 434. 字符串中的单词数
     * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
     * <p>
     * 请注意，你可以假定字符串里不包括任何不可打印的字符。
     * <p>
     * 示例:
     * <p>
     * 输入: "Hello, my name is John"
     * 输出: 5
     * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
     * <p>
     * 使用语言内置函数 【通过】
     *
     * @param s
     * @return
     */
    public int countSegments(String s) {
        String str = s.trim();
        if (str.length() == 0) {
            return 0;
        }

        return str.split("\\s+").length;
    }

    /**
     * 方法二：原地法 【通过】
     *
     * @param s
     * @return
     */
    public int countSegments2(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                ans++;
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        System.out.println(waysToStep(5));
    }
}
