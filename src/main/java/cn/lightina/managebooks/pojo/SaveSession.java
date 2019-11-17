package cn.lightina.managebooks.pojo;

import lombok.Data;
import java.util.Date;
@Data
public class SaveSession {
    private Integer id;
    private String name;
    private String token;
    private Date time;
    private String avatar_url;
}
