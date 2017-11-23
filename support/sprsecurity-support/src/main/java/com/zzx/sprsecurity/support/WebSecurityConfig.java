package com.zzx.sprsecurity.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity //可以省略
// @EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//    private MyAuthenticationProvider provider;//自定义验证
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();

		// http
		// //禁用CSRF保护
		// .csrf().disable()
		// .authorizeRequests()
		// //任何访问都必须授权
		// .anyRequest().fullyAuthenticated()
		// //配置那些路径可以不用权限访问
		// .mvcMatchers("/login").permitAll()
		// .and()
		// .formLogin()
		// //登陆成功后的处理，因为是API的形式所以不用跳转页面
		// .successHandler(new RestAuthenticationSuccessHandler())
		// //登陆失败后的处理
		// .failureHandler(new SimpleUrlAuthenticationFailureHandler())
		// .and()
		// //登出后的处理
		// .logout().logoutSuccessHandler(new RestLogoutSuccessHandler())
		// .and()
		// //认证不通过后的处理
		// .exceptionHandling()
		// .authenticationEntryPoint(new RestAuthenticationEntryPoint())
		// ;

		
//		http.authorizeRequests()
//			.antMatchers(StaticParams.PATHREGX.NOAUTH, StaticParams.PATHREGX.CSS, StaticParams.PATHREGX.JS,
//					StaticParams.PATHREGX.IMG)
//			.permitAll()// 无需访问权限
//			.antMatchers(StaticParams.PATHREGX.AUTHADMIN).hasAuthority(StaticParams.USERROLE.ROLE_ADMIN)// admin角色访问权限
//			.antMatchers(StaticParams.PATHREGX.AUTHUSER).hasAuthority(StaticParams.USERROLE.ROLE_USER)// user角色访问权限
//			.anyRequest()// all others request authentication
//			.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		
		//将验证过程交给自定义验证工具
//        auth.authenticationProvider(provider);
	}
	
	
//	@Autowired
//    MyUserDetailsService detailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true)
//                .and().logout().logoutUrl("/logout")
//                .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
//                .and()
//                .and().exceptionHandling().accessDeniedPage("/accessDenied");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
