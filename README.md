# http-funny
http-funny是一个快捷的http服务调用框架。主要功能就是帮助java开发者统一配置管理各种http服务。

## 主要特点：
*  基于注解(annotation)的配置，使配置于代码中，减少了配置文件的维护；
*  httpclient可以定制，可以根据需要来自行配置httpclient的属性。

## 使用示例：
详见代码 `\src\test\java\com\github\changedi\http\core\demoservice`

首先配置用到的http服务，见`BaiduHttpService.java`

```java
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
			@BodyParam("form") Map<String, Object> body, Callback callback);

	@HttpMethod(HttpMethodEnum.POST)
	@Path("/idl_baidu/faceverifyservice/face_deleteuser")
	@Serialization(SerializationEnum.JSON)
	public abstract Map<String, Object> face_deleteuser(
			@QueryParam Map<String, Object> querys,
			@PathParam Map<String, Object> paths,
			@HeaderParam Map<String, Object> headers,
			@BodyParam("json") Map<String, Object> body, Callback callback);

}
```

接下来需要在业务逻辑代码中引用这个服务，见`DemoService.java`

```java
public class DemoService extends HttpServiceBase {

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
```

## 注解说明：
| Annotation | Target | Spec |
| ------- |--------| ----- |
| Scheme | TYPE \ METHOD | 默认是http，表示服务协议（一般都是http或者https） |
| Host | TYPE \ METHOD | 默认是空，服务的host，http://xxx.xx.xx/中的x部分 |
| HttpMethod | METHOD | 默认是HttpMethodEnum.GET 表示http服务的方法类型支持GET\POST\PUT\DELETE |
| Path | TYPE \ METHOD | 默认是空，表示服务的路径，http://xxx.xx.xx/yy/zz/中的y和z部分 |
| Serialization | TYPE \ METHOD | 默认是SerializationEnum.STRING，表示对于服务响应内容的序列化方式，支持json和xml还有普通字符串 |
| QueryParam | PARAMETER | 表示http服务的query，http://xxx.xx.xx/yy/zz?q=aaa&p=bbb中？后面的部分，该注解标注在Map类型的对象上，所有的query都被转为key-value |
| PathParam | PARAMETER | 表示http服务的path替换部分，http://xxx.xx.xx/{yy}/zz?q=aaa&p=bbb中{yy}的部分，该注解标注在Map类型的对象上，Map中的key-value会替换{yy} |
| HeaderParam | PARAMETER | 表示http服务的header部分，该注解标注在Map类型的对象上，Map中的key-value表示header的key-value |
| BodyParam | PARAMETER | 默认值是form，表示http服务post时的body部分，该注解标注在Map类型的对象上，Map中的key-value表示FORM表单的kv部分或者是json格式的内容，json时不关心key是什么 |

## 改进中
1， 对于QueryParam等的限制放开，支持自定义业务对象。

