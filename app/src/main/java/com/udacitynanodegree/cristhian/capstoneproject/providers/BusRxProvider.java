package com.udacitynanodegree.cristhian.capstoneproject.providers;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class BusRxProvider {

    private static final
    BusRxProvider BUS = new BusRxProvider();

    private final Subject<Object, Object>
            mBusSubject = new SerializedSubject<>(PublishSubject.create());

    public static BusRxProvider getInstance() {
        return BUS;
    }

    public static <T> Subscription register(final Class<T> eventClass, Action1<T> onNext, boolean uiThread) {
        if (uiThread) {
            return getInstance().mBusSubject
                    .onBackpressureBuffer()
                    .subscribeOn(Schedulers.io())
                    .filter(event -> event.getClass().equals(eventClass))
                    .map(obj -> (T) obj)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(getInstance()::doOnError)
                    .subscribe(onNext);
        }
        return getInstance().mBusSubject
                .onBackpressureBuffer()
                .observeOn(Schedulers.computation())
                .filter(event -> event.getClass().equals(eventClass))
                .map(obj -> (T) obj)
                .doOnError(getInstance()::doOnError)
                .subscribe(onNext);
    }

    private void doOnError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public static void send(Object event) {
        getInstance().mBusSubject.onNext(event);
    }

    public static boolean hasObservers() {
        return getInstance().hasObservers();
    }

    public Subject<Object, Object> getSubject() {
        return mBusSubject;
    }

}
