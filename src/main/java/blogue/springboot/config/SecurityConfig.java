package blogue.springboot.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DyoRizqal
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    private static final String SQL_LOGIN = "select username, password, active as enabled "+
            "from c_security_user where username = ?";
    private static final String SQL_ROLE
            = "select u.username, p.permission_value as authority "
            + "from c_security_user u "
            + "inner join c_security_role r on u.id_role =  r.id "
            + "inner join c_security_role_permission rp on rp.id_role = r.id "
            + "inner join c_security_permission p on rp.id_permission = p.id "
            + "where u.username = ?";
    @Autowired
    public void configueGlobal(AuthenticationManagerBuilder auth)
            throws Exception{
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_ROLE);
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/blogue/**").hasRole("BLOGUE_VIEW")
                .antMatchers(HttpMethod.POST,"/api/blogue/**").hasRole("BLOGUE_CREATE")
                .antMatchers(HttpMethod.DELETE,"/api/blogue/**").hasRole("BLOGUE_DELETE")
                .antMatchers(HttpMethod.PUT,"/api/blogue/**").hasRole("BLOGUE_UPDATE")
                .antMatchers(HttpMethod.GET,"/api/user/**").hasRole("USER_VIEW")
                .antMatchers("/lib/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();
    }
}
    

    