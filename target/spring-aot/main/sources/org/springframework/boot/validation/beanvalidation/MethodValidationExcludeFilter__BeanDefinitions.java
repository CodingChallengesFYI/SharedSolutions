package org.springframework.boot.validation.beanvalidation;

import java.lang.Class;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Bean definitions for {@link MethodValidationExcludeFilter}.
 */
@Generated
public class MethodValidationExcludeFilter__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.boot.context.properties.EnableConfigurationPropertiesRegistrar.methodValidationExcludeFilter'.
   */
  private static BeanInstanceSupplier<MethodValidationExcludeFilter> getMethodValidationExcludeFilterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodValidationExcludeFilter>forFactoryMethod(MethodValidationExcludeFilter.class, "byAnnotation", Class.class)
            .withGenerator((registeredBean, args) -> MethodValidationExcludeFilter.byAnnotation(args.get(0)));
  }

  /**
   * Get the bean definition for 'methodValidationExcludeFilter'.
   */
  public static BeanDefinition getMethodValidationExcludeFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MethodValidationExcludeFilter.class);
    beanDefinition.setTargetType(MethodValidationExcludeFilter.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, ConfigurationProperties.class);
    beanDefinition.setInstanceSupplier(getMethodValidationExcludeFilterInstanceSupplier());
    return beanDefinition;
  }
}
