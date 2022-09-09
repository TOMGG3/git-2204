package com.bjpowernode.controller;

import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public List<Dept> list(){
        return deptService.list();
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Integer id){
        return deptService.get(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody Dept entity){
        deptService.save(entity);
        return "ok";
    }

    @PutMapping("/edit")
    public String edit(@RequestBody Dept entity){
        deptService.edit(entity);
        return "ok";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id){
        deptService.remove(id);
        return "ok";
    }
}
