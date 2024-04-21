package org.springframework.boot.autoconfigure.context;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ConfigurationPropertiesAutoConfiguration}.
 */
@Generated
public class ConfigurationPropertiesAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'configurationPropertiesAutoConfiguration'.
   */
  public static BeanDefinition getConfigurationPropertiesAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ConfigurationPropertiesAutoConfiguration::new);
    return beanDefinition;
  }
}
