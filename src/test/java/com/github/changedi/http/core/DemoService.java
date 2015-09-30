package com.github.changedi.http.core;

import java.util.Map;

import com.google.common.collect.Maps;

public class DemoService {

	@HttpService
	private BaiduHttpService service;

	public Map<String, Object> get() {
		Map<String, Object> querys = Maps.newHashMap();
		Map<String, Object> paths = Maps.newHashMap();
		Map<String, Object> headers = Maps.newHashMap();
		querys.put("cityname", "杭州");
		Map<String, Object> result = service.listcity(querys, paths, headers);
		return result;
	}
}
