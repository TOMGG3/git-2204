package com.bjpowernode.crm.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class IndexController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/settings/index")
    public String settingsIndex(){
        return "settings/index";
    }

    @GetMapping("/settings/qx/index")
    public String settingsQxIndex(){
        return "settings/qx/index";
    }

    @GetMapping("/settings/user/index")
    public String settingsUserIndex(){
        return "settings/qx/user/index";
    }

    @GetMapping("/settings/dictionary/index")
    public String settingsDictionaryIndex(){
        return "settings/dictionary/index";
    }

    @GetMapping("/settings/dictionary/type/index")
    public String settingsDictionaryTypeIndex(){
        return "settings/dictionary/type/index";
    }

    @GetMapping("/settings/dictionary/value/index")
    public String settingsDictionaryValueIndex(){
        return "settings/dictionary/value/index";
    }

    @GetMapping("/workbench/index")
    public String workbenchIndex(){
        return "workbench/index";
    }

    @GetMapping("/workbench/main/index")
    public String workbenchMainIndex(){
        return "workbench/main/index";
    }

    @GetMapping("/workbench/activity/index")
    public String workbenchActivityIndex(){
        return "workbench/activity/index";
    }

    @GetMapping("/workbench/activity/detail")
    public String workbenchActivityDetail(){
        return "workbench/activity/detail";
    }
}
