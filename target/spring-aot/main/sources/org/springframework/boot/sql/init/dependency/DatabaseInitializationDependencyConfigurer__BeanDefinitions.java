package org.springframework.boot.sql.init.dependency;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DatabaseInitializationDependencyConfigurer}.
 */
@Generated
public class DatabaseInitializationDependencyConfigurer__BeanDefinitions {
  /**
   * Bean definitions for {@link DatabaseInitializationDependencyConfigurer.DependsOnDatabaseInitializationPostProcessor}.
   */
  @Generated
  public static class DependsOnDatabaseInitializationPostProcessor {
    /**
     * Get the bean definition for 'dependsOnDatabaseInitializationPostProcessor'.
     */
    public static BeanDefinition getDependsOnDatabaseInitializationPostProcessorBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(DatabaseInitializationDependencyConfigurer.DependsOnDatabaseInitializationPostProcessor.class);
      beanDefinition.setInstanceSupplier(DatabaseInitializationDependencyConfigurer.DependsOnDatabaseInitializationPostProcessor::new);
      return beanDefinition;
    }
  }
}
