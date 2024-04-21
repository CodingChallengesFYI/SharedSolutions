package org.springframework.boot.autoconfigure.context;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LifecycleProperties}.
 */
@Generated
public class LifecycleProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'lifecycleProperties'.
   */
  public static BeanDefinition getLifecyclePropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LifecycleProperties.class);
    beanDefinition.setInstanceSupplier(LifecycleProperties::new);
    return beanDefinition;
  }
}
