package com.xst.project.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xst.project.mapper.ConfigMapper;
import com.xst.project.pojo.Config;
import com.xst.project.pojo.User;
import com.xst.project.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigMapper configMapper;

	@Override
	public Config findById(int id) {
		
		return configMapper.findId(id);
	}

	
	@Override
	public User find() {
		
		return configMapper.find();
	}

}
