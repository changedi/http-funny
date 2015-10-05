package com.github.changedi.http.core;

import java.util.Map;

@Scheme
@Host("apis.baidu.com")
public abstract class BaiduHttpService {

	@Get
	@Path("/apistore/{pathId}/citylist")
	abstract Map<String, Object> listcity(@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers);
	
	@Get
	@Path("/apistore/weatherservice/citylist")
	abstract Map<String, Object> listcity2(@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths, Callback callback);

}
