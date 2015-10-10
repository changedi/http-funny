package com.github.changedi.http.core;

import java.util.Map;

public interface Callback {

	void execute(Map<String, Object> querys, Map<String, Object> paths,
			Map<String, Object> headers, Map<String, Object> body);
}
