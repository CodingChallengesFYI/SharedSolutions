package io.valentinsoare.wordtally.runner;

import io.valentinsoare.wordtally.service.InputOptionsAsArguments;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApplicationRunner}.
 */
@Generated
public class ApplicationRunner__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'applicationRunner'.
   */
  private static BeanInstanceSupplier<ApplicationRunner> getApplicationRunnerInstanceSupplier() {
    return BeanInstanceSupplier.<ApplicationRunner>forConstructor(InputOptionsAsArguments.class)
            .withGenerator((registeredBean, args) -> new ApplicationRunner(args.get(0)));
  }

  /**
   * Get the bean definition for 'applicationRunner'.
   */
  public static BeanDefinition getApplicationRunnerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationRunner.class);
    beanDefinition.setInstanceSupplier(getApplicationRunnerInstanceSupplier());
    return beanDefinition;
  }
}
