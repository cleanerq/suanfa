package com.qby.juc;

/**
 * 当MyThread获得CPU执行时，第6行的 if 测试中，检测到中断标识被设置。
 * 即MyThread线程检测到了main线程想要中断它的 请求。
 * <p>
 * 大多数情况下，MyThread检测到了中断请求，对该中断的响应是：退出执行（或者说是结束执行）。
 * <p>
 * 但是，上面第5至8行for循环，是执行break语句跳出for循环。但是，线程并没有结束，
 * 它只是跳出了for循环而已，它还会继续执行第12行的代码....
 * <p>
 * 因此，我们的问题是，当收到了中断请求后，如何结束该线程呢？
 * <p>
 * 一种可行的方法是使用 return 语句 而不是 break语句。。。。。哈哈。。。
 * <p>
 * 当然，一种更优雅的方式则是：抛出InterruptedException异常。
 */
public class MyThread {
    public static void main(String[] args) {
        try {
            MyThreadRun thread = new MyThreadRun();
            thread.start();
            Thread.sleep(20);//modify 2000 to 20
            thread.interrupt();//请求中断MyThread线程
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }

    static class MyThreadRun extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                for (int i = 0; i < 500000; i++) {
                    if (this.interrupted()) {
                        System.out.println("should be stopped and exit");
    //                    break;
                        throw new InterruptedException();
                    }
                    System.out.println("i=" + (i + 1));
                }
                System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
            } catch (InterruptedException e) {
//                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}


