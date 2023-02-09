package com.JPAPrividerApp.Repository.UserJpaManager;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/*
//执行创建JPQL / SQL
Query createNamedQuery(String name)        创建查询的名称来创建一个命名查询，使用sql和jpql都可以
Query createNativeQuery（String SQLString） 根据的原生的sql语句查询
Query createQuery（String jpqlString）      根据指定的JPQL语句创建一个查询
//处理结果
List getResultList()                       执行JPQL的select语句，并且返回的是list集合
Object getSingleResult()                   执行返回的那个结果的select语句
int executeUpdate()                        表示执行批量的删除和更新
Query setFirstResult(int startPosition)    设置查询结果从第几条记录开始
Query setMaxResults(int maxResult)         表示设置查询最多返回几条语句
*/
@Component
public class UserEntityManagerRepository {

    @PersistenceContext // 管理实体类
    protected EntityManager entityManager;

    public List queryUserByNameContainMethod(@Param(value = "userName") String userName, @Param(value = "returnCount") Integer returnCount){
        StringBuilder jPQL = new StringBuilder();
        jPQL.append("from User u ");
        jPQL.append("where u.name like concat('%',? 1,'%')");
        System.out.println("queryUserByNameContainMethod Result only return " + returnCount + " match results !");
        return entityManager.createQuery(jPQL.toString()).setParameter(1,userName).setMaxResults(returnCount).getResultList();
    }

    public Object queryUserByEmailEqualAscByAgeMethod(@Param(value = "mail") String mail){
        StringBuilder jpql = new StringBuilder();
        jpql.append("select u.name, u.email from user u ");
        jpql.append("where u.email = ? 1 order by u.age asc");
        return entityManager.createNativeQuery(jpql.toString()).setParameter(1,mail).getSingleResult();
    }

}
