package com.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "web.file")
@PropertySource({"classpath:application.properties"})
@Data
public class FileProperty {

    private Integer limitSize;

    private String path;

    private List<String> allowTypes;
}
