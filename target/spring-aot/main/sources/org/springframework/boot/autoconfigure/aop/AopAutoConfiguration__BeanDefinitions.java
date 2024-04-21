package org.springframework.boot.autoconfigure.aop;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AopAutoConfiguration}.
 */
@Generated
public class AopAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'aopAutoConfiguration'.
   */
  public static BeanDefinition getAopAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AopAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(AopAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link AopAutoConfiguration.ClassProxyingConfiguration}.
   */
  @Generated
  public static class ClassProxyingConfiguration {
    /**
     * Get the bean definition for 'classProxyingConfiguration'.
     */
    public static BeanDefinition getClassProxyingConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(AopAutoConfiguration.ClassProxyingConfiguration.class);
      beanDefinition.setInstanceSupplier(AopAutoConfiguration.ClassProxyingConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean definition for 'forceAutoProxyCreatorToUseClassProxying'.
     */
    public static BeanDefinition getForceAutoProxyCreatorToUseClassProxyingBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(AopAutoConfiguration.ClassProxyingConfiguration.class);
      beanDefinition.setTargetType(BeanFactoryPostProcessor.class);
      beanDefinition.setInstanceSupplier(BeanInstanceSupplier.<BeanFactoryPostProcessor>forFactoryMethod(AopAutoConfiguration.ClassProxyingConfiguration.class, "forceAutoProxyCreatorToUseClassProxying").withGenerator((registeredBean) -> AopAutoConfiguration.ClassProxyingConfiguration.forceAutoProxyCreatorToUseClassProxying()));
      return beanDefinition;
    }
  }
}
