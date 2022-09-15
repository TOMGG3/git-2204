package com.bjpowernode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import com.bjpowernode.mapper.DeptMapper;
import org.springframework.stereotype.Service;

/**
* @author NINGMEI
* @description 针对表【dept】的数据库操作Service实现
* @createDate 2022-09-13 14:03:16
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

}




