package org.springframework.boot.autoconfigure.availability;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.availability.ApplicationAvailabilityBean;

/**
 * Bean definitions for {@link ApplicationAvailabilityAutoConfiguration}.
 */
@Generated
public class ApplicationAvailabilityAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'applicationAvailabilityAutoConfiguration'.
   */
  public static BeanDefinition getApplicationAvailabilityAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationAvailabilityAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ApplicationAvailabilityAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'applicationAvailability'.
   */
  private static BeanInstanceSupplier<ApplicationAvailabilityBean> getApplicationAvailabilityInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ApplicationAvailabilityBean>forFactoryMethod(ApplicationAvailabilityAutoConfiguration.class, "applicationAvailability")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(ApplicationAvailabilityAutoConfiguration.class).applicationAvailability());
  }

  /**
   * Get the bean definition for 'applicationAvailability'.
   */
  public static BeanDefinition getApplicationAvailabilityBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationAvailabilityBean.class);
    beanDefinition.setInstanceSupplier(getApplicationAvailabilityInstanceSupplier());
    return beanDefinition;
  }
}
