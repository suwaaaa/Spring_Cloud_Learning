package Publish.service.impl;

import Publish.mapper.HotelMapper;
import Publish.pojo.Hotel;
import Publish.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
