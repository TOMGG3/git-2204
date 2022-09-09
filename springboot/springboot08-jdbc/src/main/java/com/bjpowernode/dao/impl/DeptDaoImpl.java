package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeptDaoImpl implements DeptDao {

    /**
     * spring中操作数据库的工具类
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Dept> select() {
        /**
         * query() 查询多行多列的数据
         */
        String sql = "select deptno,dname,loc from dept";
        RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<>(Dept.class);
        List<Dept> list = jdbcTemplate.query(sql, rowMapper);
        return list;
    }

    @Override
    public Dept selectById(Integer id) {
        /**
         * queryForObject(sql语句, 查询之后返回的数据Class类型, 占位符的值) 查询单条数据，并且返回结果为单列
         * queryForObject(sql语句, RowMapper, 占位符的值) 查询单条数据，并且返回结果为多列
         *
         * RowMapper行映射器：字段与实体类中的成员变量的对应 BeanPropertyRowMapper
         */
//        String sql = "select count(1) from deptno";
        String sql = "select deptno,dname,loc from dept where deptno=?";
        RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<>(Dept.class);
        Dept dept = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return dept;
    }

    @Override
    public void insert(Dept entity) {
        String sql = "insert into dept(dname, loc) values(?,?)";
        jdbcTemplate.update(sql, entity.getDname(), entity.getLoc());
    }

    @Override
    public void update(Dept entity) {
        String sql = "update dept set dname=?,loc=? where deptno=?";
        jdbcTemplate.update(sql, entity.getDname(), entity.getLoc(), entity.getDeptno());
    }

    @Override
    public void deleteById(Integer id) {
        /**
         * update(sql语句, 占位符值)新增、编辑、删除数据
         */
        String sql = "delete from dept where deptno=?";
        jdbcTemplate.update(sql, id);
    }
}
