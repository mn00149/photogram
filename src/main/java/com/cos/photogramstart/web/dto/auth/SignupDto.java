package com.cos.photogramstart.web.dto.auth;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data//자동 getter, setter
public class SignupDto {
	//밑의 어노테이션들은 @Valid어노테이션을 하여야 사용 가능하다
	@Size(min=2, max=20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name ;

	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
