package com.bjpowernode.service.impl;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// @Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Dept get(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDao.select();
    }

    @Override
    public PageInfo page(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo pageInfo = new PageInfo(deptDao.select());
        return pageInfo;
    }

    @Override
    /**
     * @Transactional
     * 位置：类和方法
     * 作用：将当前标注类或方法配置上事务
     */
    // @Transactional(rollbackFor = Exception.class)
    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
    //                 readOnly = true, timeout = 50,
    //         rollbackFor = Exception.class, noRollbackFor = NullPointerException.class,
    //         rollbackForClassName = "java.lang.Exception",noRollbackForClassName = "java.lang.NullPointerException")
    public void save(Dept entity) {
        deptDao.insert(entity);
    }

    @Override
    public void edit(Dept entity) {
        deptDao.update(entity);
    }

    @Override
    public void remove(Integer id) {
        deptDao.delete(id);
    }
}
