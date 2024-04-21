package org.springframework.boot.autoconfigure.context;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Bean definitions for {@link PropertyPlaceholderAutoConfiguration}.
 */
@Generated
public class PropertyPlaceholderAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'propertyPlaceholderAutoConfiguration'.
   */
  public static BeanDefinition getPropertyPlaceholderAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PropertyPlaceholderAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(PropertyPlaceholderAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean definition for 'propertySourcesPlaceholderConfigurer'.
   */
  public static BeanDefinition getPropertySourcesPlaceholderConfigurerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PropertyPlaceholderAutoConfiguration.class);
    beanDefinition.setTargetType(PropertySourcesPlaceholderConfigurer.class);
    beanDefinition.setInstanceSupplier(BeanInstanceSupplier.<PropertySourcesPlaceholderConfigurer>forFactoryMethod(PropertyPlaceholderAutoConfiguration.class, "propertySourcesPlaceholderConfigurer").withGenerator((registeredBean) -> PropertyPlaceholderAutoConfiguration.propertySourcesPlaceholderConfigurer()));
    return beanDefinition;
  }
}
