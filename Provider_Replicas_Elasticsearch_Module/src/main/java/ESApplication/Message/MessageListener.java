package ESApplication.Message;

import ESApplication.Constants.HotelMqConstants;
import ESApplication.Pojo.Hotel;
import ESApplication.Service.IHotelService;
import org.elasticsearch.common.inject.BindingAnnotation;
import org.elasticsearch.common.inject.internal.BindingBuilder;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Java_IBM_Learning ESApplication.Message
 *
 * @author 12645
 * @createTime 2023/3/21 22:37
 * @description
 */
@Component
public class MessageListener {

    private final IHotelService hotelService;

    @Autowired
    public MessageListener(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = HotelMqConstants.EXCHANGE_NAME,type = ExchangeTypes.TOPIC),
            value = @Queue(name = HotelMqConstants.INSERT_QUEUE_NAME),
            key = HotelMqConstants.INSERT_KEY))
    public void syncInsertDataFromMySQL(Long dataId){
        System.out.println(dataId.toString() + " need Insert Queue");
        hotelService.insertDataById(dataId);

    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = HotelMqConstants.EXCHANGE_NAME,type = ExchangeTypes.TOPIC),
            value = @Queue(name = HotelMqConstants.DELETE_QUEUE_NAME),
            key = HotelMqConstants.DELETE_KEY))
    public void syncDeleteDataFromMySQL(Long dataId){
        System.out.println(dataId + " need Delete Queue");
        hotelService.deleteDataById(dataId);
    }
}
