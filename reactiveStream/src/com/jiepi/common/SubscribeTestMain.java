package com.jiepi.common;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class SubscribeTestMain {


    public static void main(String[] args) {
        SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher();

        SomeSubscriber someSubscriber = new SomeSubscriber();


        submissionPublisher.subscribe(someSubscriber);

        for (int i = 0; i <100 ; i++) {
            int nextInt = new Random().nextInt(300);
            System.out.println("生产者生产消息"+nextInt);
            submissionPublisher.submit(nextInt);
        }

        submissionPublisher.close();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
