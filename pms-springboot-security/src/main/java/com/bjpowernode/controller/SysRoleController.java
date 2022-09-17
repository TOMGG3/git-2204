package com.bjpowernode.controller;

import com.bjpowernode.entity.SysRole;
import com.bjpowernode.service.SysRoleService;
import com.bjpowernode.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author steven
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    /**
     * 服务对象
     */
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页查询所有数据
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       SysRole entity){
        return Result.success(sysRoleService.pageVo(pageNumber, pageSize, entity));
    }

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Result list(){
        return Result.success(sysRoleService.list());
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Result get(@PathVariable("id") Integer id){
        return Result.success(sysRoleService.get(id));
    }

    /**
     * 新增数据
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:role:save')")
    public Result save(@RequestBody SysRole entity){
        return Result.success(sysRoleService.add(entity));
    }

    /**
     * 编辑数据
     */
    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result edit(@RequestBody SysRole entity){
        return Result.success(sysRoleService.edit(entity));
    }

    /**
     * 单条删除数据
     */
    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public Result remove(@PathVariable("id") Integer id){
        return Result.success(sysRoleService.removeById(id));
    }

    /**
     * 批量删除数据
     */
    @DeleteMapping("/remove")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public Result removeBatch(@RequestParam("idList") List<Integer> idList){
        return Result.success(sysRoleService.removeBatchByIds(idList));
    }
}
