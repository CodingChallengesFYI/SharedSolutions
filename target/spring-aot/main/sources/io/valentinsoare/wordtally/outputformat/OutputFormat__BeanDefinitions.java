package io.valentinsoare.wordtally.outputformat;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OutputFormat}.
 */
@Generated
public class OutputFormat__BeanDefinitions {
  /**
   * Get the bean definition for 'outputFormat'.
   */
  public static BeanDefinition getOutputFormatBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OutputFormat.class);
    beanDefinition.setInstanceSupplier(OutputFormat::new);
    return beanDefinition;
  }
}
