package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository상속시 어노테이션이 없어도 ioc등록이 된다
public interface UserRepository extends JpaRepository<User, Integer> {
	//JPA query method
	User findByUsername(String username);
	
}
