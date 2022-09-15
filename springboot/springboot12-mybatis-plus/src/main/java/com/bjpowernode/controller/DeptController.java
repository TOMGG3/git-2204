package com.bjpowernode.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import com.bjpowernode.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public Result list(){
        return Result.success(deptService.list());
    }

    @GetMapping("/page")
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        IPage<Dept> iPage = deptService.page(new Page<Dept>(pageNumber, pageSize));
        Map<String, Object> map = new HashMap<>();
        map.put("list", iPage.getRecords());
        map.put("total", iPage.getTotal());
        return Result.success(map);
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Integer id){
        return Result.success(deptService.getById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Dept dept){
        return Result.success(deptService.save(dept));
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody Dept dept){
        return Result.success(deptService.updateById(dept));
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id){
        return Result.success(deptService.removeById(id));
    }

    @DeleteMapping("/remove")
    public Result removeBatch(@RequestParam List<Integer> ids){
        return Result.success(deptService.removeBatchByIds(ids));
    }
}
