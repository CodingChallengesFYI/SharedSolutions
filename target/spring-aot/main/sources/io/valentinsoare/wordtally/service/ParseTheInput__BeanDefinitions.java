package io.valentinsoare.wordtally.service;

import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ParseTheInput}.
 */
@Generated
public class ParseTheInput__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'parseTheInput'.
   */
  private static BeanInstanceSupplier<ParseTheInput> getParseTheInputInstanceSupplier() {
    return BeanInstanceSupplier.<ParseTheInput>forConstructor(OutputFormat.class);
  }

  /**
   * Get the bean definition for 'parseTheInput'.
   */
  public static BeanDefinition getParseTheInputBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ParseTheInput.class);
    beanDefinition.setInstanceSupplier(getParseTheInputInstanceSupplier());
    return beanDefinition;
  }
}
