package com.github.changedi.http.core.demo;

import java.util.Map;

import com.github.changedi.http.annotation.HttpService;
import com.github.changedi.http.core.Callback;
import com.github.changedi.http.core.HttpServiceAware;
import com.github.changedi.http.core.demoservice.BaiduHttpService;
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
//		body.put(
//				"form",
//				"fromdevice=pc&clientip=10.10.10.0&detecttype=LocateRecognize&languagetype=CHN_ENG&imagetype=1&image=/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDABMNDxEPDBMREBEWFRMXHTAfHRsbHTsqLSMwRj5KSUU+RENNV29eTVJpU0NEYYRiaXN3fX59S12Jkoh5kW96fXj/2wBDARUWFh0ZHTkfHzl4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHj/wAARCAAfACEDAREAAhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAQDBQb/xAAjEAACAgICAgEFAAAAAAAAAAABAgADBBESIRMxBSIyQXGB/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/APawEBAQEBAgy8i8ZTVV3UY6V1eU2XoWDDZB19S646Gz39w9fkKsW1r8Wm2yo1PYis1be0JG9H9QNYCAgc35Cl3yuVuJZl0cB41rZQa32dt2y6OuOiOxo61vsLcVblxaVyXD3hFFjL6La7I/sDWAgICAgICB/9k=");
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
}
