package com.bjpowernode.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysController {

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('sys:query')")
    public String query(){
        return "sys-query";
    }

    @GetMapping("/save")
    @PreAuthorize("hasAuthority('sys:save')")
    public String save(){
        return "sys-save";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('sys:update')")
    public String update(){
        return "sys-update";
    }

    @GetMapping("/remove")
    @PreAuthorize("hasAuthority('sys:remove')")
    public String remove(){
        return "sys-remove";
    }
}
