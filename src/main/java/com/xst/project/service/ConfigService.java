package com.xst.project.service;

import com.xst.project.pojo.Config;
import com.xst.project.pojo.User;

public interface ConfigService {

    Config findById(int id);

    User find();
}
