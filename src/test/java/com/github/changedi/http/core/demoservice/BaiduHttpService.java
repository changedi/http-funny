package com.github.changedi.http.core.demoservice;

import java.util.Map;

import com.github.changedi.http.annotation.BodyParam;
import com.github.changedi.http.annotation.HeaderParam;
import com.github.changedi.http.annotation.Host;
import com.github.changedi.http.annotation.HttpMethod;
import com.github.changedi.http.annotation.Path;
import com.github.changedi.http.annotation.PathParam;
import com.github.changedi.http.annotation.QueryParam;
import com.github.changedi.http.annotation.Scheme;
import com.github.changedi.http.annotation.Serialization;
import com.github.changedi.http.core.Callback;

@Scheme
@Host("apis.baidu.com")
public abstract class BaiduHttpService {

	@HttpMethod
	@Path("/apistore/{pathId}/citylist")
	public abstract Map<String, Object> listcity(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers);

	@HttpMethod("get")
	@Path("/apistore/weatherservice/citylist")
	@Serialization("json")
	public abstract Map<String, Object> listcity2(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers, Callback callback);

	@HttpMethod("post")
	@Path("/apistore/idlocr/ocr")
	@Serialization("json")
	public abstract Map<String, Object> ocr(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers,
			@BodyParam("form") Map<String, Object> body, Callback callback);

	@HttpMethod("post")
	@Path("/idl_baidu/faceverifyservice/face_deleteuser")
	@Serialization("json")
	public abstract Map<String, Object> face_deleteuser(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers,
			@BodyParam("json") Map<String, Object> body, Callback callback);

}
