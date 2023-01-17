// resources

/*
mock：构建一个我们需要的对象；可以mock具体的对象，也可以mock接口。
spy：构建监控对象
verify：验证某种行为
when：当执行什么操作的时候，一般配合thenXXX 一起使用。表示执行了一个操作之后产生什么效果。
doReturn：返回什么结果
doThrow：抛出一个指定异常
doAnswer：做一个什么相应，需要我们自定义Answer；
times：某个操作执行了多少次
atLeastOnce：某个操作至少执行一次
atLeast：某个操作至少执行指定次数
atMost：某个操作至多执行指定次数
atMostOnce：某个操作至多执行一次
doNothing：不做任何处理
doReturn：返回一个结果
doThrow：抛出一个指定异常
doAnswer：指定一个操作，传入Answer
doCallRealMethod：返回真实业务执行的结果，只能用于监控对象*/

/*  ArgumentMatchers  用于进行参数匹配，减少很多不必要的代码
anyInt：任何int类型的参数，类似的还有anyLong/anyByte等等。
eq：等于某个值的时候，如果是对象类型的，则看toString方法
isA：匹配某种类型
matches：使用正则表达式进行匹配
*/

/*OngoingStubbing  用于返回操作的结果。

thenReturn：指定一个返回的值
thenThrow：抛出一个指定异常
then：指定一个操作，需要传入自定义Answer；
thenCallRealMethod：返回真实业务执行的结果，只能用于监控对象
*/