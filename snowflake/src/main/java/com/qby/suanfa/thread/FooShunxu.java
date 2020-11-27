package com.qby.suanfa.thread;

public class FooShunxu {
    private volatile boolean firstFinished;
    private volatile boolean secondFinished;
    private Object lock = new Object();

    public FooShunxu() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized (lock) {
            while (!firstFinished) {
                lock.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooShunxu test = new FooShunxu();
        Foo foo = new Foo();
        test.first(() -> {
            foo.first();
        });

        test.second(() -> {
            foo.second();
        });

        test.third(() -> {
            foo.third();
        });


    }
}
