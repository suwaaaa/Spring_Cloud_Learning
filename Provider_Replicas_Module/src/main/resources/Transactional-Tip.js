// Transactional

/*@Transactional事务
操作数据库过程中可能发生异常，导致后续操作无法完成。此时需要进行回滚。
Spring默认会对没有被捕获（unchecked）的RunTimeException进行事务回滚，如果异常已经被catch也即是遇到的事checked异常的话则不回滚。
改变默认规则的话：
让原本不能回滚的checked回滚：在方法前加@Transaction(rollbackFor=Exception.class/rollbackForClassName=Exception)
让原本会回滚的unchecked不回滚：@Transaction(notRollbackFor=RunTimeException.class/notRollbackForClassName=Exception)
PS：如果原本能回滚的异常被try/catch了还想让它回滚，那就必须再抛出一个异常。
但实践证明，在测试中使用事务，无论是否出现异常，都会自动回滚，数据库会保持和测试前一致。
jpa那些默认的接口都没有默认开启事务的，只是支持事务。
要开启事务的话，可以在service类前加@Transactional，声明这个service的所有方法都需要事务管理。
或者是在测试类的测试方法前加@Transactional，声明这个方法里调用到的操作数据库的方法需要事务管理。加了的话测试类无论抛异常与否都会自动回滚，防止数据库污染。
如果加了该注解后，测试类没有自动回滚，可查看数据库引擎是否为Innodb，因为其他数据库引擎不支持事务。
也可以在dao层的Repository接口里创建操作数据库的方法前加上@Transactional，声明此方法开启事务管理（不过这种情况应该是很少的，因为查询方法一般都是单操作，没什么意义）。
该注解只能加在public方法上，加在其他方法上无效
*/

/*方法B定义的事务类型	                                           A方法有事务时	                          A方法无事务
 @Transactional(propagation = Propagation.REQUIRED)    	B和A事务合并成一个事务	                      B新建一个事务
 @Transactional(propagation = Propagation.REQUIRES_NEW)  	B新建一个事务，和A事务无关，互不影响	          B新建一个事务
 @Transactional(propagation = Propagation.NESTED)  	    B新建一个A的子事务，A异常影响B，B异常不影响A	  B新建一个事务
 @Transactional(propagation = Propagation.SUPPORTS)  	    B加入到A事务中	                          B无事务
 @Transactional(propagation = Propagation.NOT_SUPPORTED)  	挂起A事务，B以无事务方式执行	              B无事务
 @Transactional(propagation = Propagation.MANDATORY)  	    B加入到A事务中	                          B抛异常
 @Transactional(propagation = Propagation.NEVER)  	        B抛异常	                                  B无事务
 */