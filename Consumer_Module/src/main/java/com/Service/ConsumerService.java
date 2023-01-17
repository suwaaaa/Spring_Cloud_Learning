package com.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public interface ConsumerService {

 @GetMapping("/")
 String welcome();

 @RequestMapping("/consume/hello")
 String hellString();

 @RequestMapping("/web/hystrix")
 String hystrix();
}
