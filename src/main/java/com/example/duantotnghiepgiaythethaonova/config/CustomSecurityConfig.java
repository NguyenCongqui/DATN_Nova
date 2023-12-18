package com.example.duantotnghiepgiaythethaonova.config;

import com.example.duantotnghiepgiaythethaonova.security.MySimpleUrlAuthenticationSuccessHandler;
import com.example.duantotnghiepgiaythethaonova.security.oauth.CustomOAuth2UserService;
import com.example.duantotnghiepgiaythethaonova.security.oauth.OAuth2LoginSuccessHandler;
import com.example.duantotnghiepgiaythethaonova.service.impl.NguoiDungDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    NguoiDungDetailsService nguoiDungDetailsService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(nguoiDungDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(
                        "/customer/css/**", "/customer/fonts/**", "/customer/js/**", "/khach-hang/images/**","/customer/img/**", "/customer/view/**",
                        "/khach-hang/home/**", "/khach-hang/san-pham/**", "/khach-hang/shop-details/**","/khach-hang/san-pham-chi-tiet/**" , "/khach-hang/SoLuongSanPhamChiTiet",
                        "/MuaNgaySanPham/checkout/**",

                        "/admin/css/**", "/admin/images/**", "/admin/img/**", "/admin/imgLibr/**",
                        "/admin/js/**", "/admin/js/khach-hang/**", "/admin/scss/**", "/admin/vendor/**", "/admin/vendor test/**",

                        "/security/**",
                        "/oauth2/**"
                )
                .permitAll()
                .antMatchers("/admin/NguoiDung/danhSach").hasAnyRole("ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/khach-hang/**").hasAnyRole("ADMIN", "STAFF", "CUSTOMER")
                .and().formLogin().
                loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success?alert=success")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/security/login/error?alert=danger").and().oauth2Login().
                loginPage("/security/login/form")
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and().
                successHandler(oAuth2LoginSuccessHandler);
        ;


        http.rememberMe()
                .tokenValiditySeconds(86400);

        http.exceptionHandling()
                .accessDeniedPage("/security/unauthoried?alert=danger");

        http.logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success?alert=success");
        http.csrf().disable();
    }


}