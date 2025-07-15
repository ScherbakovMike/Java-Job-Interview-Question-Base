package com.mikescherbakov.jobinterviewbase.runner;

import com.mikescherbakov.jobinterviewbase.model.MyBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BPPDemonstrator implements ApplicationRunner {

  private final ObjectProvider<MyBean> myBeanProvider;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    var bean1 = myBeanProvider.getObject();
    var bean2 = myBeanProvider.getObject();
    System.out.println("Bean1 random int: " + bean1.getRandomInt());
    System.out.println("Bean2 random int: " + bean2.getRandomInt());
  }
}
