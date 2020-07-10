package io.spring.dataflow.sample.service;

import io.spring.dataflow.sample.entity.User;
import io.spring.dataflow.sample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public User Sel(int id){
		return userMapper.Sel(id);
	}
}
