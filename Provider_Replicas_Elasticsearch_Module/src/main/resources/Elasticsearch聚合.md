## Elasticsearch聚合

聚合可以实现对文档数据的统计，分析，运算。

*参与聚合的类型必须是keyword，数值，日期，布尔*

#### 桶（Bucket）聚合：用来对文档做分组

Term Aggregation：按照文档字段值分组

Data Aggregation：按照日期阶梯分组，例如一周为一组，或者一月为一组

#### 度量（Metric）聚合：用于计算一些值，比如 最大值，最小值，平均值等

Avg：平均值  

Max：最大值

Min：最小值

Stats：同时求max，min，avg，sum等

#### 管道（Pipeline）聚合：其他聚合的结果为基础做聚合



DSL 实现 Bucket 聚合的示例：

```json
GET /索引库名称/_search
{
  "size": 0, //设置为0时，结果无文档，只有聚合结果
  "aggs": {
    "NAME自定义名字": {//name为聚合结果，任意自定义名字-1
      "terms": {//聚合的类型，自选，这里选择的为term类型-2
        "field": "字段名",//需要聚合的字段名-3
        "size": 10,//希望聚合结果的数量
        "order": { //可以添加 对结果集的排序
          "_count": "asc" // 按照[数量][升序]
        }
      }
    }
  }
}
//agg 代表聚合，于query同级，也可以先使用query对聚合限定文档范围
//比如统计结果集中某字段的出现次数，返回统计结果
```

DSL 实现Metric聚合的示例：

```json
GET /索引库名称/_search
{
  "size": 0,
  "aggs": {//第一层聚合，同上为term聚合
    "NAME1": {
      "terms": {
        "field": "字段名1",
        "size": 10,
        "order": {
          "NAME2.avg": "desc" //第一层结果中，对于NAME2里计算出的avg 倒叙排序
        }
      },
      "aggs": {//嵌套聚合，第二层聚合，为stats聚合
        "NAME2": {
          "stats": {// stats会计算字段名2的avg，count，max，min，sum的各个结果
            "field": "字段名2"
          }
        }
      }
    }
  }
}//返回结果里面，聚合结果集‘NAME1’内部多了‘NAME2’的聚合结果
```





聚合的使用场景是，在使用搜索的时候，使用的条件信息并不是自己写死的，而是通过对索引库聚合运算后的数据得到的





Java 实现示例：

```java
@Test
    void testTermAggregation () throws IOException {
        SearchRequest request = 
            new SearchRequest("hotel");// 新建search 请求，访问hotel索引库
        request.source().size(0); //设置不返回文档信息，只返回聚合数据集
        request.source().aggregation(AggregationBuilders
                                .terms("brandAgg")//自定义聚合数据集的名称
                                .field("brand")//对brand的字段 聚合
                                .size(10));//聚合的数据量
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        handleResponse(response);//handleResponse打印返回值
        Terms brandAggTerms = response.getAggregations().get("brandAgg");//得到term 方式返回的聚合数据
        List<? extends Terms.Bucket> buckets = brandAggTerms.getBuckets();//得到所有的Bucket 数据
        for (Terms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString());//打印Bucket里的key信息
        }
```

