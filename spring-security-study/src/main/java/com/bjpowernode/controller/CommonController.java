package com.bjpowernode.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/admin/query")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminQuery(){
        return "admin-query";
    }

    @GetMapping("/manager/save")
    @PreAuthorize("hasRole('MANAGER')")
    public String managerSave(){
        return "manager-save";
    }

    @GetMapping("/student/remove")
    @PreAuthorize("hasRole('STUDENT')")
    public String studentRemove(){
        return "student-remove";
    }
}
