package com.example.utils;

import com.example.utils.domain.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kaixu on 2017/10/25.
 */
public class JDBCUtil {
    private static Map<String, BasicDataSource> map = new ConcurrentHashMap<>();

    public static Connection getConnection(DataSource dataSource) throws Exception {
        BasicDataSource bds = map.get(dataSource.getHost() + dataSource.getDbName());
        BasicDataSource ds = null;
        if (bds == null) {
            ds = createBasicDataSource(dataSource);
            map.put(dataSource.getHost() + dataSource.getDbName(), ds);
        } else if (bds != null && bds.isClosed()) {
            ds = createBasicDataSource(dataSource);
            map.put(dataSource.getHost() + dataSource.getDbName(), ds);
        }
        return map.get(dataSource.getHost() + dataSource.getDbName()).getConnection();
    }

    private static BasicDataSource createBasicDataSource(DataSource dataSource) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("url", dataSource.getUrl());
        properties.setProperty("username", dataSource.getUsername());
        properties.setProperty("password", dataSource.getPassword());
        properties.setProperty("driverClassName", dataSource.getDriverName());
        properties.setProperty("initialSize", "5");
        properties.setProperty("maxActive", "8");
        properties.setProperty("maxWait", "3000");
        return (BasicDataSource) BasicDataSourceFactory.createDataSource(properties);
    }

    /**
     * 测试连接
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    public static boolean testConn(DataSource dataSource) throws Exception {
        boolean connected = false;
        Connection con = getConnection(dataSource);
        if (con != null) {
            connected = true;
            con.close();
            return connected;
        }
        con.close();
        return connected;
    }

    /**
     * 列出所有模式
     *
     * @param data
     * @param sql
     * @return
     */
    public static List<String> listModel(DataSource data, String sql) {
        List<String> model = new ArrayList<>();
        try (
                Connection conn = getConnection(data);
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                String table = rs.getString("table_schema");
                if (table.contains("sr_catalog") || table.contains("information_schema")) {
                    continue;
                }
                model.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    public static void main(String[] args) throws Exception {
        //生成脚本文件
//        DataSource ds = new DataSource();
//        ds.setUrl("jdbc:srdbsql://10.167.210.178:9999/srdb");
//        ds.setUsername("jk");
//        ds.setPassword("xinhe!@#");
//        ds.setDriverName("org.srdbsql.Driver");
//        ds.setDbName("srdb");
//        // 连接测试
////        boolean res = JDBCUtil.testConn(ds);
////        System.out.println(res);
//
//        Metadata metadata = JDBCUtil.listColumes(ds,"jk","t_ttt");
//        System.out.println(metadata.getColumns());
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@//10.150.33.100:1521/erpdb";
        String password = "1qazXSW@";
        Connection con = DriverManager.getConnection(url, "txbi_readonly", password);
        if (con != null) {
            System.out.println("success");
            con.close();
        }

    }
}
