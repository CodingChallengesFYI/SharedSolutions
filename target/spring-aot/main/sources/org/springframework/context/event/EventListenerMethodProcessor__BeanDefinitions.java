package org.springframework.context.event;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link EventListenerMethodProcessor}.
 */
@Generated
public class EventListenerMethodProcessor__BeanDefinitions {
  /**
   * Get the bean definition for 'internalEventListenerProcessor'.
   */
  public static BeanDefinition getInternalEventListenerProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventListenerMethodProcessor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(EventListenerMethodProcessor::new);
    return beanDefinition;
  }
}
