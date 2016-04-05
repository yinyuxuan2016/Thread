package com.mobin.thread;

/**
 * Created by Mobin on 2016/4/2.
 * count ++不是原子操作，它涉及到读取count--对count加1--保存count值，如果只将count声明为volitile仍不是线程安全的
 * 必须在count++方法上使用同步机制synchronized，但是加了synchronized就没必要在count变量上加volitile
 */
public class VolatileTest extends  Thread{
    /*volatile*/  public static  int count;
   synchronized private static  void addCount(){
        for(int i = 0; i < 100; i ++){
            count ++;
        }
        System.out.println("count= "+ count);
    }

    public void run(){
        addCount();
    }


    public static void main(String[] args) {
        VolatileTest[] volatileTests = new VolatileTest[100];
        for(int i = 0; i < 100; i ++){
            volatileTests[i] = new VolatileTest();
        }

        for(int i = 0; i < 100; i ++){
            volatileTests[i].start();
        }
    }
}
