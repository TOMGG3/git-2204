package com.bjpowernode.service;

import java.util.List;

public interface UserService {

    void save();

    void edit();

    void remove(Integer id);

    List<String> list();
}
