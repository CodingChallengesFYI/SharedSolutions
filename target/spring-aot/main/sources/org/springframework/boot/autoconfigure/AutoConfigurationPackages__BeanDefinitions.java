package org.springframework.boot.autoconfigure;

import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AutoConfigurationPackages}.
 */
@Generated
public class AutoConfigurationPackages__BeanDefinitions {
  /**
   * Bean definitions for {@link AutoConfigurationPackages.BasePackages}.
   */
  @Generated
  public static class BasePackages {
    /**
     * Get the bean instance supplier for 'org.springframework.boot.autoconfigure.AutoConfigurationPackages'.
     */
    private static BeanInstanceSupplier<AutoConfigurationPackages.BasePackages> getAutoConfigurationPackagesInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<AutoConfigurationPackages.BasePackages>forConstructor(String[].class)
              .withGenerator((registeredBean, args) -> new AutoConfigurationPackages.BasePackages(args.get(0)));
    }

    /**
     * Get the bean definition for 'autoConfigurationPackages'.
     */
    public static BeanDefinition getAutoConfigurationPackagesBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(AutoConfigurationPackages.BasePackages.class);
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, new String[] {"io.valentinsoare.wordtally"});
      beanDefinition.setInstanceSupplier(getAutoConfigurationPackagesInstanceSupplier());
      return beanDefinition;
    }
  }
}
