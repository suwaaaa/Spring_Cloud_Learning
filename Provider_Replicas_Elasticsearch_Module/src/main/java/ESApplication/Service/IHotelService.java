package ESApplication.Service;

import ESApplication.Pojo.Hotel;
import ESApplication.Pojo.PageResult;
import ESApplication.Pojo.RequestParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);

    Map<String, List<String>> filer(List<String> termsNames) throws IOException;

    List<String> autoCompleteSearchCriteria(String searchCriteria) throws IOException;

    void insertDataById(Long dataId);

    void deleteDataById(Long dataId);
}
