package com.JPAPrividerApp.Repository;

import com.JPAPrividerApp.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface UserJpaRepository extends JpaRepository<User,Long> { //JpaRepository 有内部方法实现，另外的可以自己添加

    //by method name
    Page<User> findAllByEmailEndingWith(@Param(value = "emailPostFix") String emailPostFix, Pageable pageable);//当获取分页结果需要在入参加入Pageable ，必须
    List<User> queryUsersByAgeBetween(@Param(value = "minAge") Integer minAge, @Param(value = "maxAge") Integer maxAge);
    List<User> countUserByNameContains(@Param(value = "name") String name);

    //通过 JPQL 语句 by JPQL
    //使用@Query 注解查询         jpql 支持动态传参数，?1  或者:name 这种，同时只能使用一种
    @Query(value = "select u from User u where u.name like concat('%',:name,'%')")// !!! 这里JPQL 里面的 "User" 是实体类，不是表名
    //nullif 在 expr1 的值不为 NULL的情况下都返回 expr1，否则返回 expr2
    List<User> queryUserThroughJPQLByUserName(@Param(value = "name") String name);


    @Query(value = "update User u set u.email = :email where u.id = :userId ")
    @Modifying //删除更新操作 要加@Modifying
    Integer updateUserEmailById(@Param(value = "email") String email, @Param(value = "userId") Long userId);


    @Query(value = "delete from User u where u.name = :name ")
    @Modifying //删除更新操作 要加@Modifying
    Integer deleteUserByName(@Param(value = "name") String name);


    //通过SQL语句 by native SQL
    @Query(value = "SELECT * from user u where u.name like concat('%',:userName,'%')", nativeQuery = true)
    Page<User> queryUsersBySQLNameContain(@Param(value = "userName") String userName, Pageable pageable);
}
