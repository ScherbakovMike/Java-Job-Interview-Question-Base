package com.mikescherbakov.jobinterviewbase.runner;

import org.springframework.stereotype.*;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final int THREAD_COUNT = 10;
    private int concurrentResource;

    @Override
    public void run(String... args) throws Exception {
        demonstrateSynchronized();
        TimeUnit.SECONDS.sleep(3);
        demonstrateReentrantLock();
        TimeUnit.SECONDS.sleep(3);
        demonstrateStampedLock();
    }

    private void demonstrateStampedLock() {
        System.out.println("--- demonstrateStampedLock - start");
        concurrentResource = 0;
        var lock = new StampedLock();
        // writing thread
        new Thread(() -> {
            try {
                var writeStamp = lock.writeLockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " has acquired the write lock");
                concurrentResource++;
                System.out.println("New value: " + concurrentResource);
                TimeUnit.SECONDS.sleep(3);
                lock.unlockWrite(writeStamp);
                System.out.println(Thread.currentThread().getName() + " has released the write lock");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();

        // reading thread
        new Thread(() -> {
            try {
                var readStamp = lock.readLockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " has acquired the read lock");
                System.out.println("Current value: " + concurrentResource);
                lock.unlockRead(readStamp);
                System.out.println(Thread.currentThread().getName() + " has released the read lock");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();
        System.out.println("demonstrateStampedLock - end");
    }

    private void demonstrateReentrantLock() {
        System.out.println("--- demonstrateReentrantLock - start");
        concurrentResource = 0;
        var lock = new ReentrantLock(true);
        for (var i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    if (lock.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName() + " has entered to the method");
                        concurrentResource++;
                        System.out.println("New value: " + concurrentResource);
                        System.out.println(Thread.currentThread().getName() + " has leaved the method");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }).start();
        }
        System.out.println("demonstrateReentrantLock - end");
    }

    private void demonstrateSynchronized() {
        System.out.println("--- demonstrateSynchronized - start");
        concurrentResource = 0;
        for (var i = 0; i < THREAD_COUNT; i++) {
            new Thread(this::synchronizedMethod).start();
        }
        System.out.println("demonstrateSynchronized - end");
    }

    private synchronized void synchronizedMethod() {
        System.out.println(Thread.currentThread().getName() + " has entered to the method");
        concurrentResource++;
        System.out.println("New value: " + concurrentResource);
        System.out.println(Thread.currentThread().getName() + " has leaved the method");
    }
}
