package Feign.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Java_IBM_Learning MailProvider03Application.Entity
 *
 * @author 12645
 * @createTime 2023/2/3 18:34
 * @description
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Email implements Serializable {
    @JsonProperty(value = "mailTo")
    private String[] mailTo; //收件人

    @JsonProperty(value = "subject")
    private String subject;  //邮件主题

    @JsonProperty(value = "content")
    private String content;  //邮件内容

    @JsonProperty(value = "mailCc")
    private String[] mailCc; //邮件抄送人
}
