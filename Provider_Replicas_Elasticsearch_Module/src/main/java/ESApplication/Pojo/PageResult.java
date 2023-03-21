package ESApplication.Pojo;

import lombok.Data;

import java.util.List;

/**
 * Java_IBM_Learning ESApplication.pojo
 *
 * @author 12645
 * @createTime 2023/3/20 15:27
 * @description
 */

@Data
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;

    public PageResult() {
    }

    public PageResult(Long total, List<HotelDoc> hotels) {
        this.total = total;
        this.hotels = hotels;
    }
}
