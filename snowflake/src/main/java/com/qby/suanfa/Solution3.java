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

    public static void main(String[] args) {
//        System.out.println((int)'A');
//        System.out.println((int)'Z');
        System.out.println(trailingZeroes2(10));
    }
}
