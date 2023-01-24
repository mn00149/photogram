package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable Integer id) {
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable Integer id, 
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			Model model) {
		System.out.println("세션 정보 확인:" + principalDetails.getUser());
//		model.addAttribute("principal", principalDetails.getUser()); jsp taglib을 사용하므로 jsp에는 필요 없음, 만약 다른 템플렛을 사용하면 필요
		return "user/update";
	}
	
}
