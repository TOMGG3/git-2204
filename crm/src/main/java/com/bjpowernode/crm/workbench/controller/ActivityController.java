package com.bjpowernode.crm.workbench.controller;

import com.bjpowernode.crm.commons.utils.Result;
import com.bjpowernode.crm.settings.entity.User;
import com.bjpowernode.crm.workbench.entity.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (Activity)表控制层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
//@RestController
@Controller
@RequestMapping("/activity")
public class ActivityController {
    /**
     * 服务对象
     */
    @Autowired
    private ActivityService activityService;

    /**
     * 分页查询
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @param activity 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    @ResponseBody
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       Activity activity) {
        return Result.success(activityService.page(pageNumber, pageSize, activity));
    }

    /**
     * 列表查询
     * @param activity 查询条件
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result list(Activity activity){
        return Result.success(activityService.list(activity));
    }

    /**
     * 通过主键查询单条数据,并跳转到详情页
     *
     * @param id 主键
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, HttpServletRequest request) {
        request.setAttribute("activity", activityService.get(id));
        return "workbench/activity/detail";
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public Result get(@PathVariable("id") String id) {
        return Result.success(activityService.get(id));
    }

    /**
     * 新增数据
     *
     * @param activity 实体
     * @return 新增结果
     */
    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestBody Activity activity, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        activity.setCreateBy(loginUser.getLoginAct());
        activity.setEditBy(loginUser.getLoginAct());
        return Result.success(activityService.save(activity));
    }

    /**
     * 编辑数据
     *
     * @param activity 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody Activity activity, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        activity.setEditBy(loginUser.getLoginAct());
        return Result.success(activityService.edit(activity));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/remove/{id}")
    @ResponseBody
    public Result remove(@PathVariable("id") String id) {
        return Result.success(activityService.remove(id));
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键数组
     * @return 删除是否成功
     */
    @DeleteMapping("/remove")
    @ResponseBody
    public Result removeBatch(String[] ids) {
        return Result.success(activityService.removeBatch(ids));
    }
}