package com.mobin.thread;

import java.util.concurrent.*;

/**
 * Created by Mobin on 2016/3/20.
 * 执行完线程后返回结果
 */
public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() throws Exception {
                return "mobin";
            }
        });
        System.out.println(future.get());
    }
}
