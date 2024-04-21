package org.springframework.boot.autoconfigure.info;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProjectInfoAutoConfiguration}.
 */
@Generated
public class ProjectInfoAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration'.
   */
  private static BeanInstanceSupplier<ProjectInfoAutoConfiguration> getProjectInfoAutoConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ProjectInfoAutoConfiguration>forConstructor(ProjectInfoProperties.class)
            .withGenerator((registeredBean, args) -> new ProjectInfoAutoConfiguration(args.get(0)));
  }

  /**
   * Get the bean definition for 'projectInfoAutoConfiguration'.
   */
  public static BeanDefinition getProjectInfoAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProjectInfoAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(getProjectInfoAutoConfigurationInstanceSupplier());
    return beanDefinition;
  }
}
