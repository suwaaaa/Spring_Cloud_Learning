package Publish.web;

import Publish.constants.HotelMqConstants;
import Publish.pojo.Hotel;
import Publish.pojo.PageResult;
import Publish.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;

@RestController
@RequestMapping("hotel")
public class HotelController {

    private final IHotelService hotelService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public HotelController(IHotelService hotelService, RabbitTemplate rabbitTemplate) {
        this.hotelService = hotelService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id){
        return hotelService.getById(id);
    }

    @GetMapping("/list")
    public PageResult hotelList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "1") Integer size
    ){
        Page<Hotel> result = hotelService.page(new Page<>(page, size));

        return new PageResult(result.getTotal(), result.getRecords());
    }

    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel){
        // 新增酒店
        hotelService.save(hotel);
        // 发送MQ消息
        rabbitTemplate.convertAndSend(HotelMqConstants.EXCHANGE_NAME, HotelMqConstants.INSERT_KEY, hotel.getId());
    }

    @PutMapping()
    public void updateById(@RequestBody Hotel hotel){
        if (hotel.getId() == null) {
            throw new InvalidParameterException("id不能为空");
        }
        hotelService.updateById(hotel);
        System.out.println("Publish server update hotel data > " + hotel.toString() + "\n Id = " + hotel.getId());

        // 发送MQ消息
        rabbitTemplate.convertAndSend(HotelMqConstants.EXCHANGE_NAME, HotelMqConstants.INSERT_KEY, hotel.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        hotelService.removeById(id);

        // 发送MQ消息
        rabbitTemplate.convertAndSend(HotelMqConstants.EXCHANGE_NAME, HotelMqConstants.DELETE_KEY, id);
    }


    @GetMapping("/onlySearch")
    public Hotel searchXA(){
        return hotelService.list().get(0);
    }

    @PostMapping("/postSearch")
    public String postSearchXA (@RequestBody Hotel hotel){
        return JSON.toJSONString(hotelService.saveOrUpdate(hotel));
    }
}
