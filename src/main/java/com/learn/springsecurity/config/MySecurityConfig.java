package com.learn.springsecurity.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// isse hum role based authentication ko hum annotation se use kr skte hai  iske liye hume
// .antMatchers("/home","/login","/register").permitAll() use nahi krna hoga
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    // this is basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // agar hum CSRF ko disable nahi krte hai to hume unwanted action perform krwata hai jese ki hum agr nahi
                // use krte hai isko to hum addUser method ko use nahi kr sakte hai
                .csrf().disable()

                .authorizeRequests()

                // particular har ek method ko hum public kr rahe hai
                .antMatchers("/home","/login","/register").permitAll()

                // isme direct class lavel ka use ho raha hai jisme hum us particular class ke sare method ko public kr rhe hai
//                .antMatchers("/public/**").permitAll()

                // isme hum particular GET/POST or koi bhi method ko define  kr skte hai
//                .antMatchers(HttpMethod.GET,"/public/**").permitAll()

                // role based access de skte hai
//                .antMatchers("/public/**").hasRole("ADMIN")

                .anyRequest()
                .authenticated()
                .and()
                 
                // basic authentication ke liye .httpBasic() use krte hai
//                .httpBasic();

                // form bases authentication ke liye  .formLogin use krte hai
                .formLogin();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        ikso passwordEncoder ke help se rum kiye hai
//        auth.inMemoryAuthentication().withUser("ankit").password("ankit").roles("NORMAL");

        // isko passwordEncoderUsingEncoder method ke help se kiye hai
        auth.inMemoryAuthentication().withUser("abc").password(this.passwordEncoderUsingEncoder().encode("abc")).roles("ADMIN");
    }

    // isme password incode kar ke rakhta hai lekin hum password mai normal text form mai use kr rhe hai is liye ye
    // passwordEncoder method hum use kr rhe hai
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoderUsingEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
