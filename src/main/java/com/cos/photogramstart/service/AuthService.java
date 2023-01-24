package com.cos.photogramstart.service;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor//final필드를 di 할때 사용 
@Service//1. IOC 2. 트랜잭션 관리
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;//시큐리티 컨피그에서 ioc에 등록함
	
	@Transactional//함수의 시작부터 끝날때 까지 트랜잭션 관리를 해줌, Write(insert, delete, update)시 걸예정 
	public User 회원가입(User user) {
		//회원가입 진행
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");// 관리자는 ROLE_ADMIN
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
