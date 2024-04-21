package io.valentinsoare.wordtally.service;

import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ActOnInputOptionsProcessingAsAService}.
 */
@Generated
public class ActOnInputOptionsProcessingAsAService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'actOnInputOptionsProcessingAsAService'.
   */
  private static BeanInstanceSupplier<ActOnInputOptionsProcessingAsAService> getActOnInputOptionsProcessingAsAServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ActOnInputOptionsProcessingAsAService>forConstructor(OutputFormat.class, ParsingAsAService.class, ProcessingAsAService.class);
  }

  /**
   * Get the bean definition for 'actOnInputOptionsProcessingAsAService'.
   */
  public static BeanDefinition getActOnInputOptionsProcessingAsAServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ActOnInputOptionsProcessingAsAService.class);
    beanDefinition.setInstanceSupplier(getActOnInputOptionsProcessingAsAServiceInstanceSupplier());
    return beanDefinition;
  }
}
