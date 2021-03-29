package com.qby.suanfa;

import java.util.*;

/**
 * from 118
 */
public class Solution3 {

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * <p>
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例:
     * <p>
     * 输入: 5
     * 输出:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> tList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    tList.add(1);
                } else {
                    tList.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(tList);
        }
        return res;
    }

    /**
     * 119. 杨辉三角 II
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     * <p>
     * <p>
     * <p>
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例:
     * <p>
     * 输入: 3
     * 输出: [1,3,3,1]
     * <p>
     * 滚动数组
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {

        List<Integer> pre = new ArrayList<Integer>();

        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> tList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    tList.add(1);
                } else {
                    tList.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = tList;
        }

        return pre;
    }

    /**
     * 倒着计算
     * 只用一个数组实现
     * 当前行第 ii 项的计算只与上一行第 i-1i−1 项及第 ii 项有关。因此我们可以倒着计算当前行，
     * 这样计算到第 ii 项时，第 i-1i−1 项仍然是上一行的值
     *
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            row.add(0);
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     * <p>
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 暴力法
     * 我们需要找出给定数组中两个数字之间的最大差值（即，最大利润）。
     * 此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）。
     * <p>
     * 形式上，对于每组 ii 和 jj（其中 j > ij>i）我们需要找出
     * max(prices[j]−prices[i])。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    /**
     * 因此，我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：
     * 如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？当考虑完所有天数之时，我们就得到了最好的答案。
     * <p>
     * 一次遍历
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int maxprofit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }

        return maxprofit;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出,
     * 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出,
     * 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出,
     * 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 因为交易次数不受限，如果可以把所有的上坡全部收集到，一定是利益最大化的
     *
     * @param arr
     * @return
     */
    public int maxProfitV2(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;

        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                ans = ans + arr[i] - arr[i - 1];
            }
        }

        return ans;
    }

    /**
     * 动态规划
     * <p>
     * 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，
     * dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）。
     * <p>
     * 转移方程：
     * dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
     * dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
     *
     * @param prices
     * @return
     */
    public int maxProfitV22(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfitV23(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    /**
     * 贪心算法
     *
     * @param prices
     * @return
     */
    public int maxProfitV31(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    /**
     * 125. 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "race a car"
     * 输出: false
     * <p>
     * 方法一：筛选 + 判断
     * 判断的方法有两种。第一种是使用语言中的字符串翻转 API
     * 得到 \textit{sgood}sgood 的逆序字符串 \textit{sgood\_rev}sgood_rev，只要这两个字符串相同，
     * 那么 \textit{sgood}sgood 就是回文串。
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();

        s.chars().forEach(c -> {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase((char) c));
            }
        });
        StringBuilder rvSb = new StringBuilder(sb).reverse();

        return sb.toString().equals(rvSb.toString());
    }

    /**
     * 双指针
     * <p>
     * 左右指针
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        StringBuilder sb = new StringBuilder();
        s.chars().forEach(c -> {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase((char) c));
            }
        });
        String str = sb.toString();
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    /**
     * 方法二：在原字符串上直接判断
     * 我们每次将指针移到下一个字母字符或数字字符，再判断这两个指针指向的字符是否相同。
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome3(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            }
            if (i < j) {
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                    System.out.println(i);
                    System.out.println(j);
                    System.out.println(Character.toLowerCase(s.charAt(i)));
                    System.out.println(Character.toLowerCase(s.charAt(j)));
                    return false;
                }
                ++i;
                --j;
            }
        }
        return true;
    }

    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: [4,1,2,1,2]
     * 输出: 4
     * <p>
     * 集合计数法
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() == 1) {
                return next.getKey();
            }
        }
        return 0;
    }

    /**
     * 异或运算
     * 如何才能做到线性时间复杂度和常数空间复杂度呢？
     * <p>
     * 答案是使用位运算。对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
     * <p>
     * 任何数和 00 做异或运算，结果仍然是原来的数，即 a⊕0=a。
     * 任何数和其自身做异或运算，结果是 00，即 a⊕a=0。
     * 异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * <p>
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
     * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     * <p>
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * 示例 2：
     * <p>
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     * 示例 3：
     * <p>
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     * <p>
     * 哈希表解法
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                return new int[]{map.get(numbers[i]) + 1, i + 1};
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return new int[]{};
    }

    /**
     * 方法一：二分查找方法
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{};
    }

    /**
     * 方法二：双指针 最优
     * <p>
     * 时间复杂度：O(n)O(n)，其中 nn 是数组的长度。两个指针移动的总次数最多为 nn 次。
     * <p>
     * 空间复杂度：O(1)O(1)。
     * <p>
     * 初始时两个指针分别指向第一个元素位置和最后一个元素的位置。每次计算两个指针指向的两个元素之和，
     * 并和目标值比较。如果两个元素之和等于目标值，则发现了唯一解。如果两个元素之和小于目标值，则将左侧指针右移一位。
     * 如果两个元素之和大于目标值，则将右侧指针左移一位。移动指针之后，重复上述操作，直到找到答案。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum3(int[] numbers, int target) {
        int j = 0;
        int k = numbers.length - 1;
        while (j <= k) {
            if (numbers[j] + numbers[k] == target) {
                return new int[]{j + 1, k + 1};
            } else if (numbers[j] + numbers[k] > target) {
                --k;
            } else if (numbers[j] + numbers[k] < target) {
                ++j;
            }
        }
        return new int[]{};
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[3,2,3]
     * 输出：3
     * 示例 2：
     * <p>
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     * 排序法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 哈希计数法
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        // 统计每个元素出现的次数
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        int target = nums.length / 2 + 1;

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (target <= entry.getValue()) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * Boyer-Moore 投票算法
     * 思路
     * <p>
     * 如果我们把众数记为 +1+1，把其他数记为 -1−1，将它们全部加起来，显然和大于 0，
     * 从结果本身我们可以看出众数比其他数多。
     * <p>
     * 算法
     * <p>
     * Boyer-Moore 算法的本质和方法四中的分治十分类似。我们首先给出 Boyer-Moore 算法的详细步骤：
     * <p>
     * 我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
     * <p>
     * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，
     * 我们先将 x 的值赋予 candidate，随后我们判断 x：
     * <p>
     * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     * <p>
     * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     * <p>
     * 在遍历完成后，candidate 即为整个数组的众数。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * 171. Excel表列序号
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     * <p>
     * 例如，
     * <p>
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     * 示例 1:
     * <p>
     * 输入: "A"
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: "AB"
     * 输出: 28
     * 示例 3:
     * <p>
     * 输入: "ZY"
     * 输出: 701
     * <p>
     * 数学公式 求幂次方
     *
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        int cs = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            int bcs = (int) chars[i] - 64;
            int mul = bcs * (int) Math.pow(26, cs);
            sum = sum + mul;
            --cs;
        }
        return sum;
    }

    /**
     * 另一种解法
     *
     * @param s
     * @return
     */
    public static int titleToNumber2(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int num = chars[i] - 'A' + 1;
            sum = sum * 26 + num;
        }

        return sum;
    }

    /**
     * 172. 阶乘后的零
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     * 示例 2:
     * <p>
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * <p>
     * 方法一：直接计算结果 然后看最后零占几位 最慢
     * 方法二：计算因子 5
     * 计算5的个数
     *
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int curInt = i;
            while (curInt % 5 == 0) {
                zeroCount++;
                curInt = curInt / 5;
            }
        }

        return zeroCount;
    }

    /**
     * 方法三：高效的计算因子 5
     *
     * @param n
     * @return
     */
    public static int trailingZeroes2(int n) {
        int zeroCount = 0;
        while (n > 0) {
            n = n / 5;
            zeroCount = zeroCount + n;
        }
        return zeroCount;
    }

    /**
     * 190. 颠倒二进制位
     * 颠倒给定的 32 位无符号整数的二进制位。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: 00000010100101000001111010011100
     * 输出: 00111001011110000010100101000000
     * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     * 示例 2：
     * <p>
     * 输入：11111111111111111111111111111101
     * 输出：10111111111111111111111111111111
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     * 因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
     *
     * @param n
     * @return
     */
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 低位向高位移动
            ans <<= 1;
            ans += 1 & n;
            n = n >> 1;
        }
        return ans;
    }

    /**
     * 191. 位1的个数
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数
     * （也被称为汉明重量）。
     * <p>
     * <p>
     * <p>
     * 提示：
     * <p>
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
     * 并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 如果多次调用这个函数，你将如何优化你的算法？
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：00000000000000000000000000001011
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     * 示例 2：
     * <p>
     * 输入：00000000000000000000000010000000
     * 输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     * 示例 3：
     * <p>
     * 输入：11111111111111111111111111111101
     * 输出：31
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 输入必须是长度为 32 的 二进制串 。
     * <p>
     * 掩码进行移位处理 比较的数字不变
     *
     * @param n
     * @return
     */
    public static int hammingWeight2(int n) {
        int ans = 0;
        int a = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & a) != 0) {
                ++ans;
            }
            a <<= 1;
        }
        return ans;
    }

    /**
     * n与n-1 做与运算
     *
     * @param n
     * @return
     */
    public static int hammingWeight3(int n) {
        int ans = 0;

        while (n != 0) {
            n &= n - 1;
            System.out.println(n);
            ans++;
        }

        return ans;
    }

    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：
     * <p>
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * 示例 2：
     * <p>
     * 输入：n = 2
     * 输出：false
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNum(n);
        }
        return n == 1;
    }

    private int getNum(int n) {
        int totalNum = 0;
        while (n > 0) {
            int sInt = n % 10;
            n = n / 10;
            totalNum = totalNum + sInt * sInt;
        }
        return totalNum;
    }

    /**
     * 快指针 慢指针 方法
     * <p>
     * 快指针 一次走两步
     * 慢指针 一次走一步
     *
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = getNum(n);
        while (fast != 1 && slow != fast) {
            slow = getNum(slow);
            fast = getNum(getNum(fast));
        }

        return fast == 1;
    }

    private static Set<Integer> cycleMembers =
            new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

    /**
     * 如果这样做，您会发现只有一个循环：4 \rightarrow 16 \rightarrow 37 \rightarrow 58 \rightarrow 89 \rightarrow 145 \rightarrow 42 \rightarrow 20 \rightarrow 44→16→37→58→89→145→42→20→4。所有其他数字都在进入这个循环的链上，或者在进入 11 的链上。
     * <p>
     * 因此，我们可以硬编码一个包含这些数字的散列集，如果我们达到其中一个数字，那么我们就知道在循环中。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNum(n);
        }
        return n == 1;
    }

    /**
     * 367. 有效的完全平方数
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * <p>
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = 16
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：num = 14
     * 输出：false
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }

        long l = 2, h = num / 2, m = 0, pf = 0;
        while (l <= h) {
            m = (h - l) / 2 + l;
            pf = m * m;
            if (pf == num) {
                return true;
            } else if (pf > num) {
                h = m - 1;
            } else if (pf < num) {
                l = m + 1;
            }
        }
        return false;
    }

    /**
     * 解法二 官方解法 二分法
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare2(int num) {
        if (num < 2) {
            return true;
        }

        long left = 2, right = num / 2, x, guessSquared;
        while (left <= right) {
            x = left + (right - left) / 2;
            guessSquared = x * x;
            if (guessSquared == num) {
                return true;
            }
            if (guessSquared > num) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }
        return false;
    }


    /**
     * 1346. 检查整数及其两倍数是否存在
     * 给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
     * <p>
     * 更正式地，检查是否存在两个下标 i 和 j 满足：
     * <p>
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [10,2,5,3]
     * 输出：true
     * 解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。
     * 示例 2：
     * <p>
     * 输入：arr = [7,1,14,11]
     * 输出：true
     * 解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。
     * 示例 3：
     * <p>
     * 输入：arr = [3,1,7,11]
     * 输出：false
     * 解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
     *
     * @param arr
     * @return
     */
    public static boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        int p = 0;
        for (int i = 0; i < arr.length; i++) {
            p = arr[i] * 2;
            if (map.containsKey(p)) {
                Integer j = map.get(p);
                if (i != j.intValue()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 排序及双指针法
     *
     * @param arr
     * @return
     */
    public boolean checkIfExist2(int[] arr) {
        if (arr == null || arr.length == 0) return false;

        Arrays.sort(arr);
        int len = arr.length;
        int pointer = 0;
        int num = 0;


        for (int i = 0; i < len; i++) {
            num = arr[i] * 2;
            while (pointer < len && num > arr[pointer]) pointer++;
            if (pointer < len && pointer != i && num == arr[pointer]) return true;
            else if (pointer == len) break;
        }
        return false;
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     * <p>
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * <p>
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        if ("".equals(s.trim())) {
            return "";
        }

        StringBuilder st = new StringBuilder();
        String[] sz = s.split(" ");
        for (int i = sz.length - 1; i >= 0; i--) {
            if (!" ".equals(sz[i]) && !"".equals(sz[i])) {
                st.append(" ");
                st.append(sz[i]);
            }
        }
        return st.toString().substring(1);
    }

    /**
     * 1566. 重复至少 K 次且长度为 M 的模式
     * 给你一个正整数数组 arr，请你找出一个长度为 m 且在数组中至少重复 k 次的模式。
     * <p>
     * 模式 是由一个或多个值组成的子数组（连续的子序列），连续 重复多次但 不重叠 。 模式由其长度和重复次数定义。
     * <p>
     * 如果数组中存在至少重复 k 次且长度为 m 的模式，则返回 true ，否则返回  false 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [1,2,4,4,4,4], m = 1, k = 3
     * 输出：true
     * 解释：模式 (4) 的长度为 1 ，且连续重复 4 次。注意，模式可以重复 k 次或更多次，但不能少于 k 次。
     * 示例 2：
     * <p>
     * 输入：arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
     * 输出：true
     * 解释：模式 (1,2) 长度为 2 ，且连续重复 2 次。另一个符合题意的模式是 (2,1) ，同样重复 2 次。
     * 示例 3：
     * <p>
     * 输入：arr = [1,2,1,2,1,3], m = 2, k = 3
     * 输出：false
     * 解释：模式 (1,2) 长度为 2 ，但是只连续重复 2 次。不存在长度为 2 且至少重复 3 次的模式。
     * 示例 4：
     * <p>
     * 输入：arr = [1,2,3,1,2], m = 2, k = 2
     * 输出：false
     * 解释：模式 (1,2) 出现 2 次但并不连续，所以不能算作连续重复 2 次。
     * 示例 5：
     * <p>
     * 输入：arr = [2,2,2,2], m = 2, k = 3
     * 输出：false
     * 解释：长度为 2 的模式只有 (2,2) ，但是只连续重复 2 次。注意，不能计算重叠的重复次数。
     * <p>
     * 枚举法
     * 思路与算法
     * <p>
     * 题目要求我们找到一个连续出现 kk 次且长度为 mm 的子数组。也就是说如果这个子数组的左端点是 ll，
     * 那么对于任意 {\rm offset} \in [0, m \times k)offset∈[0,m×k)，
     * 都有 a[l + {\rm offset}] = a[l + ({\rm offset} \bmod m)]a[l+offset]=a[l+(offsetmodm)]。
     * 因此，我们可以枚举左端点 ll，对于每个 ll 枚举 {\rm offset} \in [0, m \times k)offset∈[0,m×k)，
     * 判断是否满足条件即可。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/solution/zhong-fu-zhi-shao-k-ci-qie-chang-du-wei-m-de-mo-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param arr
     * @param m
     * @param k
     * @return
     */
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        for (int l = 0; l <= n - m * k; ++l) {
            int offset;
            for (offset = 0; offset < m * k; ++offset) {
                if (arr[l + offset] != arr[l + offset % m]) {
                    break;
                }
            }
            if (offset == m * k) {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     * <p>
     * 示例:
     * <p>
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * <p>
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * <p>
     * <p>
     * 提示：
     * <p>
     * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
     * <p>
     * <p>
     * 算法流程：
     * 初始化： 双端队列 dequedeque ，结果列表 resres ，数组长度 nn ；
     * 滑动窗口： 左边界范围 i \in [1 - k, n - k]i∈[1−k,n−k] ，右边界范围 j \in [0, n - 1]j∈[0,n−1] ；
     * 若 i > 0i>0 且 队首元素 deque[0]deque[0] == 被删除元素 nums[i - 1]nums[i−1] ：则队首元素出队；
     * 删除 dequedeque 内所有 < nums[j]<nums[j] 的元素，以保持 dequedeque 递减；
     * 将 nums[j]nums[j] 添加至 dequedeque 尾部；
     * 若已形成窗口（即 i \geq 0i≥0 ）：将窗口最大值（即队首元素 deque[0]deque[0] ）添加至列表 resres ；
     * <p>
     * dequedeque 内 仅包含窗口内的元素 \Rightarrow⇒ 每轮窗口滑动移除了元素 nums[i - 1]nums[i−1] ，需将 dequedeque 内的对应元素一起删除。
     * dequedeque 内的元素 非严格递减 \Rightarrow⇒ 每轮窗口滑动添加了元素 nums[j + 1]nums[j+1] ，需将 dequedeque 内所有 < nums[j + 1]<nums[j+1] 的元素删除。
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/mian-shi-ti-59-i-hua-dong-chuang-kou-de-zui-da-1-6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]
            if (i > 0 && deque.peekFirst() == nums[i - 1])
                deque.removeFirst();
            // 保持 deque 递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast();
            deque.addLast(nums[j]);
            // 记录窗口最大值
            if (i >= 0)
                res[i] = deque.peekFirst();
        }
        return res;
    }


    /**
     * 面试题 16.11. 跳水板
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，
     * 长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     * <p>
     * 返回的长度需要从小到大排列。
     * <p>
     * 示例 1
     * <p>
     * 输入：
     * shorter = 1
     * longer = 2
     * k = 3
     * 输出： [3,4,5,6]
     * 解释：
     * 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }

        int[] lengths = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            lengths[i] = shorter * (k - i) + longer * i;
        }

        return lengths;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * <p>
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int[] order = new int[total];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    /**
     * 直接法
     * 空值处理： 当 matrix 为空时，直接返回空列表 [] 即可。
     * 初始化： 矩阵 左、右、上、下 四个边界 l , r , t , b ，用于打印的结果列表 res 。
     * 循环打印： “从左向右、从上向下、从右向左、从下向上” 四个方向循环，每个方向打印中做以下三件事 （各方向的具体信息见下表） ；
     * 根据边界打印，即将元素按顺序添加至列表 res 尾部；
     * 边界向内收缩 11 （代表已被打印）；
     * 判断是否打印完毕（边界是否相遇），若打印完毕则跳出。
     * <p>
     * 打印方向	1. 根据边界打印	2. 边界向内收缩	3. 是否打印完毕
     * 从左向右	左边界l ，右边界 r	上边界 t 加 11	是否 t > b
     * 从上向下	上边界 t ，下边界b	右边界 r 减 11	是否 l > r
     * 从右向左	右边界 r ，左边界l	下边界 b 减 11	是否 t > b
     * 从下向上	下边界 b ，上边界t	左边界 l 加 11	是否 l > r
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/mian-shi-ti-29-shun-shi-zhen-da-yin-ju-zhen-she-di/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder2(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;
    }


    /**
     * 1037. 有效的回旋镖
     * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
     * <p>
     * 给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[[1,1],[2,3],[3,2]]
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：[[1,1],[2,2],[3,3]]
     * 输出：false
     * <p>
     * 计算斜率
     * 判断三点是否同直线，直接思路就是判断斜率。
     * 假设三点分别为a(x1, y1), b(x2, y2), c(x3,y3),
     * a、b两点的斜率为 k1 = (y2 - y1) / (x2 - x1)
     * a、c两点的斜率为 k2 = (y3 - y1) / (x3 - x1)
     * 如果在同一直线，则k1 = k2，考虑到分母为0 的情况，可以直接交叉相乘，省去判断0的情况，直接判断
     * (y2 - y1) * (x3 - x1) 与 (y3 - y1) * (x2 - x1)
     * 不相等即为不在同一直线上
     * <p>
     * 作者：huangyt
     * 链接：https://leetcode-cn.com/problems/valid-boomerang/solution/qiao-miao-xie-lu-bian-hua-miao-yong-yi-xing-dai-ma/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param points
     * @return
     */
    public boolean isBoomerang(int[][] points) {

        return (points[1][1] - points[0][1]) * (points[2][0] - points[0][0])
                != (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
    }


    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }
}
