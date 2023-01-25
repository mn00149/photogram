package com.cos.photogramstart.web.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
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
			UserUpdateDto userUpdateDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails//세션정보를 가져옴
			) {
		//System.out.println(userUpdateDto);
		User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		//바뀐 회원 정보가 뷰에 반영 되기 위해선 session에 바뀐 정보가 적용되야함
		principalDetails.setUser(userEntity);
		return new CMRespDTO<>(1, "회원수정 완료", userEntity);
	}
}
