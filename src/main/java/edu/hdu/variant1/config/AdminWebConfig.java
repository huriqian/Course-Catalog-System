package edu.hdu.variant1.config;

import edu.hdu.variant1.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * AdminWebConfig is used to intercept all requests, including the static resources.
 * Not until the guest login, will the interceptor give the permission to visit the content.
 * For the guest, we only give the permission to visit the login page.
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/index", "/login","/logout","/register");
    }

    /**
     * Use the CorsFilter Filter to configure the cross-domain. Since the Filter is positioned before
     * the Interceptor, the problem is solved
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("token");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    /**
     * The interceptor is executed before SpringContext is initialized, and it's executed before the Bean is
     * initialized, so it definitely can't get the contents of the Spring IOC container. We then let the
     * interceptor instantiate the interceptor Bean as it executes, first instantiating the interceptor in
     * the interceptor configuration class.
     * @return Manual injection of the AuthInterceptor
     */
    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return authInterceptor;
    }
}
