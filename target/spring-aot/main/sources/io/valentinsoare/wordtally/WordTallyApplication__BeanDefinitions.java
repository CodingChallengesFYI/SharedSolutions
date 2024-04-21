package io.valentinsoare.wordtally;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link WordTallyApplication}.
 */
@Generated
public class WordTallyApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'wordTallyApplication'.
   */
  public static BeanDefinition getWordTallyApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WordTallyApplication.class);
    beanDefinition.setTargetType(WordTallyApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(WordTallyApplication.class);
    beanDefinition.setInstanceSupplier(WordTallyApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
