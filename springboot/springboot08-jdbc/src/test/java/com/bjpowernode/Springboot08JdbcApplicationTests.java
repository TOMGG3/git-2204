package com.bjpowernode;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Springboot的测试类
 *
 * @SpringBootTest
 * 作用：标注当前类为springboot项目中的测试类，可以实现在此测试中直接依赖注入IOC容器中的对象
 *
 */
@SpringBootTest
class Springboot08JdbcApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDataSource() throws SQLException {
        System.out.println(dataSource);
        System.out.println(dataSource instanceof DruidDataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        PreparedStatement statement = connection.prepareStatement("select deptno, dname, loc from dept");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int deptno = resultSet.getInt("deptno");
            String dname = resultSet.getString("dname");
            String loc = resultSet.getString("loc");
            System.out.println("deptno:" + deptno + " dname:" + dname + " loc:" + loc);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    void testDruidDataSource(){
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("初始连接数：" + druidDataSource.getInitialSize());
        System.out.println("最小连接数：" + druidDataSource.getMinIdle());
        System.out.println("最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("最大等待时间：" + druidDataSource.getMaxWait());
    }

}
