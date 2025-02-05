package sn.uasz.demoJPA.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
   //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
       return new InMemoryUserDetailsManager(
               User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
               User.withUsername("SENE").password(passwordEncoder.encode("1234")).roles("USER").build(),
               User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build(),
               User.withUsername("KASSE").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
       );
   }
   @Bean
   public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
   }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
               .formLogin(ar ->ar.loginPage("/login").defaultSuccessUrl("/").permitAll())
               .authorizeHttpRequests(arg ->arg
                       .requestMatchers("/webjars/**","/inscrire","/save").permitAll()
                       .requestMatchers("/user/**").hasRole("USER")
                       .requestMatchers("/admin/**").hasRole("ADMIN")
                       .anyRequest().authenticated())
               .exceptionHandling(a ->a.accessDeniedPage("/notAuthorized"))
               .build();
   }
}
