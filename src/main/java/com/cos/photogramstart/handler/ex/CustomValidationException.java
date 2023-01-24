package com.cos.photogramstart.handler.ex;

import java.util.Map;
//exception이 되기 위해 runtimeeception을 상속함!
public class CustomValidationException extends RuntimeException {

	/**
	 * 
	 */
	// 객체를 구분하기 위해 시리얼 넘버를 넣어준다 우리한텐 안중요함, 무시해도 됨 , JVM을 위해 넣어주는 것이다
	// 코딩과는 노상관
	private static final long serialVersionUID = 1L;
	private Map<String, String> errorMap;

	public CustomValidationException(String message, Map<String, String> errorMap) {
		//message의 경우 부모클래스가 알아서 set해준가
		super(message);
		this.errorMap = errorMap;

	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
}