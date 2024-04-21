package org.springframework.boot.autoconfigure.ssl;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SslProperties}.
 */
@Generated
public class SslProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'sslProperties'.
   */
  public static BeanDefinition getSslPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SslProperties.class);
    beanDefinition.setInstanceSupplier(SslProperties::new);
    return beanDefinition;
  }
}
