package com.bjpowernode.crm.workbench.controller;

import com.bjpowernode.crm.commons.utils.Result;
import com.bjpowernode.crm.settings.entity.User;
import com.bjpowernode.crm.workbench.entity.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * (ActivityRemark)表控制层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@RestController
@RequestMapping("/activityRemark")
public class ActivityRemarkController {
    /**
     * 服务对象
     */
    @Autowired
    private ActivityRemarkService activityRemarkService;

    /**
     * 列表查询
     * @param activityId 查询条件
     * @return
     */
    @GetMapping("/list")
    public Result list(String activityId){
        return Result.success(activityRemarkService.list(activityId));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id) {
        return Result.success(activityRemarkService.get(id));
    }

    /**
     * 新增数据
     *
     * @param activityRemark 实体
     * @return 新增结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody ActivityRemark activityRemark, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        activityRemark.setCreateBy(loginUser.getLoginAct());
        activityRemark.setEditBy(loginUser.getLoginAct());
        return Result.success(activityRemarkService.save(activityRemark));
    }

    /**
     * 编辑数据
     *
     * @param activityRemark 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public Result edit(@RequestBody ActivityRemark activityRemark, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        activityRemark.setEditBy(loginUser.getLoginAct());
        return Result.success(activityRemarkService.edit(activityRemark));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") String id) {
        return Result.success(activityRemarkService.remove(id));
    }
}