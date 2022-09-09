package com.bjpowernode.controller;

import com.bjpowernode.entity.Student;
import com.bjpowernode.vo.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Api(tags = "学生信息接口")
public class StudentController {

    /**
     * 传参方式：
     * 1.路径拼接传参: query      /student/page?pageNumber=1&pageSize=10
     * 2.路径传参： path         /student/get/2
     * 3.请求体传参：body
     */
    @GetMapping("/page")
    //描述处理请求的方法
    @ApiOperation("学生信息分页和条件查询")
    //描述请求参数
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页码", required = false, dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", defaultValue = "10", paramType = "query")
    })
    //描述响应格式
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = -1, message = "失败")
    })
    public Result page(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                       Student student){
        System.out.println(pageNumber);
        System.out.println(pageSize);
        System.out.println(student);
        return Result.success();
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据学生编号查询学生信息")
    @ApiImplicitParam(name = "id", value = "学生编号", required = true, dataType = "int", paramType = "path")
    public Result get(@PathVariable("id") Integer id){
        System.out.println(id);
        return Result.success();
    }

    @PostMapping("/save")
    @ApiOperation("学生信息新增")
    public Result save(@RequestBody Student student){
        System.out.println(student);
        return Result.success();
    }

    @PutMapping("/edit")
    @ApiOperation("学生信息编辑")
    public Result edit(@RequestBody Student student){
        System.out.println(student);
        return Result.success();
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation("根据学生编号删除学生信息")
    @ApiImplicitParam(name = "id", value = "学生编号", required = true, dataType = "int", paramType = "path")
    public Result remove(@PathVariable("id") Integer id){
        System.out.println(id);
        return Result.success();
    }
}
