package com.cos.photogramstart.domain.user;
//JPA
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //디비에 테이블 생성
public class User {
	@Id//프라이머리키 생성
	@GeneratedValue(strategy=GenerationType.IDENTITY)//번호증가 전략이 디비의 정책에 맞춰짐, 스키마 생성 뒤에 어노테이션 붙이면 에러 => DDL-AUTO를 CREATE으로 바꿀것
	private int id;
	
	@Column(unique = true, nullable=false,length=20)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	private String bio;
	
	@Column(nullable=false)
	private String email;
	
	private String phone;
	
	private String gender;
	
	private String website;

	
	private String profileImageURL;
	private String role; //권한
	
	private LocalDateTime createDate;//디비에 생성된 날짜
	@PrePersist//디비에 인서트되기 직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now();
	}
}
