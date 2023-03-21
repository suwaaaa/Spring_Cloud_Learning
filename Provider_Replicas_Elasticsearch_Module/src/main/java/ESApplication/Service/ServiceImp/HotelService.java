package ESApplication.Service.ServiceImp;

import ESApplication.Mapper.HotelMapper;
import ESApplication.Pojo.Hotel;
import ESApplication.Pojo.HotelDoc;
import ESApplication.Pojo.PageResult;
import ESApplication.Pojo.RequestParams;
import ESApplication.Service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java_IBM_Learning ESApplication.Service.ServiceImp
 *
 * @author 12645
 * @createTime 2023/3/20 15:37
 * @description
 */

@Slf4j
@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public HotelService(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public PageResult search(RequestParams params) {
        try {
            // 1.准备Request
            SearchRequest request = new SearchRequest("hotel");
            // 2.准备请求参数
            // 2.1.query
            buildBasicQuery(params, request);
            // 2.2.分页
            int page = params.getPage();
            int size = params.getSize();
            request.source().from((page - 1) * size).size(size);
            // 2.3.距离排序
            String location = params.getLocation();
            if (StringUtils.isNotBlank(location)) {
                request.source().sort(SortBuilders
                        .geoDistanceSort("location", new GeoPoint(location))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS)
                );
            }
            // 3.发送请求
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            // 4.解析响应
            return handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException("搜索数据失败", e);
        }
    }

    private void buildBasicQuery(RequestParams params, SearchRequest request) {
        // 1.准备Boolean查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 1.1.关键字搜索，match查询，放到must中
        String key = params.getKey();
        if (StringUtils.isNotBlank(key)) {
            // 不为空，根据关键字查询
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        } else {
            // 为空，查询所有
            boolQuery.must(QueryBuilders.matchAllQuery());
        }

        // 1.2.品牌
        String brand = params.getBrand();
        if (StringUtils.isNotBlank(brand)) {
            boolQuery.filter(QueryBuilders.termQuery("brand", brand));
        }
        // 1.3.城市
        String city = params.getCity();
        if (StringUtils.isNotBlank(city)) {
            boolQuery.filter(QueryBuilders.termQuery("city", city));
        }
        // 1.4.星级
        String starName = params.getStarName();
        if (StringUtils.isNotBlank(starName)) {
            boolQuery.filter(QueryBuilders.termQuery("starName", starName));
        }
        // 1.5.价格范围
        Integer minPrice = params.getMinPrice();
        Integer maxPrice = params.getMaxPrice();
        if (minPrice != null && maxPrice != null) {
            maxPrice = maxPrice == 0 ? Integer.MAX_VALUE : maxPrice;
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        }

        // 2.算分函数查询
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(
                boolQuery, // 原始查询，boolQuery
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{ // function数组
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                QueryBuilders.termQuery("isAD", true), // 过滤条件
                                ScoreFunctionBuilders.weightFactorFunction(10) // 算分函数
                        )
                }
        );

        // 3.设置查询条件
        request.source().query(functionScoreQuery);
    }

    private PageResult handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        // 4.1.总条数
        long total = searchHits.getTotalHits().value;
        // 4.2.获取文档数组
        SearchHit[] hits = searchHits.getHits();
        // 4.3.遍历
        List<HotelDoc> hotels = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            // 4.4.获取source
            String json = hit.getSourceAsString();
            // 4.5.反序列化，非高亮的
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            // 4.6.处理高亮结果
            // 1)获取高亮map
            Map<String, HighlightField> map = hit.getHighlightFields();
            if (map != null && !map.isEmpty()) {
                // 2）根据字段名，获取高亮结果
                HighlightField highlightField = map.get("name");
                if (highlightField != null) {
                    // 3）获取高亮结果字符串数组中的第1个元素
                    String hName = highlightField.getFragments()[0].toString();
                    // 4）把高亮结果放到HotelDoc中
                    hotelDoc.setName(hName);
                }
            }
            // 4.8.排序信息
            Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                hotelDoc.setDistance(sortValues[0]);
            }

            // 4.9.放入集合
            hotels.add(hotelDoc);
        }
        return new PageResult(total, hotels);
    }


    /*
        Javon add 2023-03-21
        聚合 索引库特定字段 集合 结果集， 作为搜索条件
    */
    @Override
    public Map<String, List<String>> filer(List<String> termsNames) throws IOException {
        //Request
        SearchRequest request = new SearchRequest("hotel");
        //size result
        request.source().size(0);
        //aggregation result
            //need result aggregation terms' names
        for (String name : termsNames) {
            takeAggregation(request,name);
        }
        //get result
        Map<String, List<String>> resultMap = new HashMap<>();
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        Aggregations responseAggregations = response.getAggregations();
        //get brand/city/star list
        for (String termsName : termsNames) {
            resultMap.put(termsName, getTermResults(responseAggregations,termsName));
        }
        //return result
        System.out.println(JSON.toJSONString(resultMap));
        return resultMap;
    }

    private void takeAggregation(SearchRequest request, String termName) {
        request.source().aggregation(AggregationBuilders
                .terms(termName+"Agg")//自定义聚合数据集的名称
                .field(termName)//对brand的字段 聚合
                .size(100));//聚合的数据量
    }

    private List<String> getTermResults(Aggregations responseAggregations, String termName) {
        Terms terms = responseAggregations.get(termName+"Agg");//自定义聚合数据集的名称
        List<String> bucketResultList = new ArrayList<>();
        for (Terms.Bucket bucket : terms.getBuckets()) {
            bucketResultList.add(bucket.getKeyAsString());
        }
        return bucketResultList;
    }

    public List<String> autoCompleteSearchCriteria(String searchCriteria) throws IOException {
        //create search request
        SearchRequest searchRequest = new SearchRequest("hotel");
        //request size/search criteria/skipDuplicates
        searchRequest.source().suggest(new SuggestBuilder().addSuggestion(
                                                            searchCriteria + "_suggestion",
                                                            SuggestBuilders.completionSuggestion("suggestion")
                                                                                .prefix(searchCriteria)
                                                                                .skipDuplicates(true)
                                                                                .size(10)));
        //send request and get response
        SearchResponse response = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        // get response results
        //Suggest.Suggestion<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>>
        CompletionSuggestion suggestion = response.getSuggest().getSuggestion(searchCriteria + "_suggestion");
        List<CompletionSuggestion.Entry.Option> options = suggestion.getOptions();
        List<String> resultList = new ArrayList<>(options.size());
        for (CompletionSuggestion.Entry.Option option : options) {
            resultList.add(option.getText().toString());
        }
        return resultList;
    }


    /*
    * Javon add    Sync elasticsearch data from MQ Queue sent by MYSQL [Publish_Module]
    * */
    public void insertDataById(Long dataId){
        try {
            System.out.println("start to complete data sync");
            //MySQL 查到数据，并且转换为HotelDoc
            HotelDoc hotelDoc = new HotelDoc(getById(dataId));
            //request
            IndexRequest request = new IndexRequest("hotel").id(dataId.toString());
            request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
            //send request
            restHighLevelClient.index(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("Hotel Data change fail !!! >>> ", e);
        }
    }


    public void deleteDataById(Long dataId){
        try {
            DeleteRequest deleteRequest = new DeleteRequest("hotel", dataId.toString());
            restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
