package com.mikescherbakov.jobinterviewbase.model;

import com.mikescherbakov.jobinterviewbase.postprocessor.InjectRandomInt;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope("prototype")
@InjectRandomInt
public class MyBean {
  private int randomInt;

}
