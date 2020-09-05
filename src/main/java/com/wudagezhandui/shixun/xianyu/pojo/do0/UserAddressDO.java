package com.wudagezhandui.shixun.xianyu.pojo.do0;

import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPut;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public class UserAddressDO {

    @Null(message = "INVALID_PARAMETER: The id must be null.",
            groups = {GroupPost.class})
    @Id(groups = {Group.class})
    private Integer id;

    @NotNull(message = "INVALID_PARAMETER_IS_NULL: The userId must be not null.",
            groups = {GroupPut.class})
    @Id(groups = {Group.class})
    private Integer userId;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The address must be not blank.",
            groups = {GroupPost.class})
    @Size(message = "INVALID_PARAMETER_SIZE: The size of address must be between 1 to 255.",
            min = 1, max = 255,
            groups = {Group.class})
    private String address;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The fullname must be not blank.",
            groups = {GroupPost.class})
    @Size(message = "INVALID_PARAMETER_SIZE: The size of fullname must be between 1 to 20.",
            min = 1, max = 20,
            groups = {Group.class})
    private String fullname;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The phone must be not blank.",
            groups = {GroupPost.class})
    @Size(message = "INVALID_PARAMETER_SIZE: The size of phone must be between 1 to 11.",
            min = 1, max = 11,
            groups = {Group.class})
    private String phone;

    @Null(message = "INVALID_PARAMETER: The createTime must be null.",
            groups = {GroupPost.class, GroupPut.class})
    private Date createTime;

    @Null(message = "INVALID_PARAMETER: The createTime must be null.",
            groups = {GroupPost.class, GroupPut.class})
    private Date updateTime;

    public UserAddressDO() {
    }

    public UserAddressDO(Integer id, Integer userId, String address, String fullname, String phone,
                        Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.fullname = fullname;
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserAddressDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
