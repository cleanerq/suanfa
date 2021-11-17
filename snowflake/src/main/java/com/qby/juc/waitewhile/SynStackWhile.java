package com.qby.juc.waitewhile;

/**
 * 就是用if判断的话，唤醒后线程会从wait之后的代码开始运行，但是不会重新判断if条件，
 * 直接继续运行if代码块之后的代码，而如果使用while的话，
 * 也会从wait之后的代码运行，但是唤醒后会重新判断循环条件，如果不成立再执行while代码块之后的代码块，成立的话继续wait。
 */
class SynStackWhile {
    private char[] data = new char[6];
    private int cnt = 0; //表示数组有效元素的个数

    public synchronized void push(char ch) {
        while (cnt >= data.length) {
            try {
                System.out.println("生产线程" + Thread.currentThread().getName() + "准备休眠");
                this.wait();
                System.out.println("生产线程" + Thread.currentThread().getName() + "休眠结束了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.notify();
        data[cnt] = ch;
        ++cnt;
        System.out.printf("生产线程" + Thread.currentThread().getName() + "正在生产第%d个产品，该产品是: %c\n", cnt, ch);
    }

    public synchronized char pop() {
        char ch;
        while (cnt <= 0) {
            try {
                System.out.println("消费线程" + Thread.currentThread().getName() + "准备休眠");
                this.wait();
                System.out.println("消费线程" + Thread.currentThread().getName() + "休眠结束了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.notify();
        ch = data[cnt - 1];
        System.out.printf("消费线程" + Thread.currentThread().getName() + "正在消费第%d个产品，该产品是: %c\n", cnt, ch);
        --cnt;
        return ch;
    }
}

class ProducerWhile implements Runnable {
    private SynStackWhile ss = null;

    public ProducerWhile(SynStackWhile ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        char ch;
        for (int i = 0; i < 20; ++i) {
//          try{
//          Thread.sleep(100);
//          }
//          catch (Exception e){
//          }

            ch = (char) ('a' + i);
            ss.push(ch);
        }
    }
}

class ConsumerWhile implements Runnable {
    private SynStackWhile ss = null;

    public ConsumerWhile(SynStackWhile ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            /*try{
            Thread.sleep(100);
            }
            catch (Exception e){
            }*/

            //System.out.printf("%c\n", ss.pop());
            ss.pop();
        }
    }
}
