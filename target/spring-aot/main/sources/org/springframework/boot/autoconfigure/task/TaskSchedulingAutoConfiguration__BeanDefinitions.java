package org.springframework.boot.autoconfigure.task;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TaskSchedulingAutoConfiguration}.
 */
@Generated
public class TaskSchedulingAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'taskSchedulingAutoConfiguration'.
   */
  public static BeanDefinition getTaskSchedulingAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(TaskSchedulingAutoConfiguration::new);
    return beanDefinition;
  }
}
