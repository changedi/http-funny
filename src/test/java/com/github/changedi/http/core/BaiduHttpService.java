package com.github.changedi.http.core;

import java.util.Map;

@Scheme
@Host("apis.baidu.com")
public abstract class BaiduHttpService {

	@Method
	@Path("/apistore/{pathId}/citylist")
	abstract Map<String, Object> listcity(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers);

	@Method("get")
	@Path("/apistore/weatherservice/citylist")
	@Serialization("json")
	abstract Map<String, Object> listcity2(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers, Callback callback);

	@Method("post")
	@Path("/apistore/idlocr/ocr")
	@Serialization("json")
	abstract Map<String, Object> ocr(@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers,
			@BodyParam("string") Map<String, Object> body, Callback callback);

}
