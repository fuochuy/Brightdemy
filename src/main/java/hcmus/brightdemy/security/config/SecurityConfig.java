package hcmus.brightdemy.security.config;


import hcmus.brightdemy.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();

        // cấu hình session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // disable csrf
        http.csrf().disable();

        // add jwt filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // cấu hình xác thực cho các api
        // cấu hình xác thực cho các api
        http.antMatcher("/**").authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/openapi/**").permitAll()
                .antMatchers("login").permitAll()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/role/**").permitAll()
                .antMatchers("/api/course/**").permitAll()
                .antMatchers("/api/admin/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/403");

    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/configuration/ui",
            "/configuration/security",
            "/api/**",
            "/login"
    };
}
