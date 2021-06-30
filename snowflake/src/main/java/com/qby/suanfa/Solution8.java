package com.qby.suanfa;

public class Solution8 {
    public static void main(String[] args) {
        System.out.println(binaryGap2(1));
    }

    /**
     * 868. 二进制间距
     * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。
     * 如果不存在两个相邻的 1，返回 0 。
     * <p>
     * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。
     * 两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。
     *
     * @param n
     * @return
     */
    public static int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        char[] charArray = s.toCharArray();
        int count = 0;
        int max = 0;

        char pre = '9';
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                max = Math.max(max, count);
                count = 1;
                pre = charArray[i];
            } else if (pre == '1') {
                if (charArray[i] == '0') {
                    count++;
                }
            } else if (pre == '9') {
                count = 0;
            }
        }
        return max;
    }

    public static int binaryGap2(int N) {
        int last = -1, ans = 0;
        for (int i = 0; i < 32; ++i)
            if (((N >> i) & 1) > 0) {
                if (last >= 0)
                    ans = Math.max(ans, i - last);
                last = i;
            }
        return ans;
    }
}
