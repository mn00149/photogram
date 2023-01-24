package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDTO<T> {
	private int code;// 성공:1, 실패:-1  => 성공과 실패를 코드로 나타냄
	private String meassage;
	private T data;
}
