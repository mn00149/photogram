package com.cos.photogramstart.web.dto;



import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	//필수로 받아야 하늩 데이터
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	
	// 받지 않아도 되는 데이터
	private String website;
	private String bio;
	private String phone;
	private String gender;
	//조금 위험함 수정 예정
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
