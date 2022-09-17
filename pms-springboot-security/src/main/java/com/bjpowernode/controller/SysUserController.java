package com.bjpowernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.entity.SysUser;
import com.bjpowernode.security.utils.SecurityUtil;
import com.bjpowernode.service.SysUserService;
import com.bjpowernode.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author steven
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询所有数据
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       SysUser entity){
        return Result.success(sysUserService.pageVo(pageNumber, pageSize, entity));
    }

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Result list(){
        return Result.success(sysUserService.list());
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Result get(@PathVariable("id") Integer id){
        return Result.success(sysUserService.get(id));
    }

    /**
     * 通过用户名查询用户
     */
    @GetMapping("/getByUsername/{username}")
    public Result getByUsername(@PathVariable String username){
        return Result.success(sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", username)));
    }

    /**
     * 新增数据
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public Result save(@RequestBody SysUser entity){
        entity.setCreateUser(SecurityUtil.getUsername());
        entity.setUpdateUser(SecurityUtil.getUsername());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        return Result.success(sysUserService.add(entity));
    }

    /**
     * 编辑数据
     */
    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public Result edit(@RequestBody SysUser entity){
        entity.setUpdateUser(SecurityUtil.getUsername());
        entity.setUpdateTime(new Date());
        return Result.success(sysUserService.edit(entity));
    }

    /**
     * 单条删除数据
     */
    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('sys:user:del')")
    public Result remove(@PathVariable("id") Integer id){
        return Result.success(sysUserService.removeById(id));
    }

    /**
     * 批量删除数据
     */
    @DeleteMapping("/remove")
    @PreAuthorize("hasAuthority('sys:user:del')")
    public Result removeBatch(@RequestParam("idList") List<Integer> idList){
        return Result.success(sysUserService.removeBatchByIds(idList));
    }
}
