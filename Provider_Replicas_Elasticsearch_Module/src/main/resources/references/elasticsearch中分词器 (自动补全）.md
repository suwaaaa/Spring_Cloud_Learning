## elasticsearch中分词器 (analyzer）

组成包含三部分：

- character filters：在tokenizer之前对文本进行处理。例如删除宇符、替换字符
- tokenizer： 將文本按照一定的规则切制成词条（term）。例如keyword，就不分词；还有ik_smart
- tokenizer filter：特tokenizer输出的词条做进一步处理。例如大小号转换、同义词处理、拼音处理等

```json
// 自定义拼音分词器
PUT /test
{
  "settings": {
    "analysis": {
      "analyzer": { 
        "my_analyzer": { 
          "tokenizer": "ik_max_word",
          "filter": "py"
        }
      },
      "filter": {
        "py": { 
          "type": "pinyin",
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_original": true,
          "limit_first_letter_length": 16,
          "remove_duplicated_term": true,
          "none_chinese_pinyin_tokenize": false
        }
      }
    }
  },
  "mappings": {//修改字段名字为‘name’的分词器可以用自定义my_analyzer
    "properties": {
      "name":{
        "type": "text",
        "analyzer": "my_analyzer",//拼音分词器适合在创建倒排索引的时候使用
        "search_analyzer": "ik_smart"//但是不能在搜索的时候使用
      }
    }
  }
}
POST test/_analyze//使用 test/_analyze 来查询分词器效果
{
  "text": ["上海酒店"],
  "analyzer": "my_analyzer"
}
DELETE /test   //删除刚刚的配置
```

elasticsearch提供了 Completion Suggester查询来实现自动补全功能。

这个查询会匹配以用户输入内容开头的词条并返回。为了提高补全查询的效率，对于文档中字段的类型有一些约束：

- 参与补全查询的字段必须是completion类型
- 字段的内容一般用来补全多个词条形成的数组

```json
// 自动补全的索引库
PUT test2//给 test2索引库 设置 字段类型
{
  "mappings": {
    "properties": {
      "title":{//title字段类型为completion
        "type": "completion"
      }
    }
  }
}
// 示例数据 补充title字段 提示的信息数组
POST test2/_doc
{
  "title": ["Sony", "WH-1000XM3"]
}
POST test2/_doc
{
  "title": ["SK-II", "PITERA"]
}
POST test2/_doc
{
  "title": ["Nintendo", "switch"]
}

GET /test2/_search  //查询模板
{
  "suggest": {
    "YOUR_SUGGESTION": {
      "text": "YOUR TEXT",
      "term": {
        "FIELD": "MESSAGE"
      }
    }
  }
}
//示例
POST /test2/_search
{
  "suggest": {
    "title_suggest": {
      "text": "s", 
      "completion": {
        "field": "title", 
        "skip_duplicates": true, 
        "size": 10 
      }
    }
  }
}
```

