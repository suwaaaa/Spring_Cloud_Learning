package Feign.FallBackHandler;

import Feign.Service.RequestService;
import Feign.Entity.RequestResult;
import feign.Response;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
  *  Class Name: MyFallBackFactory.java    fallbackFactory 推荐：可以捕获异常信息并返回默认降级结果。可以打印堆栈信息
  *  Description:
  *  服务熔断获取异常信息，拿到远程服务的异常信息
  */
@Slf4j
public class MyFallBackFactory implements FallbackFactory<RequestService> {

	public RequestService create(final Throwable cause) {
		return new RequestService() {
			public String helloString() {
				System.out.println("远程服务helloString不可用时，暂时采用本地逻辑代替");
				log.error("远程服务helloString不可用",cause);
				return "远程服务helloString不可用时，暂时采用本地逻辑代替: " + cause.getMessage();
			}

			public RequestResult fileUpload(MultipartFile file, HttpServletRequest request) {
				return new RequestResult(401, "远程服务不可用时，暂时采用本地逻辑代替。。。"+cause.getMessage());
			}

			public RequestResult fileUpload(MultipartFile file) {
				return new RequestResult(401, "远程服务不可用时，暂时采用本地逻辑代替。。。"+cause.getMessage());
			}

			public Response fileDownload() {
				return null;
			}
		};
	}


}
