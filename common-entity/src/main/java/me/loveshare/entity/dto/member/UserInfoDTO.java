package me.loveshare.entity.dto.member;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tony on 2017/8/1.
 */
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = -7192266111744705905L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String compellation;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Byte age;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 生肖
     */
    private String zodiac;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 出生地
     */
    private Integer birthplace;
    /**
     * 教育程度
     */
    private Byte eduDegree;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 自我介绍
     */
    private String aboutMe;
}
