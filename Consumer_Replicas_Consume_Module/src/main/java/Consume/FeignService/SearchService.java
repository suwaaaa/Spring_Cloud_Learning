package Consume.FeignService;


import Consume.Pojo.PageResult;
import Consume.Pojo.RequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Component
@FeignClient(name = "Provider-Replicas-Elasticsearch")
public interface SearchService {

    @PostMapping("/hotel/list")
    PageResult search(@RequestBody RequestParams params);
}
