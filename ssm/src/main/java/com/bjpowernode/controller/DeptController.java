package com.bjpowernode.controller;

import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import com.bjpowernode.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理同步请求
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public String list(HttpServletRequest request){
        request.setAttribute("deptList", deptService.list());
        return "dept/list";
    }

    @GetMapping("/listAll")
    @ResponseBody
    public Result listAll(){
        return Result.success(deptService.list());
    }

    @GetMapping("/page")
    public String page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       HttpServletRequest request){
        request.setAttribute("pageInfo", deptService.page(pageNumber, pageSize));
        return "dept/page";
    }

    @GetMapping("/get/{id}")
    public String page(@PathVariable("id") Integer id, HttpServletRequest request){
        request.setAttribute("dept", deptService.get(id));
        return "dept/edit";
    }

    @PostMapping("/save")
    public String save(Dept dept){
        deptService.save(dept);
        //新增，编辑，删除操作完成之后，统一跳转到列表页
        return "redirect:/dept/list";
    }

    @PutMapping("/edit")
    public String edit(Dept dept){
        deptService.edit(dept);
        return "redirect:/dept/list";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id){
        deptService.remove(id);
        return "redirect:/dept/list";
    }
}
