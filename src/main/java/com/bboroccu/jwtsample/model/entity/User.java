package com.bboroccu.jwtsample.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="USERS")
public class User {
    @Id @Column(name="SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seq;

    @Column(name="USERID")
    private String userId;

    @Column(name="PASSWD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    protected Role role;


    private String email;
    private String useYn;
    private Integer isFirst;
    private Date regTime;
    private Date loginTime;
    private String initToken;

    public User() { }

    public User(Integer seq, String userId, String password, Role role) {
        this.seq = seq;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public Integer getSeq() {
        return seq;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "USEYN")
    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    @Basic
    @Column(name = "ISFIRST")
    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    @Basic
    @Column(name = "REGTIME")
    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Basic
    @Column(name = "LOGINTIME")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column(name = "INITTOKEN")
    public String getInitToken() {
        return initToken;
    }

    public void setInitToken(String initToken) {
        this.initToken = initToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(seq, user.seq) &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(email, user.email) &&
                Objects.equals(useYn, user.useYn) &&
                Objects.equals(isFirst, user.isFirst) &&
                Objects.equals(regTime, user.regTime) &&
                Objects.equals(loginTime, user.loginTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(seq, userId, password, role, email, useYn, isFirst, regTime, loginTime);
    }
}
