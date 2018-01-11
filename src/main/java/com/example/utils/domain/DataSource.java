package com.example.utils.domain;

import java.io.Serializable;

/***
 * @Description: 数据源
 * @Title: DataSource.java
 */
public class DataSource extends BasePage implements Serializable {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 6760842545525683094L;
    /***  */
    private Long createTime;
    /***  */
    private String dbName;
    /***  */
    private String dbType;
    /***  */
    private String dsName;
    /***  */
    private String host;
    /***  */
    private Integer id;
    /***  */
    private Integer isAlarm;

    private String alarmContent;
    /***  */
    private Integer isMonitor;
    /***  */
    private String password;
    /***  */
    private String port;
    /***  */
    private String remark;
    /***  */
    private Integer status;

    /***  */
    private Long updateTime;
    /***  */
    private String username;

    private String driverName;

    private String url;

    private String tableName;

    private String businessSystem;
    /**
     * @return the createTime
     */
    public Long getCreateTime() {
        return createTime;
    }
    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }
    /**
     * @return the dbType
     */
    public String getDbType() {
        return dbType;
    }
    /**
     * @return the dsName
     */
    public String getDsName() {
        return dsName;
    }
    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @return the isAlarm
     */
    public Integer getIsAlarm() {
        return isAlarm;
    }
    /**
     * @return the isMonitor
     */
    public Integer getIsMonitor() {
        return isMonitor;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @return the updateTime
     */
    public Long getUpdateTime() {
        return updateTime;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    /**
     * @param dbType the dbType to set
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
    /**
     * @param dsName the dsName to set
     */
    public void setDsName(String dsName) {
        this.dsName = dsName;
    }
    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @param isAlarm the isAlarm to set
     */
    public void setIsAlarm(Integer isAlarm) {
        this.isAlarm = isAlarm;
    }
    /**
     * @param isMonitor the isMonitor to set
     */
    public void setIsMonitor(Integer isMonitor) {
        this.isMonitor = isMonitor;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBusinessSystem() {
        return businessSystem;
    }

    public void setBusinessSystem(String businessSystem) {
        this.businessSystem = businessSystem;
    }
}
