package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDTO;
import com.cos.photogramstart.web.dto.UserUpdateDto;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDTO<?> update(@PathVariable Integer id, 
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult,//반드기 @Valid되는 파라미터 옆에 있어야 함
			@AuthenticationPrincipal PrincipalDetails principalDetails//세션정보를 가져옴
			) {
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
			throw new CustomValidationApiException("유효성검사 실패", errorMap);
		}else {
			//System.out.println(userUpdateDto);
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			//바뀐 회원 정보가 뷰에 반영 되기 위해선 session에 바뀐 정보가 적용되야함
			principalDetails.setUser(userEntity);
			return new CMRespDTO<>(1, "회원수정 완료", userEntity);
		}

	}
}
