package com.cos.photogramstart.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableWebSecurity // 해당파일로 시큐리티 활성화
@Configuration // ioc
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean// 시큐리티 컨피그 실행시 실행되어 리턴해줌으로서 ioc에 등록 해줌
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	//로그인 로직은 시큐리티가 가지고 있으므로 컨트롤러를 안 만들어도 된다 위임해주면 됨
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 슈펴삭제 => 기존 시큐리티가 가지고있는 모든 기능이 비활성화
		http.csrf().disable();//csrf 해제
		http.authorizeRequests()
		.antMatchers("/", "/user/**", "/image/**", "subscribe/**", "/comment/**").authenticated()//해당 주소들은 인증이 필요함
		.anyRequest().permitAll()//그외의 주소들은 그냥 허용
		.and()
		.formLogin()//로그인 인증이 필요한 요청이 오면
		.loginPage("/auth/signin")//인증이 필요한 페이지에 인증 없이 요청하면 요 페이지로 이동(get요청)
		.loginProcessingUrl("/auth/signin")//해당주소로 post요청
		.defaultSuccessUrl("/");//로그인 성공시 들어가는 페이지
	}

}
