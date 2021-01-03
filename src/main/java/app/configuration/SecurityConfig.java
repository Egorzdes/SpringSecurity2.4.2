package app.configuration;

import app.configuration.handler.LoginSuccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import app.service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final app.service.UserService UserService;
    private final LoginSuccess loginSuccessHandler;


    public SecurityConfig(UserService userService, LoginSuccess loginSuccessHandler) {
        this.UserService = userService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")
                .successHandler(new LoginSuccess())
                .loginProcessingUrl("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')")
                .anyRequest().authenticated();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
