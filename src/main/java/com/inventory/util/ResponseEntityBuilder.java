
package com.inventory.util;

import org.springframework.http.ResponseEntity;
import com.nagarro.inventory.exceptionshandler.ApiError;

public class ResponseEntityBuilder {
	public static ResponseEntity<Object> build(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
