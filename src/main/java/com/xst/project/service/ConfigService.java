package com.xst.project.service;

import com.xst.project.pojo.Config;
import com.xst.project.pojo.User;

/**
 * ******************************************************************
 * @brief      网站配置查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:36:40
 * @author     ChangZiYang
 *******************************************************************
 */
public interface ConfigService {

    Config findById(int id);

    User find();
}
