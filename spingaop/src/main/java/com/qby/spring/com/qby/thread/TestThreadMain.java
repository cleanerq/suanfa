package com.qby.spring.com.qby.thread;

public class TestThreadMain {
    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    CacheMgrUtil.setIdLocal(Math.random() * 1000 + "", Math.random() * 1000 + "");
                    System.out.println(Thread.currentThread().getId() + " 生成的 companyId："
                            + CacheMgrUtil.getCompanyIdLocal() + " 生成的 interfaceId："
                            + CacheMgrUtil.getInterfaceIdLocal());
                    CacheMgrUtil.clearIdLocal();
                    System.out.println(Thread.currentThread().getId() + " 销毁ID");
                    System.out.println(Thread.currentThread().getId() + " 生成的 companyId："
                            + CacheMgrUtil.getCompanyIdLocal() + " 生成的 interfaceId："
                            + CacheMgrUtil.getInterfaceIdLocal());
                }
            });
            t1.start();
        }
    }
}
