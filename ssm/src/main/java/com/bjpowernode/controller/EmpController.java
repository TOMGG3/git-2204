package com.bjpowernode.controller;

import com.bjpowernode.entity.Emp;
import com.bjpowernode.service.EmpService;
import com.bjpowernode.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理异步请求
 * @RestController = @Controller + @ResponseBody
 */
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/page")
    public Result page(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                       Emp emp){
        return Result.success(empService.page(pageNumber, pageSize, emp));
    }

    @GetMapping("/list")
    public Result list(){
        return Result.success(empService.list());
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Integer id){
        return Result.success(empService.get(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Emp emp){
        return Result.success(empService.save(emp));
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody Emp emp){
        return Result.success(empService.edit(emp));
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id){
        return Result.success(empService.remove(id));
    }

    @DeleteMapping("/remove")
    public Result removeBatch(Integer[] ids){
        return Result.success(empService.removeBatch(ids));
    }
}
