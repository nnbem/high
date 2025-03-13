package common.application.commons.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;

import common.application.commons.security.handler.CompanyLoginSuccessHandler;
import common.application.commons.security.handler.LoginFailureHandler;
import common.application.commons.security.handler.MemberLoginSuccessHandler;
import common.application.commons.security.provider.CompanyCustomAuthenticationProvider;
import common.application.commons.security.provider.MemberCustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final MemberCustomAuthenticationProvider MemberCustomAuthenticationProvider;
    private final CompanyCustomAuthenticationProvider CompanyCustomAuthenticationProvider;
    private final MemberLoginSuccessHandler MemberLoginSuccessHandler;
    private final CompanyLoginSuccessHandler CompanyLoginSuccessHandler;
    private final LoginFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain memberFilterChain(HttpSecurity http) throws Exception {

          http
          .securityMatcher("/class/**")
          .authenticationManager(new ProviderManager(MemberCustomAuthenticationProvider))
                                .csrf(AbstractHttpConfigurer::disable)
                                .exceptionHandling((exception) -> exception.accessDeniedPage("/commons/accessDenied"))
                                .authorizeHttpRequests((authorize) -> authorize
                                                        .requestMatchers("/class/mypage").hasAnyRole("USER", "ADMIN")
                                                        .anyRequest().permitAll())
                                .formLogin(formLogin -> formLogin
                                                        .loginPage("/class/loginForm")
                                                        .loginProcessingUrl("/class/login")
                                                        .usernameParameter("mid")
                                                        .passwordParameter("pwd")
                                                        .defaultSuccessUrl("/class/main")
                                                        .successHandler(MemberLoginSuccessHandler)
                                                        .failureHandler(failureHandler))
                                .logout((logout) -> logout
                                                    .logoutUrl("/class/logout")
                                                    .logoutSuccessUrl("/class/main")
                                                    .deleteCookies("JSESSIONID")
                                                    .invalidateHttpSession(true))
                                .sessionManagement(session -> session
                                                    .invalidSessionUrl("/class/loginTimeOut")
                                                    .maximumSessions(2)
                                                    .expiredUrl("/commons/loginExpired")
                                                    .sessionRegistry(new SessionRegistryImpl()))
                                .headers(headersConfigurer -> headersConfigurer
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));


        return http.build();
    }

    
    @Bean
    public SecurityFilterChain CompanyFilterChain(HttpSecurity http) throws Exception {

          http
          .securityMatcher("/job/**")
          .authenticationManager(new ProviderManager(CompanyCustomAuthenticationProvider))
                                .csrf(AbstractHttpConfigurer::disable)
                                .exceptionHandling((exception) -> exception.accessDeniedPage("/commons/accessDenied"))
                                .authorizeHttpRequests((authorize) -> authorize
                                                        .anyRequest().permitAll())
                                .formLogin(formLogin -> formLogin
                                                        .loginPage("/job/loginForm")
                                                        .loginProcessingUrl("/job/login")
                                                        .usernameParameter("cno")
                                                        .passwordParameter("pwd")
                                                        .defaultSuccessUrl("/job/main")
                                                        .successHandler(CompanyLoginSuccessHandler)
                                                        .failureHandler(failureHandler))
                                .logout((logout) -> logout
                                                    .logoutUrl("/job/logout")
                                                    .logoutSuccessUrl("/job/loginForm")
                                                    .deleteCookies("JSESSIONID")
                                                    .invalidateHttpSession(true))
                                .sessionManagement(session -> session
                                                    .invalidSessionUrl("/job/loginTimeOut")
                                                    .maximumSessions(2)
                                                    .expiredUrl("/commons/loginExpired")
                                                    .sessionRegistry(new SessionRegistryImpl()))
                                .headers(headersConfigurer -> headersConfigurer
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));


        return http.build();
    }
}
