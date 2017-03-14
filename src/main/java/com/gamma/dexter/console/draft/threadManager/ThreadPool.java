package com.gamma.dexter.console.draft.threadManager;

/**
 * Created by lenovo on 4/26/2016.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    ExecutorService executor;

    public ThreadPool() {

        executor = Executors.newFixedThreadPool(10);
//        getThread();
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
//        System.out.println("Finished all threads");
    }

    public static void main(String[] args) {

        ThreadPool ob = new ThreadPool();
        for (int i = 0; i < 25; i++){
//            ob.startNotificationThread();
        }
    }

    public void startNotificationThread(String ip, String community) {

        Runnable worker = new NotificationWorkerThread(ip,community);
        executor.execute(worker);
    }

    public void shutdownNotificationThread(){
        executor.shutdown();
    }

}