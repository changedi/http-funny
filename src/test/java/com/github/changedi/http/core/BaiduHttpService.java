package com.github.changedi.http.core;

import java.util.Map;

@Schema("http")
@Host("apis.baidu.com")
public interface BaiduHttpService {

	@Get
	@Path("/apistore/weatherservice/citylist")
	Map<String, Object> listcity(@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers);

}
