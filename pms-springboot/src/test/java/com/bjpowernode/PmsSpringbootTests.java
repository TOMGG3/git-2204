package com.bjpowernode;

import com.bjpowernode.dao.SysUserMapper;
import com.bjpowernode.entity.SysPermission;
import com.bjpowernode.entity.SysUser;
import com.bjpowernode.service.SysPermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author steven
 */
@SpringBootTest
public class PmsSpringbootTests {

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    void testSelectVoById(){
        SysUser sysUser = sysUserMapper.selectVoById(1);
        System.out.println(sysUser);
    }

    @Autowired
    SysPermissionService sysPermissionService;

    @Test
    void testTree(){
        List<SysPermission> tree = sysPermissionService.tree();
        for (SysPermission sysPermission : tree) {
            System.out.println("第一层:" + sysPermission);
            if(sysPermission.getChildren() != null){
                for (SysPermission child : sysPermission.getChildren()) {
                    System.out.println("第二层:" + child);
                }
            }
        }
    }
}
