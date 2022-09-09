package com.bjpowernode.crm.settings.controller;

import com.bjpowernode.crm.commons.utils.Result;
import com.bjpowernode.crm.settings.entity.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @param user 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       User user) {
        return Result.success(userService.page(pageNumber, pageSize, user));
    }

    /**
     * 列表查询
     * @param user 查询条件
     * @return
     */
    @GetMapping("/list")
    public Result list(User user){
        return Result.success(userService.list(user));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id) {
        return Result.success(userService.get(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody User user, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        user.setCreateBy(loginUser.getLoginAct());
        user.setEditBy(loginUser.getLoginAct());
        return Result.success(userService.save(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public Result edit(@RequestBody User user, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        user.setEditBy(loginUser.getLoginAct());
        return Result.success(userService.edit(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") String id) {
        return Result.success(userService.remove(id));
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键数组
     * @return 删除是否成功
     */
    @DeleteMapping("/remove")
    public Result removeBatch(String[] ids) {
        return Result.success(userService.removeBatch(ids));
    }
}