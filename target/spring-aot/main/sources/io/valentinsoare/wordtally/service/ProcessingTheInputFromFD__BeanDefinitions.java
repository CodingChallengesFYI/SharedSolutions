package io.valentinsoare.wordtally.service;

import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProcessingTheInputFromFD}.
 */
@Generated
public class ProcessingTheInputFromFD__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'processingTheInputFromFD'.
   */
  private static BeanInstanceSupplier<ProcessingTheInputFromFD> getProcessingTheInputFromFDInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ProcessingTheInputFromFD>forConstructor(OutputFormat.class)
            .withGenerator((registeredBean, args) -> new ProcessingTheInputFromFD(args.get(0)));
  }

  /**
   * Get the bean definition for 'processingTheInputFromFD'.
   */
  public static BeanDefinition getProcessingTheInputFromFDBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProcessingTheInputFromFD.class);
    beanDefinition.setInstanceSupplier(getProcessingTheInputFromFDInstanceSupplier());
    return beanDefinition;
  }
}
