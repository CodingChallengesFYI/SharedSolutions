package org.springframework.boot.autoconfigure.task;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TaskSchedulingProperties}.
 */
@Generated
public class TaskSchedulingProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'taskSchedulingProperties'.
   */
  public static BeanDefinition getTaskSchedulingPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingProperties.class);
    beanDefinition.setInstanceSupplier(TaskSchedulingProperties::new);
    return beanDefinition;
  }
}
