package com.mikescherbakov.jobinterviewbase.postprocessor;

import java.security.SecureRandom;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class InjectRandomIntBPP implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (bean.getClass().isAnnotationPresent(InjectRandomInt.class)) {
      var field = ReflectionUtils.findField(bean.getClass(), "randomInt");
      if (field != null) {
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, bean, new SecureRandom().nextInt(100));
      }
    }
    return bean;
  }
}
