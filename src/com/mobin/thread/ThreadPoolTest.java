package com.mobin.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mobin on 2016/3/15.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);//创建一个线程池，有三个线程(l固定线程)
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池里面的线程数会动态变化
        for (int i = 1; i <= 10; i ++) {
            final  int task = i;
            threadPool.execute(new Runnable() {
                public void run() {
                    for (int j = 1; j <= 5; j++) {
                        System.out.println("Thread Name is" + Thread.currentThread().getName() + " is loop of " + j + "  for task of  "+task);
                    }
                }
            });
        }
    }
}
