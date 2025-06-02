package vn.tr.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private final String adminContextPath;
	
	public SecurityConfig(AdminServerProperties adminServerProperties) {
		this.adminContextPath = adminServerProperties.getContextPath();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminContextPath + "/");
		
		return httpSecurity
				.headers((header) ->
						header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				.authorizeHttpRequests((authorize) ->
						authorize.requestMatchers(
										PathPatternRequestMatcher.withDefaults().matcher(adminContextPath + "/assets/**"),
										PathPatternRequestMatcher.withDefaults().matcher(adminContextPath + "/actuator/**"),
										PathPatternRequestMatcher.withDefaults().matcher(adminContextPath + "/instances/**"),
										PathPatternRequestMatcher.withDefaults().matcher(adminContextPath + "/login")
						                         ).permitAll()
								.anyRequest().authenticated())
				.formLogin((formLogin) ->
						formLogin.loginPage(adminContextPath + "/login").successHandler(successHandler))
				.logout((logout) ->
						logout.logoutUrl(adminContextPath + "/logout"))
				.httpBasic(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.build();
	}
	
}
