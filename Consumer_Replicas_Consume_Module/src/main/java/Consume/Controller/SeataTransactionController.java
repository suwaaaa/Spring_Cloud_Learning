package Consume.Controller;

import Consume.Service.ServiceImp.XAServiceImp;
import Consume.Service.XAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Java_IBM_Learning Consume.Controller
 *
 * @author 12645
 * @createTime 2023/3/25 22:58
 * @description
 */
@RestController
public class SeataTransactionController {

    private final XAServiceImp xaServiceImp;

    @Autowired
    public SeataTransactionController(XAServiceImp xaServiceImp) {
        this.xaServiceImp = xaServiceImp;
    }


    @GetMapping(value = "/test")
    public String testSeataTransactionXA(){
        return xaServiceImp.testSeataTransactionXA();
    }

    @GetMapping(value = "/test1")
    public String testXA1(){
        return xaServiceImp.testXA1();
    }

    @GetMapping(value = "/test2")
    public String testXA2(){
        return xaServiceImp.testXA2();
    }
}
