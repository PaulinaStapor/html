package p.stapor.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT email, password ,1 FROM user_entity WHERE email=?")
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name" +
                        " FROM user_entity u " +
                        " JOIN user_role ur" +
                        " ON u.id=ur.user_entity_id" +
                        " JOIN Role r" +
                         " ON r.id+ur.roles_id" +
                        " WHERE email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/users").hasAnyRole("USER")
                .antMatchers("/case").hasAnyRole("USER")
                .antMatchers("/case/{id}").hasAnyRole("USER")
                .antMatchers("/case/{id}/register").hasAnyRole("USER")
                .antMatchers("/case/{id}/leave").hasAnyRole("USER")
                .antMatchers("/addcase").hasAnyRole("USER")
                .antMatchers("/allcases").hasAnyRole("USER")
                .antMatchers("/case/{id}/comment").hasAnyRole("USER")
                .antMatchers("/addholidays").hasAnyRole("USER")
                .antMatchers("/allholidays").hasAnyRole("USER")

                .anyRequest()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("loginEmail")
                .passwordParameter("loginPassword")
                .loginProcessingUrl("/login-process")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=1")
                .and()

                .logout()
                .logoutSuccessUrl("/index");


    }
}
