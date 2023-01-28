package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	// DI
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public User 회원수정(Integer id, User user) {
		//1. 영속화 하기
		//jpa는 optionnal을 리턴 1-.get()데이터는 무조건 찾을것이니 신경 마라!, 2-orElseThrow() null일떄 Exception 발동
		// 1. 무조건 찾았다. 걱정마 get() 2. 못찾았어 익섹션 발동시킬께 orElseThrow()
				User userEntity = userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 id입니다.");});
		
		//2.영속화된 오브젝트 수정하기
		//2-1 password 암호화 안하면 스프링 시큐리티가 로그인 처리를 안함
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
				
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	}// 3. 리턴 완료되면 더티체킹이 일어나면서 업데이트 완료
}
