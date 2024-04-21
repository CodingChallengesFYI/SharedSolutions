package org.springframework.boot.autoconfigure.context;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.DefaultLifecycleProcessor;

/**
 * Bean definitions for {@link LifecycleAutoConfiguration}.
 */
@Generated
public class LifecycleAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'lifecycleAutoConfiguration'.
   */
  public static BeanDefinition getLifecycleAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LifecycleAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(LifecycleAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'lifecycleProcessor'.
   */
  private static BeanInstanceSupplier<DefaultLifecycleProcessor> getLifecycleProcessorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DefaultLifecycleProcessor>forFactoryMethod(LifecycleAutoConfiguration.class, "defaultLifecycleProcessor", LifecycleProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(LifecycleAutoConfiguration.class).defaultLifecycleProcessor(args.get(0)));
  }

  /**
   * Get the bean definition for 'lifecycleProcessor'.
   */
  public static BeanDefinition getLifecycleProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultLifecycleProcessor.class);
    beanDefinition.setInstanceSupplier(getLifecycleProcessorInstanceSupplier());
    return beanDefinition;
  }
}
