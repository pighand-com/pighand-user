package com.pighand.user;

import com.pighand.framework.spring.PighandFrameworkConfig;
import com.pighand.framework.spring.api.jacksonSerializer.JacksonSerializer;
import com.pighand.framework.spring.api.springdoc.analysis.SpringDocParameter;
import com.pighand.framework.spring.api.springdoc.analysis.SpringDocProperty;
import com.pighand.framework.spring.http.exchange.HttpExchangeRegister;
import com.pighand.user.interceptor.AuthorizationInterceptor;
import com.pighand.user.interceptor.HeaderInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springdoc.core.customizers.ParameterCustomizer;
import org.springdoc.core.customizers.PropertyCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring-boot:run
 *
 * @author wangshuli
 */
@EnableAsync
@EnableWebSecurity
@SpringBootApplication
@MapperScan("com.pighand.user.mapper")
@EnableConfigurationProperties({PighandFrameworkConfig.class})
@Import({HttpExchangeRegister.class, JacksonSerializer.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 分页插件
     *
     * @return
     */
    //    @Bean
    //    public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //        interceptor.addInnerInterceptor(new PageInterceptor(DbType.MYSQL));
    //        return interceptor;
    //    }
    @Bean
    public PropertyCustomizer propertyCustomizer() {
        return (schema, annotatedType) -> SpringDocProperty.analysis(schema, annotatedType);
    }

    @Bean
    public ParameterCustomizer propertyCustomizers() {
        return (parameterModel, methodParameter) -> SpringDocParameter.analysis(parameterModel, methodParameter);
    }

    @Configuration
    @EnableWebMvc
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new HeaderInterceptor());
            registry.addInterceptor(new AuthorizationInterceptor());
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

}
