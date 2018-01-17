package com.example.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by kaixu on 2018/1/17.
 * 蓝球中红球分布情况
 */
@Entity
@Table(name = "double_ball_blue_red_relations")
public class RedBlueCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String blue;
    private String red;
    private Integer count;
    @Column(name = "create_time")
    private Timestamp createTime;

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
