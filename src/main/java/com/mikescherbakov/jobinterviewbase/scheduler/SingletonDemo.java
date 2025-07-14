package com.mikescherbakov.jobinterviewbase.scheduler;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SingletonDemo implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {

    var singleton1 = SingletonDoubleChecking.getInstance();
    var singleton2 = SingletonEnum.INSTANCE;
    var singleton3 = SingletonLazy.getInstance();
  }
}

class SingletonDoubleChecking {
  private static volatile SingletonDoubleChecking instance = null;

  private SingletonDoubleChecking() {}

  public static SingletonDoubleChecking getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (SingletonDoubleChecking.class) {
      if (instance == null) {
        instance = new SingletonDoubleChecking();
      }
    }
    return instance;
  }
}

enum SingletonEnum {
  INSTANCE;

  public void singletonMethod() {
    System.out.println("Hello from singleton method");
  }
}

class SingletonLazy {

  private static class LazyHolder {
    private static final SingletonLazy INSTANCE = new SingletonLazy();
  }

  private SingletonLazy() {}

  public static SingletonLazy getInstance() {
    return LazyHolder.INSTANCE;
  }
}
