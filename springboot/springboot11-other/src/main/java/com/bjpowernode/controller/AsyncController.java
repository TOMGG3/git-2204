package com.bjpowernode.controller;

import com.bjpowernode.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/doAsync")
    public Map<String, Object> doAsync(){
        long start = System.currentTimeMillis();
        //调用service中的三个任务方法
        //方法同步调用：第二个任务方法执行完成之后才能调用第二个方法...第三个方法...。总耗时三个方法时间相加
        //方法异步调用：三个任务方法仅是调用，不再等待方法执行完毕，继续向下执行
        asyncService.task1();
        asyncService.task2();
        asyncService.task3();

        Map<String, Object> map = new HashMap<>();
        long end = System.currentTimeMillis();
        map.put("code", 200);
        map.put("message", "调用方法成功，总耗时为" + (end -start) + "毫秒");
        return map;
    }
}
