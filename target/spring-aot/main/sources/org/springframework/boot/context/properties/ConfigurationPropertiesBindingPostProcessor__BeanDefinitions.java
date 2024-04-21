package org.springframework.boot.context.properties;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ConfigurationPropertiesBindingPostProcessor}.
 */
@Generated
public class ConfigurationPropertiesBindingPostProcessor__BeanDefinitions {
  /**
   * Get the bean definition for 'configurationPropertiesBindingPostProcessor'.
   */
  public static BeanDefinition getConfigurationPropertiesBindingPostProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesBindingPostProcessor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(ConfigurationPropertiesBindingPostProcessor::new);
    return beanDefinition;
  }
}
