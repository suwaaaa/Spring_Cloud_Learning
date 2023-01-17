package JPAPrividerApp.Controller;

import JPAPrividerApp.Entity.User;
import JPAPrividerApp.Repository.UserJpaRepository;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/provider02")
@Transactional
public class UserController {

    private final UserJpaRepository userJpaRepository;
    @Autowired
    public UserController(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("/")
    public String helloProvider02(){
        return "Hello, This is Spring-Cloud-Eureka-Provider-02";
    }

    /*JpaRepository 有内部方法实现，另外的可以自己添加   by method name*/
    @GetMapping("/findAUser/{userId}")
    public Optional<User> findAllUser(@PathVariable Long userId){
        return userJpaRepository.findById(userId);
    }
    @GetMapping("/findAllByEmailEndingWith")
    public Page<User> findAllByEmailEndingWith(@Param(value = "emailPostFix") String emailPostFix, Pageable pageable){
        return userJpaRepository.findAllByEmailEndingWith(emailPostFix, pageable);
    }
    @GetMapping("/queryUsersByAgeBetween")
    public List<User> queryUsersByAgeBetween(@RequestParam("minAge") Integer minAge, @RequestParam("maxAge") Integer maxAge){
        return userJpaRepository.queryUsersByAgeBetween(minAge,maxAge);
    }
    @GetMapping("/countUserByNameContains")
    public List<User> countUserByNameContains(@Param(value = "name") String name){
        return userJpaRepository.countUserByNameContains(name);
    }



    /*通过 JPQL 语句 by JPQL   使用@Query 注解查询*/
    @GetMapping("/queryUserThroughJPQLByUserName")
    public List<User> queryUserThroughJPQLByUserName(@Param(value = "name") String name){
        return userJpaRepository.queryUserThroughJPQLByUserName(name);
    }

    @PostMapping("/updateUserEmailById")
//    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    public Integer updateUserEmailById(@Param(value = "email") String email, @Param(value = "userId") Long userId){
        System.out.println("Provider02 changed One User as: User's id-" + userId + "'s mailbox change into " + email);
        return userJpaRepository.updateUserEmailById(email,userId);
    }

    @DeleteMapping("/deleteUserByName")
    public Integer deleteUserByName(@Param(value = "name") String name){
        return userJpaRepository.deleteUserByName(name);
    }

    /*
    * 通过SQL语句 by native SQL*/
    @GetMapping("/queryUsersBySQLNameContain")
    public Page<User> queryUsersBySQLNameContain(@Param(value = "userName") String userName, Pageable pageable){
        return userJpaRepository.queryUsersBySQLNameContain(userName, pageable);
    }
}
