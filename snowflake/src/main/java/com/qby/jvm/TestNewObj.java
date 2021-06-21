package com.qby.jvm;

public class TestNewObj {
   private int a = 1;

    public TestNewObj(int a) {
        this.a = a;
    }

    public static void main(String[] args) {
        String s = "222";
        System.out.println(s);
    }

    public int getA() {
        return this.a++;
    }

    public void  test04() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量c使用了之前已经销毁的b的slot的位置
        int c = a + 1;
    }

    public void test05() {
        int a;

    }

    public void testAddOperation() {
        byte i = 15;
        int j = 8;
        int k = i + j;

        int m = 800;
    }

    public int sum() {
        int m = 10;
        int n = 20;
        int k = m + n;
        return k;
    }

    public void testSum() {
        int i = sum();
        int j = 10;
    }
}
