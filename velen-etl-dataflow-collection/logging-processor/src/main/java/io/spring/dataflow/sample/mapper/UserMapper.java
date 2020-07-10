package io.spring.dataflow.sample.mapper;

import io.spring.dataflow.sample.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {

	User Sel(int id);
}
