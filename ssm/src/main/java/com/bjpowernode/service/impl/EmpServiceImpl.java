package com.bjpowernode.service.impl;

import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Emp;
import com.bjpowernode.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> list() {
        return empDao.select(null);
    }

    @Override
    public Map<String, Object> page(Integer pageNumber, Integer pageSize, Emp entity) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<Emp> pageInfo = new PageInfo<>(empDao.select(entity));
        Map<String, Object> map = new HashMap<>();
        map.put("list", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public Emp get(Integer id) {
        return empDao.selectById(id);
    }

    @Override
    public boolean save(Emp entity) {
        return empDao.insert(entity) > 0;
    }

    @Override
    public boolean edit(Emp entity) {
        return empDao.update(entity) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        return empDao.delete(id) > 0;
    }

    @Override
    public boolean removeBatch(Integer[] ids) {
        return empDao.deleteBatch(ids) > 0;
    }
}
