package com.wl.data;

import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {

    static class MyData2 {
        volatile int  number = 0;
//        int  number = 0;

        public void addTo60() {
            this.number = 60;
        }

        // 请注意，此时number前面是加了volatile关键字修饰，volatile不保证原子性
        public void addPlusPlus(){
            number++;
        }
    }


    /**
     * 1.验证volatile的可见性
     *  1.1 假如 int number = 0; number变量之前没有关键字修饰，没有可见性，数值不会修改到主内存
     */
    public static void main(String[] args) {
//        seeOKByVolatile();
        atomicByVolatile();
    }

    private static void atomicByVolatile() {
        MyData2 myData = new MyData2();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    myData.addPlusPlus();

                }
            },String.valueOf(i)).start();
        }

        // 需要等待上面所有子线层执行完毕之后，主线程在运行
        // 如果当前线程大于2，说明还有子线程
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(myData.number);
    }
    /**
     * 可见性测试
     */
    private static void seeOKByVolatile() {
        MyData2 myData = new MyData2();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 暂停线程，当前线程挂起，这时候main会抢占资源
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number " + myData.number);
        }, "AAA").start();

        System.out.println(Thread.currentThread().getName() + "\t come in");
        // 第二个线程就是我们main主线程
        while (myData.number == 0) {
            // 发现myData.number还是0，一直循环，3秒后，myData.number被修改，但是没有通知到主线程,造成可见性问题
        }

        System.out.println(Thread.currentThread().getName() + "\t main is over ");
    }
}
