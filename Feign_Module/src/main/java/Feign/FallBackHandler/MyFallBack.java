package Feign.FallBackHandler;

import Feign.Service.RequestService;
import Feign.Entity.RequestResult;
import feign.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFallBack implements RequestService {  // fallback 不推荐:不能捕获异常打印堆栈信息，不利于问题排查

	public String helloString() {
		return "远程服务不可用时，暂时采用本地逻辑代替。。。";
	}

	public RequestResult fileUpload(MultipartFile file, HttpServletRequest request) {
		return new RequestResult(401, "远程服务不可用时，暂时采用本地逻辑代替。。。");
	}

	public RequestResult fileUpload(MultipartFile file) {
		return new RequestResult(401, "远程服务不可用时，暂时采用本地逻辑代替。。。");
	}

	public Response fileDownload() {
		return null;
	}


}
