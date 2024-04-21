package org.springframework.boot.autoconfigure.jackson;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.boot.jackson.JsonMixinModule;
import org.springframework.boot.jackson.JsonMixinModuleEntries;
import org.springframework.context.ApplicationContext;

/**
 * Bean definitions for {@link JacksonAutoConfiguration}.
 */
@Generated
public class JacksonAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'jacksonAutoConfiguration'.
   */
  public static BeanDefinition getJacksonAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(JacksonAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jsonComponentModule'.
   */
  private static BeanInstanceSupplier<JsonComponentModule> getJsonComponentModuleInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JsonComponentModule>forFactoryMethod(JacksonAutoConfiguration.class, "jsonComponentModule")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(JacksonAutoConfiguration.class).jsonComponentModule());
  }

  /**
   * Get the bean definition for 'jsonComponentModule'.
   */
  public static BeanDefinition getJsonComponentModuleBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JsonComponentModule.class);
    beanDefinition.setInstanceSupplier(getJsonComponentModuleInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link JacksonAutoConfiguration.JacksonMixinConfiguration}.
   */
  @Generated
  public static class JacksonMixinConfiguration {
    /**
     * Get the bean definition for 'jacksonMixinConfiguration'.
     */
    public static BeanDefinition getJacksonMixinConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonAutoConfiguration.JacksonMixinConfiguration.class);
      beanDefinition.setInstanceSupplier(JacksonAutoConfiguration.JacksonMixinConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jsonMixinModule'.
     */
    private static BeanInstanceSupplier<JsonMixinModule> getJsonMixinModuleInstanceSupplier() {
      return BeanInstanceSupplier.<JsonMixinModule>forFactoryMethod(JacksonAutoConfiguration.JacksonMixinConfiguration.class, "jsonMixinModule", ApplicationContext.class, JsonMixinModuleEntries.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(JacksonAutoConfiguration.JacksonMixinConfiguration.class).jsonMixinModule(args.get(0), args.get(1)));
    }

    /**
     * Get the bean definition for 'jsonMixinModule'.
     */
    public static BeanDefinition getJsonMixinModuleBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(JsonMixinModule.class);
      beanDefinition.setInstanceSupplier(getJsonMixinModuleInstanceSupplier());
      return beanDefinition;
    }
  }
}
