package hr.fer.opp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class BasicWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()

                .and()
                .httpBasic()
                .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        }
                )

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isCorsRequest).permitAll()
                .antMatchers("/history/**", "/map", "/map/{\\d+}", "/register", "/clearance").permitAll()
                .antMatchers("/container*", "/neighborhood*", "/employee*").hasAuthority("ADMIN")
                .antMatchers("/route", "/empty/{\\d+}", "/report/**").hasAuthority("EMPLOYEE")
                .antMatchers("/ping/{\\d+}/*", "/favorite", "/favorite/{\\d+}", "/auth").hasAuthority("CITIZEN")

                .and()
                .addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class)
                .formLogin().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
