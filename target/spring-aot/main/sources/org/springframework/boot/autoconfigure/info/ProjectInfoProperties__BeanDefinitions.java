package org.springframework.boot.autoconfigure.info;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProjectInfoProperties}.
 */
@Generated
public class ProjectInfoProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'projectInfoProperties'.
   */
  public static BeanDefinition getProjectInfoPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProjectInfoProperties.class);
    beanDefinition.setInstanceSupplier(ProjectInfoProperties::new);
    return beanDefinition;
  }
}
