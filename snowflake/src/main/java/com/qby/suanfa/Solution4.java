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

    /**
     * 1033. 移动石子直到连续
     * 三枚石子放置在数轴上，位置分别为 a，b，c。
     * <p>
     * 每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。
     * 形式上，假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。
     * 那么就可以从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。
     * <p>
     * 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
     * <p>
     * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？
     * 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：a = 1, b = 2, c = 5
     * 输出：[1, 2]
     * 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
     * 示例 2：
     * <p>
     * 输入：a = 4, b = 3, c = 2
     * 输出：[0, 0]
     * 解释：我们无法进行任何移动。
     * <p>
     * 思路：
     * 先将最小数给a，最大数给c（用于判断相对位置）
     * 最大移动次数很简单就是将a,c一步步向b靠拢也就是移动(c-b-1)+(b-a-1)=c-a-2 次
     * 下面讨论最小移动次数即可：
     * 情况I 若a,b,c相邻则无法移动 返回[0,0]
     * 情况II 若a,b,c中有两个相邻 最少移动一次 返回[1,c-a-2]
     * 情况III 若a,b,c中有两个想近（隔一位，也就是a_b或b_c）,最少移动一次，填在空中即可。返回[1,c-a-2]
     * 其他情况都是最少移动两次，先创造一个相近的位置，达到情况III然后填空。返回[2,c-a-2]
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] numMovesStones(int a, int b, int c) {
        int t;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }   //将最小值给a，最大值给c
        if (a == b - 1 && a == c - 2) { //a，b，c相邻无法移动
            return new int[]{0, 0};
        }
        if (b == a + 1 || c == b + 1 || b == a + 2 || b == c - 2) {
            //有两个数相邻或相近（隔一位）注意我们上面已经排除了3个数相邻情况
            return new int[]{1, c - a - 2};
        }
        return new int[]{2, c - a - 2};  //其他情况
    }

    /**
     * 914. 卡牌分组
     * 给定一副牌，每张牌上都写着一个整数。
     * <p>
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     * <p>
     * 每组都有 X 张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回 true。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     * 示例 2：
     * <p>
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 3：
     * <p>
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 4：
     * <p>
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     * 示例 5：
     * <p>
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     * <p>
     * 暴力法
     * <p>
     * 我们从 22 开始，从小到大枚举 XX。
     * <p>
     * 由于每一组都有 XX 张牌，那么 XX 必须是卡牌总数 NN 的约数。
     * <p>
     * 其次，对于写着数字 ii 的牌，如果有 \textit{count}_icount
     * i
     * ​
     * 张，由于题目要求「组内所有的牌上都写着相同的整数」，那么 XX 也必须是 \textit{count}_icount
     * i
     * ​
     * 的约数，即：
     * <p>
     * \textit{count}_i \bmod X == 0
     * count
     * i
     * ​
     * modX==0
     * <p>
     * 所以对于每一个枚举到的 XX，我们只要先判断 XX 是否为 NN 的约数，然后遍历所有牌中存在的数字 ii，
     * 看它们对应牌的数量 \textit{count}_icount
     * i
     * ​
     * 是否满足上述要求。如果都满足等式，则 XX 为符合条件的解，否则需要继续令 XX 增大，枚举下一个数字。
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        int N = deck.length;
        int[] count = new int[10000];
        for (int c : deck) {
            count[c]++;
        }

        List<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < 10000; ++i) {
            if (count[i] > 0) {
                values.add(count[i]);
            }
        }

        for (int X = 2; X <= N; ++X) {
            if (N % X == 0) {
                boolean flag = true;
                for (int v : values) {
                    if (v % X != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 求解最大公约数
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX2(int[] deck) {
        int[] count = new int[10000];
        for (int c : deck) {
            count[c]++;
        }

        int g = -1;
        for (int i = 0; i < 10000; ++i) {
            if (count[i] > 0) {
                if (g == -1) {
                    g = count[i];
                } else {
                    g = gcd(g, count[i]);
                }
            }
        }
        return g >= 2;
    }

    /**
     * 求解最大公约数
     *
     * @param x
     * @param y
     * @return
     */
    public int gcd(int x, int y) {

        return x == 0 ? y : gcd(y % x, x);
    }


    /**
     * 925. 长按键入
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，
     * 而字符可能被输入 1 次或多次。
     * <p>
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），
     * 那么就返回 True。
     * <p>
     * 示例 1：
     * <p>
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     * 示例 2：
     * <p>
     * 输入：name = "saeed", typed = "ssaaedd"
     * 输出：false
     * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     * 示例 3：
     * <p>
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：name = "laiden", typed = "laiden"
     * 输出：true
     * 解释：长按名字中的字符并不是必要的。
     * <p>
     * 双指针法
     *
     * @param name
     * @param typed
     * @return
     */
    public static boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;

        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                j++;
                i++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }

        return i == name.length();
    }

    /**
     * 941. 有效的山脉数组
     * 给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。
     * <p>
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     * <p>
     * arr.length >= 3
     * 在 0 < i < arr.length - 1 条件下，存在 i 使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [2,1]
     * 输出：false
     * 示例 2：
     * <p>
     * 输入：arr = [3,5,5]
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：arr = [0,3,2,1]
     * 输出：true
     * <p>
     * 线性扫描
     *
     * @param arr
     * @return
     */
    public static boolean validMountainArray(int[] arr) {
        int n = arr.length;
        int i = 0;
        while (i + 1 < n && arr[i + 1] > arr[i]) {
            i++;
        }
        if (i == n - 1 || i == 0) {
            return false;
        }

        // 递减扫描
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }

        return i == n - 1;
    }


    /**
     * 507. 完美数
     * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
     * <p>
     * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：28
     * 输出：True
     * 解释：28 = 1 + 2 + 4 + 7 + 14
     * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
     * 示例 2：
     * <p>
     * 输入：num = 6
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：num = 496
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：num = 8128
     * 输出：true
     * 示例 5：
     * <p>
     * 输入：num = 2
     * 输出：false
     * <p>
     * 1 枚举法
     * <p>
     * 我们枚举 n 的所有因数，并计算它们的和。
     * <p>
     * 在枚举时，我们只需要从 1 到 sqrt(n) 进行枚举即可。这是因为如果 n 有一个大于 sqrt(n) 的因数 x，
     * 那么它一定有一个小于 sqrt(n) 的因数 n/x。因此我们可以从 1 到 sqrt(n) 枚举 n 的因数，
     * 当出现一个 n 的因数 x 时，我们还需要算上 n/x。此外还需要考虑特殊情况，即 x = n/x，这时我们不能重复计算。
     *
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }

    /**
     * 680. 验证回文字符串 Ⅱ
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "aba"
     * 输出: True
     * 示例 2:
     * <p>
     * 输入: "abca"
     * 输出: True
     * 解释: 你可以删除c字符。
     *
     * @param s
     * @return
     */
    public static boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return validPalindrome(s, i + 1, j) || validPalindrome(s, i, j - 1);
            }
        }

        return true;
    }

    public static boolean validPalindrome(String s, int l, int h) {
        for (int i = l, j = h; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1784. 检查二进制字符串字段
     * 给你一个二进制字符串 s ，该字符串 不含前导零 。
     * <p>
     * 如果 s 最多包含 一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "1001"
     * 输出：false
     * 解释：字符串中的 1 没有形成一个连续字段。
     * 示例 2：
     * <p>
     * 输入：s = "110"
     * 输出：true
     *
     * @param s
     * @return
     */
    public static boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }

    /**
     * 1736. 替换隐藏数字得到的最晚时间
     * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     * <p>
     * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     * <p>
     * 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：time = "2?:?0"
     * 输出："23:50"
     * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
     * 示例 2：
     * <p>
     * 输入：time = "0?:3?"
     * 输出："09:39"
     * 示例 3：
     * <p>
     * 输入：time = "1?:22"
     * 输出："19:22"
     * <p>
     * 贪心算法
     *
     * @param time
     * @return
     */
    public static String maximumTime(String time) {
        String[] st = new String[]{"2", "3", ":", "5", "9"};
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == '?') {
                if (i == 0 && time.charAt(1) != '?' && time.charAt(1) >= '4') {
                    stb.append("1");
                } else if (i == 1 && stb.toString().charAt(i - 1) != '2') {
                    stb.append("9");
                } else {
                    stb.append(st[i]);
                }
            } else {
                stb.append(time.charAt(i));
            }
        }
        return stb.toString();
    }

    /**
     * 1013. 将数组分成和相等的三个部分
     * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
     * <p>
     * 形式上，如果可以找出索引 i+1 < j 且满足 A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1] 就可以将数组三等分。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[0,2,1,-6,6,-7,9,1,2,0,1]
     * 输出：true
     * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
     * 示例 2：
     * <p>
     * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：[3,3,6,5,-2,2,5,1,-9,4]
     * 输出：true
     * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
     *
     * @param arr
     * @return
     */
    public static boolean canThreePartsEqualSum(int[] arr) {
        int s = 0;
        for (int num : arr) {
            s += num;
        }
        if (s % 3 != 0) {
            return false;
        }
        int target = s / 3;
        int n = arr.length, i = 0, cur = 0;
        while (i < n) {
            cur += arr[i];
            if (cur == target) {
                break;
            }
            ++i;
        }
        if (cur != target) {
            return false;
        }
        int j = i + 1;
        while (j + 1 < n) {  // 需要满足最后一个数组非空
            cur += arr[j];
            if (cur == target * 2) {
                return true;
            }
            ++j;
        }
        return false;
    }

    /**
     * 数组每一位n是前面n-1位想加的结果
     *
     * @param arr
     * @return
     */
    public static boolean canThreePartsEqualSum1(int[] arr) {
        int len = arr.length - 1;
        for (int i = 1; i <= len; ++i) {
            arr[i] += arr[i - 1];
        }

        if (arr[len] % 3 != 0) return false;

        int eqsum = arr[len] / 3, times = 1;
        for (int i = 0; i < len && times < 3; ++i) {
            if (arr[i] == eqsum * times) ++times;
        }
        return times == 3;
    }

    /**
     * 面试题 05.03. 翻转数位
     * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     * <p>
     * 示例 1：
     * <p>
     * 输入: num = 1775(110111011112)
     * 输出: 8
     * 示例 2：
     * <p>
     * 输入: num = 7(01112)
     * 输出: 4
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        String s = Integer.toBinaryString(num);
        String[] arr = s.split("0");
        if (num == -1) {
            return 32;
        }
        if (arr.length < 1) {
            return arr.length + 1;
        }
        int max = arr[0].length();
        int res = max + 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].length() + arr[i].length() > max) {
                max = arr[i - 1].length() + arr[i].length();
                res = max + 1;
            }
        }
        return res;
    }

    /**
     * 747. 至少是其他数字两倍的最大数
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * <p>
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * <p>
     * 如果是，则返回最大元素的索引，否则返回-1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [3, 6, 1, 0]
     * 输出: 1
     * 解释: 6是最大的整数, 对于数组中的其他整数,
     * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入: nums = [1, 2, 3, 4]
     * 输出: -1
     * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     *
     * @param nums
     * @return
     */
    public static int dominantIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > nums[maxIndex])
                maxIndex = i;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (maxIndex != i && nums[maxIndex] < 2 * nums[i])
                return -1;
        }
        return maxIndex;
    }


    /**
     * 874. 模拟行走机器人
     * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。
     * 该机器人可以接收以下三种类型的命令 commands ：
     * <p>
     * -2 ：向左转 90 度
     * -1 ：向右转 90 度
     * 1 <= x <= 9 ：向前移动 x 个单位长度
     * 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。
     * <p>
     * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
     * <p>
     * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
     * <p>
     * <p>
     * 注意：
     * <p>
     * 北表示 +Y 方向。
     * 东表示 +X 方向。
     * 南表示 -Y 方向。
     * 西表示 -X 方向。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：commands = [4,-1,3], obstacles = []
     * 输出：25
     * 解释：
     * 机器人开始位于 (0, 0)：
     * 1. 向北移动 4 个单位，到达 (0, 4)
     * 2. 右转
     * 3. 向东移动 3 个单位，到达 (3, 4)
     * 距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
     * 示例 2：
     * <p>
     * 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
     * 输出：65
     * 解释：机器人开始位于 (0, 0)：
     * 1. 向北移动 4 个单位，到达 (0, 4)
     * 2. 右转
     * 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
     * 4. 左转
     * 5. 向北走 4 个单位，到达 (1, 8)
     * 距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65
     *
     * @param commands
     * @param obstacles
     * @return
     */
    public static int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int ans = 0;
        for (int cmd : commands) {
            if (cmd == -2)  //left
                di = (di + 3) % 4;
            else if (cmd == -1)  //right
                di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 219. 存在重复元素 II
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
     * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     * <p>
     * 线性检索
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k && i + j < nums.length; j++) {
                if (nums[i + j] == nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 平衡二叉树
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 散列表
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 482. 密钥格式化
     * 有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。
     * <p>
     * 给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，
     * 第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。两个分组之间需要用 '-'（破折号）隔开，
     * 并且将所有的小写字母转换为大写字母。
     * <p>
     * 给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：S = "5F3Z-2e-9-w", K = 4
     * 输出："5F3Z-2E9W"
     * 解释：字符串 S 被分成了两个部分，每部分 4 个字符；
     * 注意，两个额外的破折号需要删掉。
     * 示例 2：
     * <p>
     * 输入：S = "2-5g-3-J", K = 2
     * 输出："2-5G-3J"
     * 解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，
     * 其余部分皆为 2 个字符。
     *
     * @param S
     * @param K
     * @return
     */
    public static String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        String t = S.replaceAll("-", "");
        int s = t.length() % K;
        int i = 0;
        while (i < t.length() && i + K <= t.length()) {
            sb.append("-");
            if (i == 0 && s != 0) {
                sb.append(t.substring(i, s).toUpperCase());
                i = i + s;
            } else {
                sb.append(t.substring(i, i + K).toUpperCase());
                i = i + K;
            }
        }
        if (sb.toString().equals("")) {
            if (!"".equals(t)) {
                sb.append(S);
            }
            return sb.toString();
        }

        return sb.toString().substring(1);
    }

    /**
     * 倒序遍历
     *
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting2(String S, int K) {
        StringBuilder s = new StringBuilder();
        // 统计已打印字符个数
        int count = 0;
        // 倒序遍历字符串
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) != '-') {
                // 计算什么时候打印分隔符
                if (count != 0 && count % K == 0) {
                    s.append('-');
                }
                // 转为大写字母添加
                s.append(Character.toUpperCase(S.charAt(i)));
                count++;
            }
        }
        // 反转字符串
        return s.reverse().toString();
    }

    /**
     * 使用char数组
     *
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting3(String S, int K) {
        char[] array = S.toCharArray();
        // 统计字符的个数
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != '-') {
                // 将小写字符转为大写字符
                if (array[i] > 'Z') {
                    array[i] -= 32;
                }
                count++;
            }
        }
        // 没有字符返回空串
        if (count == 0) {
            return "";
        }
        // 计算分隔符的个数
        int separator = count / K;
        // 如果正好整除，分隔符个数 - 1
        separator = count % K == 0 ? separator - 1 : separator;
        // 存储结果的数组
        char[] result = new char[count + separator];
        // 指向结果数组的下标，倒序赋值
        int index = result.length - 1;
        // 统计已打印字符个数
        int letter = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (array[i] != '-') {
                // 计算什么时候打印分隔符
                if (letter != 0 && letter % K == 0) {
                    result[index--] = '-';
                }
                result[index--] = array[i];
                letter++;
            }
        }
        return new String(result);
    }


    /**
     * 819. 最常见的单词
     * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
     * <p>
     * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
     * <p>
     * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入:
     * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
     * banned = ["hit"]
     * 输出: "ball"
     * 解释:
     * "hit" 出现了3次，但它是一个禁用的单词。
     * "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
     * 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
     * "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";

        Set<String> banset = new HashSet();
        for (String word : banned) banset.add(word);
        Map<String, Integer> count = new HashMap();

        String ans = "";
        int ansfreq = 0;

        StringBuilder word = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (word.length() > 0) {
                String finalword = word.toString();
                if (!banset.contains(finalword)) {
                    count.put(finalword, count.getOrDefault(finalword, 0) + 1);
                    if (count.get(finalword) > ansfreq) {
                        ans = finalword;
                        ansfreq = count.get(finalword);
                    }
                }
                word = new StringBuilder();
            }
        }

        return ans;
    }

    public static String mostCommonWord2(String paragraph, String[] banned) {

        paragraph += ".";
        Set<String> banSet = new HashSet<>();
        for (String s : banned) {
            banSet.add(s);
        }

        Map<String, Integer> count = new HashMap<>();
        String ans = "";
        int ansfreq = 0;

        StringBuilder word = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (word.length() > 0) {
                String finalWord = word.toString();
                if (!banSet.contains(finalWord)) {
                    count.put(finalWord, count.getOrDefault(finalWord, 0) + 1);
                    if (count.get(finalWord) > ansfreq) {
                        ans = finalWord;
                        ansfreq = count.get(finalWord);
                    }
                }
                word = new StringBuilder();
            }
        }


        return ans;
    }


    /**
     * 441. 排列硬币
     * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
     * <p>
     * 给定一个数字 n，找出可形成完整阶梯行的总行数。
     * <p>
     * n 是一个非负整数，并且在32位有符号整型的范围内。
     * <p>
     * 示例 1:
     * <p>
     * n = 5
     * <p>
     * 硬币可排列成以下几行:
     * ¤
     * ¤ ¤
     * ¤ ¤
     * <p>
     * 因为第三行不完整，所以返回2.
     * 示例 2:
     * <p>
     * n = 8
     * <p>
     * 硬币可排列成以下几行:
     * ¤
     * ¤ ¤
     * ¤ ¤ ¤
     * ¤ ¤
     * <p>
     * 因为第四行不完整，所以返回3.
     * <p>
     * 二分法
     *
     * @param n
     * @return
     */
    public static int arrangeCoins(int n) {
        int l = 0, h = n;
        while (l <= h) {
            long mid = (h - l) / 2 + l;
            long sum = (mid + 1) * mid / 2;
            if (sum == n) {
                return (int) mid;
            } else if (sum > n) {
                h = (int) mid - 1;
            } else {
                l = (int) mid + 1;
            }
        }

        return h;
    }

    /**
     * 645. 错误的集合
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，
     * 导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
     * 导致集合 丢失了一个数字 并且 有一个数字重复 。
     * <p>
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * <p>
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,2,4]
     * 输出：[2,3]
     * 示例 2：
     * <p>
     * 输入：nums = [1,1]
     * 输出：[1,2]
     * <p>
     * 线性遍历
     *
     * @param nums
     * @return
     */
    public static int[] findErrorNums(int[] nums) {
        Set<Integer> setE = new HashSet<>();
        int cf = 0;
        for (int num : nums) {
            if (setE.contains(num)) {
                cf = num;
            }
            setE.add(num);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!setE.contains(i)) {
                return new int[]{cf, i};
            }
        }

        return new int[]{};
    }

    /**
     * 排序 numsnums 数组后，相等的两个数字将会连续出现。此外，检查相邻的两个数字是否只相差 1 可以找到缺失数字。
     *
     * @param nums
     * @return
     */
    public static int[] findErrorNums2(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, missing = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                dup = nums[i];
            else if (nums[i] > nums[i - 1] + 1)
                missing = nums[i - 1] + 1;
        }
        return new int[]{dup, nums[nums.length - 1] != nums.length ? nums.length : missing};
    }

    /**
     * 278. 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * <p>
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * <p>
     * 示例:
     * <p>
     * 给定 n = 5，并且 version = 4 是第一个错误的版本。
     * <p>
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     * <p>
     * 所以，4 是第一个错误的版本。
     * <p>
     * 二分查找
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1, h = n;
        int mid = 0;
        while (l < h) {
            mid = (h - l) / 2 + l;
//            if (isBadVersion(mid)) {
//                h = mid;
//            } else {
//                l = mid + 1;
//            }
        }
        return l;
    }


    /**
     * 1668. 最大重复子字符串
     * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，
     * 那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。
     * 如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
     * <p>
     * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：sequence = "ababc", word = "ab"
     * 输出：2
     * 解释："abab" 是 "ababc" 的子字符串。
     * 示例 2：
     * <p>
     * 输入：sequence = "ababc", word = "ba"
     * 输出：1
     * 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
     * 示例 3：
     * <p>
     * 输入：sequence = "ababc", word = "ac"
     * 输出：0
     * 解释："ac" 不是 "ababc" 的子字符串。
     *
     * @param sequence
     * @param word
     * @return
     */
    public int maxRepeating(String sequence, String word) {
        int count = 0;
        StringBuilder st = new StringBuilder(word);
        while (sequence.contains(st.toString())) {
            count++;
            st.append(word);
        }
        return count;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：n = 7
     * 输出：21
     * 示例 3：
     * <p>
     * 输入：n = 0
     * 输出：1
     * <p>
     * 动态规划 fn = fn-1 + fn-2
     *
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int f1 = 1, f2 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

    /**
     * 724. 寻找数组的中心下标
     * 给你一个整数数组 nums，请编写一个能够返回数组 “中心下标” 的方法。
     * <p>
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 如果数组不存在中心下标，返回 -1 。如果数组有多个中心下标，应该返回最靠近左边的那一个。
     * <p>
     * 注意：中心下标可能出现在数组的两端。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 中心下标是 3 。
     * 左侧数之和 (1 + 7 + 3 = 11)，
     * 右侧数之和 (5 + 6 = 11) ，二者相等。
     * 示例 2：
     * <p>
     * 输入：nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心下标。
     * 示例 3：
     * <p>
     * 输入：nums = [2, 1, -1]
     * 输出：0
     * 解释：
     * 中心下标是 0 。
     * 下标 0 左侧不存在元素，视作和为 0 ；
     * 右侧数之和为 1 + (-1) = 0 ，二者相等。
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if ((sum * 2 + nums[i]) == total) {
                return i;
            }
            sum = sum + nums[i];
        }
        return -1;
    }

    /**
     * 228. 汇总区间
     * 给定一个无重复元素的有序整数数组 nums 。
     * <p>
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
     * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * <p>
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * <p>
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     *
     * @param nums
     * @return
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;

        StringBuilder sb = null;

        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            sb = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                sb.append("->");
                sb.append(nums[high]);
            }
            ret.add(sb.toString());
        }
        return ret;
    }

    /**
     * 231. 2的幂
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     * <p>
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     * <p>
     * 输入: 218
     * 输出: false
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (-x)) == x;
    }

    /**
     * 2 的幂二进制表示只含有一个 1。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (x - 1)) == 0;
    }




    public static void main(String[] args) {
        System.out.println(summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    }
}
