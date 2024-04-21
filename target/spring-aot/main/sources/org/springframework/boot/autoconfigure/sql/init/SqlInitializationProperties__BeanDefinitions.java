package org.springframework.boot.autoconfigure.sql.init;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SqlInitializationProperties}.
 */
@Generated
public class SqlInitializationProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'sqlInitializationProperties'.
   */
  public static BeanDefinition getSqlInitializationPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SqlInitializationProperties.class);
    beanDefinition.setInstanceSupplier(SqlInitializationProperties::new);
    return beanDefinition;
  }
}
