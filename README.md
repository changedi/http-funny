# http-funny
http-funny��һ����ݵ�http������ÿ�ܡ���Ҫ���ܾ��ǰ���java������ͳһ���ù������http����

## ��Ҫ�ص㣺
*  ����ע��(annotation)�����ã�ʹ�����ڴ����У������������ļ���ά����
*  httpclient���Զ��ƣ����Ը�����Ҫ����������httpclient�����ԡ�

## ʹ��ʾ����
������� `\src\test\java\com\github\changedi\http\core\demoservice`

���������õ���http���񣬼�`BaiduHttpService.java`

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

��������Ҫ��ҵ���߼�����������������񣬼�`DemoService.java`

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
		querys.put("cityname", "����");
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

## ע��˵����
| Annotation | Target | Spec |
| ------- |--------| ----- |
| Scheme | TYPE \ METHOD | Ĭ����http����ʾ����Э�飨һ�㶼��http����https�� |
| Host | TYPE \ METHOD | Ĭ���ǿգ������host��http://xxx.xx.xx/�е�x���� |
| HttpMethod | METHOD | Ĭ����HttpMethodEnum.GET ��ʾhttp����ķ�������֧��GET\POST\PUT\DELETE |
| Path | TYPE \ METHOD | Ĭ���ǿգ���ʾ�����·����http://xxx.xx.xx/yy/zz/�е�y��z���� |
| Serialization | TYPE \ METHOD | Ĭ����SerializationEnum.STRING����ʾ���ڷ�����Ӧ���ݵ����л���ʽ��֧��json��xml������ͨ�ַ��� |
| QueryParam | PARAMETER | ��ʾhttp�����query��http://xxx.xx.xx/yy/zz?q=aaa&p=bbb�У�����Ĳ��֣���ע���ע��Map���͵Ķ����ϣ����е�query����תΪkey-value |
| PathParam | PARAMETER | ��ʾhttp�����path�滻���֣�http://xxx.xx.xx/{yy}/zz?q=aaa&p=bbb��{yy}�Ĳ��֣���ע���ע��Map���͵Ķ����ϣ�Map�е�key-value���滻{yy} |
| HeaderParam | PARAMETER | ��ʾhttp�����header���֣���ע���ע��Map���͵Ķ����ϣ�Map�е�key-value��ʾheader��key-value |
| BodyParam | PARAMETER | Ĭ��ֵ��form����ʾhttp����postʱ��body���֣���ע���ע��Map���͵Ķ����ϣ�Map�е�key-value��ʾFORM����kv���ֻ�����json��ʽ�����ݣ�jsonʱ������key��ʲô |

## �Ľ���
1�� ����QueryParam�ȵ����Ʒſ���֧���Զ���ҵ�����

