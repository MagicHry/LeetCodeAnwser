package com.reillyhe.leetcodeanwser.concurrentdemo;

public class WaitTest {
    private Object mSingleLock = new Object();

    /**
     * 这个函数将会Failed
     * 因为持有的锁不同
     */
    public synchronized void doWaitViaMethodSync() {
        try {
            System.out.println("START at thread : " + Thread.currentThread().getName());
            mSingleLock.wait(5000);
        }catch (InterruptedException e){

        }finally {
            System.out.println("BREAK at thread : " + Thread.currentThread().getName());
        }
    }

    public void doWaitViaSingleLock() {
        long time = 0;
        try {
            synchronized (mSingleLock) {
                time = System.currentTimeMillis();
                System.out.println("START at thread : " + Thread.currentThread().getName());
                mSingleLock.wait(2500L);
            }
        }catch (InterruptedException e){

        }finally {
            System.out.println("BREAK at thread : " + Thread.currentThread().getName()
                    + "time consumed = " + (System.currentTimeMillis() - time));
        }
    }
}
