package com.bjpowernode.service.impl;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageInfo page(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<Dept> pageInfo = new PageInfo<>(deptDao.select());
        return pageInfo;
    }

    @Override
    public Dept get(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    public boolean save(Dept entity) {
        return deptDao.insert(entity) > 0;
    }

    @Override
    public boolean edit(Dept entity) {
        return deptDao.update(entity) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        return deptDao.delete(id) > 0;
    }
}
