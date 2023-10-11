package poscodx.mysite.config;

import java.util.List;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import poscodx.mysite.event.ApplicationContextEventListener;
import poscodx.mysite.interceptor.SiteInterceptor;
import poscodx.mysite.security.AuthInterceptor;
import poscodx.mysite.security.AuthUserHandlerMethodArgumentResolver;
import poscodx.mysite.security.LoginInterceptor;
import poscodx.mysite.security.LogoutInterceptor;


@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
	//
	// Application ConextEvent Listener
	//
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}

	//
	// Argument Resolver
	//
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(handlerMethodArgumentResolver());
	}

	// Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}

	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}

	// Site Interceptor
	@Bean
	public HandlerInterceptor siteInterceptor() {
		return new SiteInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(loginInterceptor())
				.addPathPatterns("/user/auth");

		registry
				.addInterceptor(logoutInterceptor())
				.addPathPatterns("/user/logout");

		registry
				.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**", "/user/auth", "/user/logout");

		registry
				.addInterceptor(siteInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**");
	}
}