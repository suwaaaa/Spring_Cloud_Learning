package Consume.FeignService;


import Consume.Pojo.Hotel;
import Consume.Pojo.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@FeignClient(name = "Provider-Replicas-Publisher")
public interface CallService {

    @GetMapping("/hotel/list")
    PageResult hotelList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "1") Integer size);

    @PostMapping("/hotel")
    void saveHotel(@RequestBody Hotel hotel);

    @GetMapping("/hotel/onlySearch")
    Hotel searchXA();

    @PostMapping("/hotel/postSearch")
    String postSearchXA (@RequestBody Hotel hotel);

}
