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
import com.github.changedi.http.annotation.consts.HttpMethodEnum;
import com.github.changedi.http.annotation.consts.PostContentEnum;
import com.github.changedi.http.annotation.consts.SerializationEnum;
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

	@HttpMethod(HttpMethodEnum.GET)
	@Path("/apistore/weatherservice/citylist")
	@Serialization(SerializationEnum.JSON)
	public abstract Map<String, Object> listcity2(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers, Callback callback);

	@HttpMethod(HttpMethodEnum.POST)
	@Path("/apistore/idlocr/ocr")
	@Serialization(SerializationEnum.JSON)
	public abstract Map<String, Object> ocr(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers,
			@BodyParam(PostContentEnum.FORM) Map<String, Object> body,
			Callback callback);

	@HttpMethod(HttpMethodEnum.POST)
	@Path("/idl_baidu/faceverifyservice/face_deleteuser")
	@Serialization(SerializationEnum.JSON)
	public abstract Map<String, Object> face_deleteuser(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers,
			@BodyParam(PostContentEnum.JSON) Map<String, Object> body,
			Callback callback);

}
