package com.ma.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        //1.创建Callable实现类的实例
        MyCallable myCallable = new MyCallable();
        //2.使用FutureTask类进行包装Callable对象，FutureTask对象封装了Callable对象的call()方法的返回值
        FutureTask<String> futureTask = new FutureTask<>(myCallable);

        System.out.println(Thread.currentThread() + "：=========futureTask线程启动之前=========");
        //3 启动futureTask线程
        new Thread(futureTask, "有返回值的线程FutureTask").start();
        System.out.println(Thread.currentThread() + "：=========futureTask线程启动之后=========");

        try {
            //4.调用FutureTask对象的get（）方法获取子线程执行结束后的返回值。
            //get()方法会阻塞，直到子线程执行结束才返回
            System.out.println(Thread.currentThread() + "：=========futureTask get 方法之前=========");
            System.out.println(Thread.currentThread() + "：子线程的返回值：" + futureTask.get());
            System.out.println(Thread.currentThread() + "：=========futureTask get 方法之后=========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() {
        System.out.println(Thread.currentThread());
        return "Fuck You";
    }
}
