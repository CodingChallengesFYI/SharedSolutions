package org.springframework.scheduling.annotation;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.AutowiredMethodArgumentsResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ProxyAsyncConfiguration}.
 */
@Generated
public class ProxyAsyncConfiguration__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ProxyAsyncConfiguration apply(RegisteredBean registeredBean,
      ProxyAsyncConfiguration instance) {
    AutowiredMethodArgumentsResolver.forRequiredMethod("setConfigurers", ObjectProvider.class).resolve(registeredBean, args -> instance.setConfigurers(args.get(0)));
    return instance;
  }
}
