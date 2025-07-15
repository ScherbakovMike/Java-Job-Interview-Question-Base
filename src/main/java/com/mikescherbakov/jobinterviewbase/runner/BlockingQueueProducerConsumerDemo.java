package com.mikescherbakov.jobinterviewbase.runner;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class BlockingQueueProducerConsumerDemo implements ApplicationRunner {

  private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
  private final AtomicInteger counter = new AtomicInteger();

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Runnable producer =
        () -> {
          while (true) {
            try {
              var newValue = counter.addAndGet(1);
              queue.put(newValue);
              System.out.printf("Producer, added value: %d%n", newValue);
              TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              throw new RuntimeException(e);
            }
          }
        };

    Runnable consumer =
        () -> {
          while (true) {
            try {
              System.out.printf("Consumer, taken value: %d%n", queue.take());
              TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              throw new RuntimeException(e);
            }
          }
        };

    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(consumer).start();
  }
}
