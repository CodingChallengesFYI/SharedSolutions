package org.springframework.boot.context.properties;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ConfigurationPropertiesBinder}.
 */
@Generated
public class ConfigurationPropertiesBinder__BeanDefinitions {
  /**
   * Bean definitions for {@link ConfigurationPropertiesBinder.ConfigurationPropertiesBinderFactory}.
   */
  @Generated
  public static class ConfigurationPropertiesBinderFactory {
    /**
     * Get the bean definition for 'internalConfigurationPropertiesBinder'.
     */
    public static BeanDefinition getInternalConfigurationPropertiesBinderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesBinder.ConfigurationPropertiesBinderFactory.class);
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.setInstanceSupplier(ConfigurationPropertiesBinder.ConfigurationPropertiesBinderFactory::new);
      return beanDefinition;
    }
  }
}
