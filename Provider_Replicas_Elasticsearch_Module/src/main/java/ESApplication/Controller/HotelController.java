package ESApplication.Controller;

import ESApplication.Pojo.PageResult;
import ESApplication.Pojo.RequestParams;
import ESApplication.Service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Java_IBM_Learning ESApplication.Controller
 *
 * @author 12645
 * @createTime 2023/3/20 15:40
 * @description
 */

@RestController
@RequestMapping("hotel")
public class HotelController {

    private final IHotelService hotelService;

    @Autowired
    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("list")
    public PageResult search(@RequestBody RequestParams params) {
        return hotelService.search(params);
    }



    @GetMapping("filter")//聚合索引，根据terms方式获取 三个字段的聚合分类
    public Map<String, List<String>> filer() throws IOException {
        List<String> needTermNamesList = new ArrayList<>();
        needTermNamesList.add("brand");
        needTermNamesList.add("starName");
        needTermNamesList.add("city");
        return hotelService.filer(needTermNamesList);
    }

    @GetMapping("suggestion")
    public List<String> autoCompleteSearchCriteria(@RequestParam("key") String searchCriteria) throws IOException {
        return hotelService.autoCompleteSearchCriteria(searchCriteria);
    }
}
