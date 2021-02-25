package com.qby.suanfa;

import java.util.ArrayList;
import java.util.List;

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
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 示例 1：
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 暴力法
     * 我们需要找出给定数组中两个数字之间的最大差值（即，最大利润）。
     * 此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）。
     *
     * 形式上，对于每组 ii 和 jj（其中 j > ij>i）我们需要找出
     * max(prices[j]−prices[i])。
     *
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
     *
     * 因此，我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：
     * 如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？当考虑完所有天数之时，我们就得到了最好的答案。
     *
     * 一次遍历
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

    public static void main(String[] args) {
        System.out.println(getRow2(2));
    }
}
