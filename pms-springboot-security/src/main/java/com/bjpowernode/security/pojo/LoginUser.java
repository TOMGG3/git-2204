package com.bjpowernode.security.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 自定义登陆用户类
 */
public class LoginUser implements UserDetails {

    private Integer id;
    private String username;
    private String mobile;
    private String nickname;
    private String email;
    private String gender;
    private String avatar;
    private String userpwd;
    private String userType;
    private String status;
    /**
     * 当前登陆用户拥有的角色名称列表
     */
    private List<String> roleNameList;
    /**
     * 当前登陆用户拥有的权限字符串列表
     */
    private List<String> percodeList;

    /**
     * 为当前登陆用户配置权限
     * @return
     */
    @Override
    //jackson中通过@JsonIgnore注解来忽略某个属性的json格式化
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> grantedAuthorityHashSet = new HashSet<>();
        //添加角色
        if(!CollectionUtils.isEmpty(roleNameList)){
            for (String roleName : roleNameList) {
                if(StringUtils.hasLength(roleName)){
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + roleName);
                    grantedAuthorityHashSet.add(simpleGrantedAuthority);
                }
            }
        }
        //添加权限
        if(!CollectionUtils.isEmpty(percodeList)){
            for (String percode : percodeList) {
                if(StringUtils.hasLength(percode)){
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(percode);
                    grantedAuthorityHashSet.add(simpleGrantedAuthority);
                }
            }
        }
        return grantedAuthorityHashSet;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.userpwd;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        //如果业务中存在账号过期，可在此方法中实现
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return "1".equals(this.status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<String> roleNameList) {
        this.roleNameList = roleNameList;
    }

    public List<String> getPercodeList() {
        return percodeList;
    }

    public void setPercodeList(List<String> percodeList) {
        this.percodeList = percodeList;
    }
}
