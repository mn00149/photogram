package com.cos.photogramstart.web;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor//final필드를 DI할떄 사용!!
@Controller // 1. ioc 2.파일리턴 하겠다

public class AuthController {
	
	private static final org.jboss.logging.Logger log = LoggerFactory.logger(AuthController.class);
	//@Autowired//ioc에 등록이 되어있기 때문에 의존성 주입 가능, 만약 AuthService에 어노테이션이 없다면 불가능
	private final AuthService authService;
//	public AuthController(AuthService authService) {//어노테이션 없이 DI
//		this.authService=authService;
//	}

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	//회원가입버튼-> signup 페이지 post요청 -> signin 페이지
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		//form으로 data가 날라올떄 => key=value(x-www-form-urlencoded)
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			//bindingResult의 getFieldErrors에 다 모아줌, for문 돌면서 에러를  error에 담아줌
			for(FieldError error: bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("============================");
				System.out.println(error.getDefaultMessage());
				System.out.println("============================");
			}
			//Exception들을 한곳에 모아주자!!
			throw new CustomValidationException("유효성검사 실패", errorMap);
		}else {
			log.info(signupDto);
			//SignupDto->User
			User user = signupDto.toEntity();
			log.info(user.toString());
			User userEntity=authService.회원가입(user);
			System.out.println(userEntity);
			
			return "auth/signin";
		}
		
	}

}
