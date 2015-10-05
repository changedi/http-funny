package com.github.changedi.http.core;

import java.util.Map;

import com.google.common.collect.Maps;

public class DemoService extends HttpServiceAware{

	@HttpService
	private BaiduHttpService service;
	
	public DemoService() {
		super();
	}

	public Map<String, Object> get() {
		Map<String, Object> querys = Maps.newHashMap();
		Map<String, Object> paths = Maps.newHashMap();
		Map<String, Object> headers = Maps.newHashMap();
		querys.put("cityname", "杭州");
		headers.put("apikey", "31da01966ee96d06fdd5c2f2c855424e");
		paths.put("pathId", "weatherservice");
//		Map<String, Object> result = service.listcity(querys, paths, headers);
		Map<String, Object> result = service.listcity2(querys, paths, new Callback(){
			
		});
		return result;
	}
}
