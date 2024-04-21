package org.springframework.boot.autoconfigure.task;

import java.lang.SuppressWarnings;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Bean definitions for {@link TaskExecutorConfigurations}.
 */
@Generated
public class TaskExecutorConfigurations__BeanDefinitions {
  /**
   * Bean definitions for {@link TaskExecutorConfigurations.TaskExecutorBuilderConfiguration}.
   */
  @Generated
  public static class TaskExecutorBuilderConfiguration {
    /**
     * Get the bean definition for 'taskExecutorBuilderConfiguration'.
     */
    public static BeanDefinition getTaskExecutorBuilderConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorConfigurations.TaskExecutorBuilderConfiguration.class);
      beanDefinition.setInstanceSupplier(TaskExecutorConfigurations.TaskExecutorBuilderConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'taskExecutorBuilder'.
     */
    @SuppressWarnings("removal")
    private static BeanInstanceSupplier<TaskExecutorBuilder> getTaskExecutorBuilderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<TaskExecutorBuilder>forFactoryMethod(TaskExecutorConfigurations.TaskExecutorBuilderConfiguration.class, "taskExecutorBuilder", TaskExecutionProperties.class, ObjectProvider.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(TaskExecutorConfigurations.TaskExecutorBuilderConfiguration.class).taskExecutorBuilder(args.get(0), args.get(1), args.get(2)));
    }

    /**
     * Get the bean definition for 'taskExecutorBuilder'.
     */
    @SuppressWarnings("removal")
    public static BeanDefinition getTaskExecutorBuilderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorBuilder.class);
      beanDefinition.setInstanceSupplier(getTaskExecutorBuilderInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link TaskExecutorConfigurations.TaskExecutorConfiguration}.
   */
  @Generated
  public static class TaskExecutorConfiguration {
    /**
     * Get the bean definition for 'taskExecutorConfiguration'.
     */
    public static BeanDefinition getTaskExecutorConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorConfigurations.TaskExecutorConfiguration.class);
      beanDefinition.setInstanceSupplier(TaskExecutorConfigurations.TaskExecutorConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'applicationTaskExecutor'.
     */
    @SuppressWarnings("removal")
    private static BeanInstanceSupplier<ThreadPoolTaskExecutor> getApplicationTaskExecutorInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<ThreadPoolTaskExecutor>forFactoryMethod(TaskExecutorConfigurations.TaskExecutorConfiguration.class, "applicationTaskExecutor", TaskExecutorBuilder.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(TaskExecutorConfigurations.TaskExecutorConfiguration.class).applicationTaskExecutor(args.get(0), args.get(1)));
    }

    /**
     * Get the bean definition for 'applicationTaskExecutor'.
     */
    public static BeanDefinition getApplicationTaskExecutorBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ThreadPoolTaskExecutor.class);
      beanDefinition.setLazyInit(true);
      beanDefinition.setInstanceSupplier(getApplicationTaskExecutorInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link TaskExecutorConfigurations.ThreadPoolTaskExecutorBuilderConfiguration}.
   */
  @Generated
  public static class ThreadPoolTaskExecutorBuilderConfiguration {
    /**
     * Get the bean definition for 'threadPoolTaskExecutorBuilderConfiguration'.
     */
    public static BeanDefinition getThreadPoolTaskExecutorBuilderConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorConfigurations.ThreadPoolTaskExecutorBuilderConfiguration.class);
      beanDefinition.setInstanceSupplier(TaskExecutorConfigurations.ThreadPoolTaskExecutorBuilderConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'threadPoolTaskExecutorBuilder'.
     */
    private static BeanInstanceSupplier<ThreadPoolTaskExecutorBuilder> getThreadPoolTaskExecutorBuilderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<ThreadPoolTaskExecutorBuilder>forFactoryMethod(TaskExecutorConfigurations.ThreadPoolTaskExecutorBuilderConfiguration.class, "threadPoolTaskExecutorBuilder", TaskExecutionProperties.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(TaskExecutorConfigurations.ThreadPoolTaskExecutorBuilderConfiguration.class).threadPoolTaskExecutorBuilder(args.get(0), args.get(1), args.get(2), args.get(3)));
    }

    /**
     * Get the bean definition for 'threadPoolTaskExecutorBuilder'.
     */
    public static BeanDefinition getThreadPoolTaskExecutorBuilderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ThreadPoolTaskExecutorBuilder.class);
      beanDefinition.setInstanceSupplier(getThreadPoolTaskExecutorBuilderInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration}.
   */
  @Generated
  public static class SimpleAsyncTaskExecutorBuilderConfiguration {
    /**
     * Get the bean instance supplier for 'org.springframework.boot.autoconfigure.task.TaskExecutorConfigurations$SimpleAsyncTaskExecutorBuilderConfiguration'.
     */
    private static BeanInstanceSupplier<TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration> getSimpleAsyncTaskExecutorBuilderConfigurationInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration>forConstructor(TaskExecutionProperties.class, ObjectProvider.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> new TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration(args.get(0), args.get(1), args.get(2)));
    }

    /**
     * Get the bean definition for 'simpleAsyncTaskExecutorBuilderConfiguration'.
     */
    public static BeanDefinition getSimpleAsyncTaskExecutorBuilderConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration.class);
      beanDefinition.setInstanceSupplier(getSimpleAsyncTaskExecutorBuilderConfigurationInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'simpleAsyncTaskExecutorBuilder'.
     */
    private static BeanInstanceSupplier<SimpleAsyncTaskExecutorBuilder> getSimpleAsyncTaskExecutorBuilderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<SimpleAsyncTaskExecutorBuilder>forFactoryMethod(TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration.class, "simpleAsyncTaskExecutorBuilder")
              .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration.class).simpleAsyncTaskExecutorBuilder());
    }

    /**
     * Get the bean definition for 'simpleAsyncTaskExecutorBuilder'.
     */
    public static BeanDefinition getSimpleAsyncTaskExecutorBuilderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(SimpleAsyncTaskExecutorBuilder.class);
      beanDefinition.setInstanceSupplier(getSimpleAsyncTaskExecutorBuilderInstanceSupplier());
      return beanDefinition;
    }
  }
}
