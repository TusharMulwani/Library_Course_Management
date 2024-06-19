package com.learningcourse.util;

import org.springframework.stereotype.Component;

import com.learningcourse.DTO.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Component
public class ResponseUtil {
public ResponseEntity<?> createResponseEntity(HttpStatus code, String message)
{
	ResponseDTO resp = new ResponseDTO();
	resp.setStatusCode(code.value());
	resp.setMessage(message);
	return ResponseEntity.status(code).body(resp);
}
}