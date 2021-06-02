package khannanov.accountantbot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource

@Configuration
class AppConfig {

    @Bean
    fun messageSource() = ResourceBundleMessageSource().apply {
        setBasename("dictionary/messages")
        setDefaultEncoding("UTF-8")
    }
}