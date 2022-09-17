package com.bjpowernode.controller;

import com.bjpowernode.entity.SysPermission;
import com.bjpowernode.entity.SysUser;
import com.bjpowernode.security.utils.SecurityUtil;
import com.bjpowernode.service.SysPermissionService;
import com.bjpowernode.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author steven
 */
@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    /**
     * 服务对象
     */
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询所有数据
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       SysPermission entity){
        return Result.success(sysPermissionService.pageVo(pageNumber, pageSize, entity));
    }

    /**
     * 查询树形数据
     */
    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    public Result tree(){
        return Result.success(sysPermissionService.tree());
    }

    /**
     * 查询当前用户拥有的权限列表
     */
    @GetMapping("/nav")
    public Result nav(){
        return Result.success(sysPermissionService.listByUserId(SecurityUtil.getUserId()));
    }

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    public Result list(){
        return Result.success(sysPermissionService.list());
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    public Result get(@PathVariable("id") Integer id){
        return Result.success(sysPermissionService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:permission:save')")
    public Result save(@RequestBody SysPermission entity){
        return Result.success(sysPermissionService.save(entity));
    }

    /**
     * 编辑数据
     */
    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('sys:permission:update')")
    public Result edit(@RequestBody SysPermission entity){
        return Result.success(sysPermissionService.updateById(entity));
    }

    /**
     * 单条删除数据
     */
    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('sys:permission:del')")
    public Result remove(@PathVariable("id") Integer id){
        return Result.success(sysPermissionService.removeById(id));
    }

    /**
     * 批量删除数据
     */
    @DeleteMapping("/remove")
    @PreAuthorize("hasAuthority('sys:permission:del')")
    public Result removeBatch(@RequestParam("idList") List<Integer> idList){
        return Result.success(sysPermissionService.removeBatchByIds(idList));
    }
}
