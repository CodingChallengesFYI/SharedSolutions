package org.springframework.boot.autoconfigure.task;

import java.lang.SuppressWarnings;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;

/**
 * Bean definitions for {@link TaskSchedulingConfigurations}.
 */
@Generated
public class TaskSchedulingConfigurations__BeanDefinitions {
  /**
   * Bean definitions for {@link TaskSchedulingConfigurations.ThreadPoolTaskSchedulerBuilderConfiguration}.
   */
  @Generated
  public static class ThreadPoolTaskSchedulerBuilderConfiguration {
    /**
     * Get the bean definition for 'threadPoolTaskSchedulerBuilderConfiguration'.
     */
    public static BeanDefinition getThreadPoolTaskSchedulerBuilderConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingConfigurations.ThreadPoolTaskSchedulerBuilderConfiguration.class);
      beanDefinition.setInstanceSupplier(TaskSchedulingConfigurations.ThreadPoolTaskSchedulerBuilderConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'threadPoolTaskSchedulerBuilder'.
     */
    private static BeanInstanceSupplier<ThreadPoolTaskSchedulerBuilder> getThreadPoolTaskSchedulerBuilderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<ThreadPoolTaskSchedulerBuilder>forFactoryMethod(TaskSchedulingConfigurations.ThreadPoolTaskSchedulerBuilderConfiguration.class, "threadPoolTaskSchedulerBuilder", TaskSchedulingProperties.class, ObjectProvider.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(TaskSchedulingConfigurations.ThreadPoolTaskSchedulerBuilderConfiguration.class).threadPoolTaskSchedulerBuilder(args.get(0), args.get(1), args.get(2)));
    }

    /**
     * Get the bean definition for 'threadPoolTaskSchedulerBuilder'.
     */
    public static BeanDefinition getThreadPoolTaskSchedulerBuilderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ThreadPoolTaskSchedulerBuilder.class);
      beanDefinition.setInstanceSupplier(getThreadPoolTaskSchedulerBuilderInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link TaskSchedulingConfigurations.TaskSchedulerBuilderConfiguration}.
   */
  @Generated
  public static class TaskSchedulerBuilderConfiguration {
    /**
     * Get the bean definition for 'taskSchedulerBuilderConfiguration'.
     */
    public static BeanDefinition getTaskSchedulerBuilderConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingConfigurations.TaskSchedulerBuilderConfiguration.class);
      beanDefinition.setInstanceSupplier(TaskSchedulingConfigurations.TaskSchedulerBuilderConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'taskSchedulerBuilder'.
     */
    @SuppressWarnings("removal")
    private static BeanInstanceSupplier<TaskSchedulerBuilder> getTaskSchedulerBuilderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<TaskSchedulerBuilder>forFactoryMethod(TaskSchedulingConfigurations.TaskSchedulerBuilderConfiguration.class, "taskSchedulerBuilder", TaskSchedulingProperties.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(TaskSchedulingConfigurations.TaskSchedulerBuilderConfiguration.class).taskSchedulerBuilder(args.get(0), args.get(1)));
    }

    /**
     * Get the bean definition for 'taskSchedulerBuilder'.
     */
    @SuppressWarnings("removal")
    public static BeanDefinition getTaskSchedulerBuilderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulerBuilder.class);
      beanDefinition.setInstanceSupplier(getTaskSchedulerBuilderInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration}.
   */
  @Generated
  public static class SimpleAsyncTaskSchedulerBuilderConfiguration {
    /**
     * Get the bean instance supplier for 'org.springframework.boot.autoconfigure.task.TaskSchedulingConfigurations$SimpleAsyncTaskSchedulerBuilderConfiguration'.
     */
    private static BeanInstanceSupplier<TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration> getSimpleAsyncTaskSchedulerBuilderConfigurationInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration>forConstructor(TaskSchedulingProperties.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> new TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration(args.get(0), args.get(1)));
    }

    /**
     * Get the bean definition for 'simpleAsyncTaskSchedulerBuilderConfiguration'.
     */
    public static BeanDefinition getSimpleAsyncTaskSchedulerBuilderConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration.class);
      beanDefinition.setInstanceSupplier(getSimpleAsyncTaskSchedulerBuilderConfigurationInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'simpleAsyncTaskSchedulerBuilder'.
     */
    private static BeanInstanceSupplier<SimpleAsyncTaskSchedulerBuilder> getSimpleAsyncTaskSchedulerBuilderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<SimpleAsyncTaskSchedulerBuilder>forFactoryMethod(TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration.class, "simpleAsyncTaskSchedulerBuilder")
              .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration.class).simpleAsyncTaskSchedulerBuilder());
    }

    /**
     * Get the bean definition for 'simpleAsyncTaskSchedulerBuilder'.
     */
    public static BeanDefinition getSimpleAsyncTaskSchedulerBuilderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(SimpleAsyncTaskSchedulerBuilder.class);
      beanDefinition.setInstanceSupplier(getSimpleAsyncTaskSchedulerBuilderInstanceSupplier());
      return beanDefinition;
    }
  }
}
