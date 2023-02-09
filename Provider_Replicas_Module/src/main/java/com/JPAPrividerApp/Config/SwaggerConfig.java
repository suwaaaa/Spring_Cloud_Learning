package com.JPAPrividerApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Config
 *
 * @author 12645
 * @createTime 2023/2/8 14:46
 * @description
 */

@Configuration // 配置类
@EnableSwagger2 // 开启 swagger2 的自动配置               http://localhost:9025/swagger-ui.html
public class SwaggerConfig {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket docket() {
        // 创建一个 swagger 的 bean 实例
        return new Docket(DocumentationType.SWAGGER_2).host("localhost:9025")
                // 配置基本信息
                .apiInfo(apiInfo())
                ;
    }

    // 基本信息设置
    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "Javon", // 作者姓名
                "Null", // 作者网址
                "1264584869@qq.com"); // 作者邮箱
        return new ApiInfoBuilder()
                .title("Provider_Replicas_Module-接口文档") // 标题
                .description("This Module don't achieve the file upload interface but build for JPA project for week-3 learning") // 描述
                .termsOfServiceUrl("https://github.com/suwaaaa") // 跳转连接
                .version("1.0") // 版本
                .license("Swagger-")
                .licenseUrl("https://github.com/suwaaaa")
                .contact(contact)
                .build();
    }
}

