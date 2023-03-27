package Consume.Pojo;

import lombok.Data;

/**
 * Java_IBM_Learning ESApplication.pojo
 *
 * @author 12645
 * @createTime 2023/3/20 15:28
 * @description
 */

@Data
public class RequestParams {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String brand;
    private String city;
    private String starName;
    private Integer minPrice;
    private Integer maxPrice;
    private String location;
}
