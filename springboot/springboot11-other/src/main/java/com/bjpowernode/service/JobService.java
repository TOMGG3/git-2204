package com.bjpowernode.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    /**
     * 定时任务：在指定的时间周期内自动执行的方法
     *
     * @Scheduled 定时任务注解
     * cron配置项为时间表达式
     *
     * 秒 分 时 日 月 周 年(可选)
     * *星号: 表示每，每秒、每分、每时、每日...
     * ?问号: 只能在日和周两个位置出现，排除冲突
     * -中划线: 表示一个范围
     * ,逗号: 表示一个列表值，比如在星期中使用1,2,4,5
     */
//    @Scheduled(cron = "* * * ? * 1-5")
//    @Scheduled(cron = "* * * 1 * ?")
    @Scheduled(cron = "0/5 * * ? * 1-5")
    public void myJob(){
        System.out.println("定时任务....");
    }
}
