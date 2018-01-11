package com.example.domain;

import javax.persistence.*;

/**
 * Created by kaixu on 2018/1/11.
 * 双色球实体类
 */
@Entity
@Table(name = "double_ball")
public class DoubleBall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String detailsLink;
    private String videoLink;
    private String date;
    private String week;
    private String red;
    private String blue;
    private String blue2;
    /**本期销售*/
    private Long sales;
    /**奖池金额*/
    private Long poolmoney;
    private String content;
    private String addmoney;
    private String addmoney2;
    private String msg;
    private String z2add;
    private String m2add;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getBlue2() {
        return blue2;
    }

    public void setBlue2(String blue2) {
        this.blue2 = blue2;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getPoolmoney() {
        return poolmoney;
    }

    public void setPoolmoney(Long poolmoney) {
        this.poolmoney = poolmoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddmoney() {
        return addmoney;
    }

    public void setAddmoney(String addmoney) {
        this.addmoney = addmoney;
    }

    public String getAddmoney2() {
        return addmoney2;
    }

    public void setAddmoney2(String addmoney2) {
        this.addmoney2 = addmoney2;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getZ2add() {
        return z2add;
    }

    public void setZ2add(String z2add) {
        this.z2add = z2add;
    }

    public String getM2add() {
        return m2add;
    }

    public void setM2add(String m2add) {
        this.m2add = m2add;
    }
}
