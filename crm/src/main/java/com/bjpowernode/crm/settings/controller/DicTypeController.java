package com.bjpowernode.crm.settings.controller;

import com.bjpowernode.crm.commons.utils.Result;
import com.bjpowernode.crm.settings.entity.DicType;
import com.bjpowernode.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (DicType)表控制层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@RestController
@RequestMapping("/dicType")
public class DicTypeController {
    /**
     * 服务对象
     */
    @Autowired
    private DicTypeService dicTypeService;

    /**
     * 分页查询
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @param dicType 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public Result page(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       DicType dicType) {
        return Result.success(dicTypeService.page(pageNumber, pageSize, dicType));
    }

    /**
     * 列表查询
     * @param dicType 查询条件
     * @return
     */
    @GetMapping("/list")
    public Result list(DicType dicType){
        return Result.success(dicTypeService.list(dicType));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id) {
        return Result.success(dicTypeService.get(id));
    }

    /**
     * 新增数据
     *
     * @param dicType 实体
     * @return 新增结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody DicType dicType) {
        return Result.success(dicTypeService.save(dicType));
    }

    /**
     * 编辑数据
     *
     * @param dicType 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public Result edit(@RequestBody DicType dicType) {
        return Result.success(dicTypeService.edit(dicType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") String id) {
        return Result.success(dicTypeService.remove(id));
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键数组
     * @return 删除是否成功
     */
    @DeleteMapping("/remove")
    public Result removeBatch(String[] ids) {
        return Result.success(dicTypeService.removeBatch(ids));
    }
}