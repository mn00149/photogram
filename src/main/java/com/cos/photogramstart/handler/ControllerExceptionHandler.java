package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
//런타임 이셉션으로 받았을땐 에러는 오직 제약조건에 관한것만 받을수 있었다
//또한 RuntimeException은 문자열만 받기에 Map타입인 error.getDefaultMessage를
//받을 수 없다 따라서 무수히 많은 http의 에러를 받기위해서Map타입을 받을 수 있는 CustomException을 만들어야 한다
@RestController// 데이터를 리턴하도록 해줌
@ControllerAdvice//모든 Exception을 여기로 받게 해줌
public class ControllerExceptionHandler {
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		// CMRespDTO, Script 비교
		// 1. 클라이언트에게 응답시 Script가 좋음
		// 2. Ajax 통신시-CMRespDTO
		// 3. Android-CMRespDTO
		return Script.back(e.getErrorMap().toString());
	}

}
