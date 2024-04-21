package io.valentinsoare.wordtally.setupasync;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link AsynchronousConfiguration}.
 */
@Generated
public class AsynchronousConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'asynchronousConfiguration'.
   */
  public static BeanDefinition getAsynchronousConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AsynchronousConfiguration.class);
    beanDefinition.setTargetType(AsynchronousConfiguration.class);
    ConfigurationClassUtils.initializeConfigurationClass(AsynchronousConfiguration.class);
    beanDefinition.setInstanceSupplier(AsynchronousConfiguration$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
