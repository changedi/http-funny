package com.github.changedi.http.core.demo;

import java.util.List;
import java.util.Map;

import com.github.changedi.http.annotation.HttpService;
import com.github.changedi.http.core.Callback;
import com.github.changedi.http.core.HttpServiceAware;
import com.github.changedi.http.core.demoservice.BaiduHttpService;
import com.github.changedi.http.core.demoservice.model.FaceDeleteUserInternalModel;
import com.github.changedi.http.core.demoservice.model.FaceDeleteUserModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DemoService extends HttpServiceAware {

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
		// Map<String, Object> result = service.listcity(querys, paths,
		// headers);
		Map<String, Object> result = service.listcity2(querys, paths, headers,
				new Callback() {
					@Override
					public void execute(Map<String, Object> querys,
							Map<String, Object> paths,
							Map<String, Object> headers,
							Map<String, Object> body) {
						System.out.println(querys);
						querys.put("aa", "bb");
					}
				});
		return result;
	}

	public Map<String, Object> post() {
		Map<String, Object> querys = Maps.newHashMap();
		Map<String, Object> paths = Maps.newHashMap();
		Map<String, Object> headers = Maps.newHashMap();
		Map<String, Object> body = Maps.newHashMap();
		headers.put("apikey", "31da01966ee96d06fdd5c2f2c855424e");
		body.put("fromdevice", "pc");
		body.put("clientip", "10.10.10.0");
		body.put("detecttype", "LocateRecognize");
		body.put("languagetype", "CHN_ENG");
		body.put("imagetype", "1");
		body.put(
				"image",
				"/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDABMNDxEPDBMREBEWFRMXHTAfHRsbHTsqLSMwRj5KSUU+RENNV29eTVJpU0NEYYRiaXN3fX59S12Jkoh5kW96fXj/2wBDARUWFh0ZHTkfHzl4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHj/wAARCAAfACEDAREAAhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAQDBQb/xAAjEAACAgICAgEFAAAAAAAAAAABAgADBBESIRMxBSIyQXGB/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/APawEBAQEBAgy8i8ZTVV3UY6V1eU2XoWDDZB19S646Gz39w9fkKsW1r8Wm2yo1PYis1be0JG9H9QNYCAgc35Cl3yuVuJZl0cB41rZQa32dt2y6OuOiOxo61vsLcVblxaVyXD3hFFjL6La7I/sDWAgICAgICB/9k=");
		// body.put(
		// "form",
		// "fromdevice=pc&clientip=10.10.10.0&detecttype=LocateRecognize&languagetype=CHN_ENG&imagetype=1&image=/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDABMNDxEPDBMREBEWFRMXHTAfHRsbHTsqLSMwRj5KSUU+RENNV29eTVJpU0NEYYRiaXN3fX59S12Jkoh5kW96fXj/2wBDARUWFh0ZHTkfHzl4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHj/wAARCAAfACEDAREAAhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAQDBQb/xAAjEAACAgICAgEFAAAAAAAAAAABAgADBBESIRMxBSIyQXGB/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/APawEBAQEBAgy8i8ZTVV3UY6V1eU2XoWDDZB19S646Gz39w9fkKsW1r8Wm2yo1PYis1be0JG9H9QNYCAgc35Cl3yuVuJZl0cB41rZQa32dt2y6OuOiOxo61vsLcVblxaVyXD3hFFjL6La7I/sDWAgICAgICB/9k=");
		Map<String, Object> result = service.ocr(querys, paths, headers, body,
				new Callback() {
					@Override
					public void execute(Map<String, Object> querys,
							Map<String, Object> paths,
							Map<String, Object> headers,
							Map<String, Object> body) {
					}
				});
		return result;
	}

	public Map<String, Object> postJson() {
		Map<String, Object> querys = Maps.newHashMap();
		Map<String, Object> paths = Maps.newHashMap();
		Map<String, Object> headers = Maps.newHashMap();
		Map<String, Object> body = Maps.newHashMap();
		headers.put("apikey", "31da01966ee96d06fdd5c2f2c855424e");
		FaceDeleteUserModel param = new FaceDeleteUserModel();
		param.setJsonrpc("2.0");
		param.setMethod("Delete");
		param.setId(12);
		List<FaceDeleteUserInternalModel> paramList = Lists.newArrayList();
		FaceDeleteUserInternalModel innerMap = new FaceDeleteUserInternalModel();
		innerMap.setVersionnum("1.0.0.1");
		innerMap.setGroupid("0");
		innerMap.setType("st_groupverify");
		innerMap.setClientip("10.23.34.5");
		innerMap.setAppid("31da01966ee96d06fdd5c2f2c855424e");
		innerMap.setLogid("12345");
		innerMap.setCmdid("1000");
		innerMap.setUsername("test");
		paramList.add(innerMap);
		param.setParams(paramList);
		
		body.put("anykey", param);
//		body.put(
//				"anykey",
//				"{\"params\": [    {      \"username\":\"test\",      \"cmdid\":\"1000\",      \"logid\": \"12345\",      \"appid\": \"31da01966ee96d06fdd5c2f2c855424e\",      \"clientip\":\"10.23.34.5\",      \"type\":\"st_groupverify\",      \"groupid\": \"0\",      \"versionnum\": \"1.0.0.1\",    }  ],  \"jsonrpc\": \"2.0\",  \"method\": \"Delete\",  \"id\":12}");
		Map<String, Object> result = service.face_deleteuser(querys, paths,
				headers, body, new Callback() {
					@Override
					public void execute(Map<String, Object> querys,
							Map<String, Object> paths,
							Map<String, Object> headers,
							Map<String, Object> body) {
					}
				});
		return result;
	}
}
