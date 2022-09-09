package com.bjpowernode.service.impl;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> list() {
        return deptDao.select();
    }

    @Override
    public Dept get(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Dept entity) {
        deptDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(Dept entity) {
        deptDao.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        deptDao.deleteById(id);
    }
}
