package Consume.Service.ServiceImp;

import Consume.FeignService.CallService;
import Consume.FeignService.SearchService;
import Consume.Pojo.Hotel;
import Consume.Pojo.PageResult;
import Consume.Pojo.RequestParams;
import Consume.Service.XAService;
import com.alibaba.fastjson.JSON;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Java_IBM_Learning Consume.Service.ServiceImp
 *
 * @author 12645
 * @createTime 2023/3/27 14:50
 * @description
 */
@Component
public class XAServiceImp implements XAService {

    private final CallService callService;
    private final SearchService searchService;

    @Autowired
    public XAServiceImp(CallService callService, SearchService searchService) {
        this.callService = callService;
        this.searchService = searchService;
    }


    @Override
    @GlobalTransactional
    public String testSeataTransactionXA() {
        //call
        Hotel hotel = callService.searchXA();
        System.out.println(hotel);
        hotel.setPrice(hotel.getPrice()+1);
        //save
        callService.postSearchXA(hotel);
        return JSON.toJSONString("Data has update: " + hotel);
    }

    @Override
    public String testXA1() {
        PageResult pageResult = callService.hotelList(1, 1);
        String hotelCity = pageResult.getHotels().get(0).getCity();
        System.out.println("getCity " + pageResult.getHotels().get(0).getCity());
        return JSON.toJSONString(pageResult+hotelCity);
    }

    @Override
    public String testXA2() {
        RequestParams params = new RequestParams();
        params.setCity("深圳");
        params.setKey("");
        params.setPage(1);
        params.setSize(5);
        params.setSortBy("default");
        PageResult searchResult = searchService.search(params);
        return JSON.toJSONString(searchResult);
    }
}
