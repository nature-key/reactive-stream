package com.jiepi.common;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class SomeSubscriber implements Flow.Subscriber{

    private Flow.Subscription  subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription=subscription;
        subscription.request(10);
    }

    @Override
    public void onNext(Object item) {
        System.out.println("当前订阅者正在消费的消息是："+item);
        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.request(8);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("所有消息消息完毕");
    }
}
