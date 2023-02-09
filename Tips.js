/*
* 自动生成测试类的IDEA 插件：
* 收费：Squaretest(30 days for free)，  免费：TestMe
*
* 自定义注解使用：类注释：创建类的时候自动生成 方法注释：使用/**+Tab键
*
* Github 上传失败 原因之一： 没有配置公开密钥到github
*
* 1、18 JPA Learning
* refer:
* https://blog.csdn.net/Yuncoco/article/details/92574137?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522167393555916800192251858%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=167393555916800192251858&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~baidu_landing_v2~default-4-92574137-null-null.142^v71^control,201^v4^add_ask&utm_term=JPQL&spm=1018.2226.3001.4187
* https://blog.csdn.net/suncaishen/article/details/6512028?spm=1001.2101.3001.6650.13&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-13-6512028-blog-103017261.pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-13-6512028-blog-103017261.pc_relevant_aa&utm_relevant_index=14
*https://blog.csdn.net/qq_27886997/article/details/84969207?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-84969207-blog-78780147.pc_relevant_recovery_v2&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-84969207-blog-78780147.pc_relevant_recovery_v2&utm_relevant_index=1
* https://janus.blog.csdn.net/article/details/78780147?spm=1001.2101.3001.6650.14&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-14-78780147-blog-103017261.pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-14-78780147-blog-103017261.pc_relevant_aa&utm_relevant_index=15
* https://blog.csdn.net/qq_41574004/article/details/103017261?spm=1001.2101.3001.6650.14&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-14-103017261-blog-80001826.pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-14-103017261-blog-80001826.pc_relevant_aa&utm_relevant_index=18
* https://blog.csdn.net/dtttyc/article/details/80001826?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522167393555916800192251858%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=167393555916800192251858&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-80001826-null-null.142^v71^control,201^v4^add_ask&utm_term=JPQL&spm=1018.2226.3001.4187
* */

/*JPA Repository
* 1.方法名称命名规则查询
规则：findBy(关键字)+属性名称(属性名称的首字母大写)+查询条件(首字母大写)

关键字	方法名	sql where 子句
And	findByNameAndPwd	where name= ? and pwd =?
Or	findByNameOrSex	where name= ? or sex=?
Is,Equal	findById,findByIdEquals	where id= ?
Between	findByIdBetween	where id between ? and ?
LessThan	findByIdLessThan	where id < ?
LessThanEqual	findByIdLessThanEquals	where id <= ?
GreaterThan	findByIdGreaterThan	where id > ?
GreaterThanEqual	findByIdGreaterThanEquals	where id > = ?
After	findByIdAfter	where id > ?
Before	findByIdBefore	where id < ?
IsNull	findByNameIsNull	where name is null
isNotNull,Not	findByNameNotNull	where name is not Null null
Like	findByNameLike	where name like ?
NotLike	findByNameNotLike	where name not like ?
StartingWith	findByNameStartingWith	where name like ‘?%’
EndingWith	findByNameEndingWith	where name like ‘%?’
Containing	findByNameContaining	where name like ‘%?%’
OrderBy	findByIdOrderByXDesc	where id=? order by x desc
Not	findByNameNot	where name <> ?
In	findByIdIn(Collection<?> c)	where id in (?)
NotIn	findByIdNotIn(Collection<?> c)	where id not in (?)
True	findByAaaTue	where aaa = true
False	findByAaaFalse	where aaa = false
IgnoreCase	findByNameIgnoreCase	where UPPER(name)=UPPER(?)
*/

/*JPS JPQL 写法
* JPQL必须由JPA的JPQL解析器解析为SQL才能执行，必须先获取Query对象

参数查询
1命名参数查询
entityManager.createQuery("select u from User u where u.age=：param");
query.setParameter("param",24);
List<User> result=(List<User>)query.getResultList();
2位置参数查询
"select u from User u where u.age=？1"
query.setParameter(1,24);
 3实体参数(JPQL中除了基本类型外，还可以是对象类型)
"select u from User u where u.person=？1"
Person p=new Person();
p.setId(1);
query.setParameter(1,p);
JPQL运算符
算术运算符  +  - *  /    关系运算符  =  >   <   >=   <=   <>   逻辑运算符   between ,like , in , is null,is empty,member of,not ,and ,or
1 not "select u from User u where not(u.cityid=?1)"
2  between和sql中一样
3  in    在给定的值列表中查询   "select u from User u where u.cityid in(1,2,3)"
4 like       (_表示一个字符，%表示任意数量字符)
5 is null      (实体的属性是否为空，即实体属性映射的字段是否为空)    "select u from User u where u.cityid is null"
6 is empty        (与is null 不同的是，它针对实体的集合属性 内有没有元素。虽然集合对象是存在的，但是集合内没有元素)
select o from Order o where o.orderItems is empty
条件查询
1排序查询       asc升序  desc降序   order by p.age desc,p.birthday asc
2聚合函数查询
avg() 平均值，返回Double     count()返回Long       max()          min()         sum()
以上除了count外其它的函数的参数对应的是 单个的字段，即属性而不是对象
HAVING和WHERE的区别。一个是先分组，再对组进行筛选；一个是先筛选，然后对筛选后的记录进行分组。
构造器查询
"select new  com.jsun.Person(p.name) from Person p"
Person有这个构造器时，即Person(String name)，成功返回对象。
关联查询（实际工作中仍然会有这种需求）
left join 允许右边实体表达式的实体为空，这里的实体指 集合属性
inner join 右边必须存在
"select o from Order o inner join o.orderItems"
在默认查询中,集合属性是懒加载的。只有用的时候JPA才会再发SQL语句    在查询的时候我们希望一次全抓取出来怎么办？用 fetch
(其实left join和inner join在这里使用主要是为了配合fetch，一次抓取，因为默认的是懒加载)
"select o from Order o inner join fetch o.orderItems"
排除相同记录DISTINCT
select distinct o from Order o
(还可以配合聚合函数使用)
如 select  count(distinct o) from Order o
JPQL内置函数
字符串函数
concat(str1,str2)
substring(string,position,length)
trim( [leading | trailing | both]  sub_str  from str)
upper(str)
lower(str)
length(str)
locate(searchString,initPosition)
日期，时间函数
CURRENT_DATE                       //返回当前日期（这是数据库端的时间）
CURRENT_TIME           //返回当前时间
CURRENT_TIMESTAMP       //返回当前日期时间

数学函数
ABS(number)    //绝对值
SQRT(number)  //平方根
MOD（num,div）//取模
SIZE(collection)    //返回集合中元素的数量
如：select o from Order o where size(o.items)>10
使用子查询
xxxx  in(子查询)
xxxx  not in(子查询)
exists（子查询）
not exists（子查询 ）       exists存在的意思 即子查询的结果不为空就是存在
分页查询
用到了query接口的两个方法
setMaxResults(int)                //设置获取多少条记录
和setFirstResult(int)//结果集开始的索引，索引默认从0开始
以上两个方法都返回Query对象所以可以组成方法链
query.setMaxResults(max).setFirstResult(index).getResultList()
批量操作（批量更新和批量删除）
建议将所有批量操作与不连续的事务隔离开，
批量操作可能造成受管实体与数据库的不一致
原生SQL查询与调用存储过程(EJB3.0不支持以OUT参数的形式返回的存储过程）
Query query=entityManager.createNativeQuery(" {call pro_1(?)} ");
query.setParameter(1,"jsun");
//无返回值存储过程
query.executeUpdate();
//返回单个值的存储过程
String result=(String)query.getSingleResult();
//返回全部列的存储过程
List<User> results=(List<User>)query.getResultList();
//返回部分列的存储过程
List<Object[]> results=(List<Object[]>)query.getResultList();
for(Object[] row:results){
row[0],row[1]
}*/

/*
解决端口号(port)被占的问题
Linux:
netstat  -anp  |grep 8000
kill -9  进程ID

Windows:
netstat -ano|findstr 8000
taskkill /pid 7912 /f
*/