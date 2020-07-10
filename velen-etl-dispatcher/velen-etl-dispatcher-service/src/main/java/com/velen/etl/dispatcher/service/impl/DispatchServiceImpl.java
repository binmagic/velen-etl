package com.velen.etl.dispatcher.service.impl;

import com.velen.etl.dispatcher.service.DispatchService;
import com.velen.etl.verification.entity.DeployEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DispatchServiceImpl implements DispatchService
{
	// TODO: 这里涉及一个问题，什么时候需要 Service, 这里的逻辑完全可以写到 controller 里
	// 这里需要写一个配置，能通过配置中心来修改

	@Override
	public boolean updateStream(DeployEnum.PlatformType platformType, String type, String definition)
	{
		return true;
	}

	@Override
	public boolean updateTask(DeployEnum.PlatformType platformType, String type, String definition)
	{
		return true;
	}

	@Override
	public boolean deployStream(String appId, DeployEnum.PlatformType platform, String type)
	{
		return false;
	}

	@Override
	public boolean deployTask(String appId, DeployEnum.PlatformType platform, String type)
	{
		return false;
	}

	@Override
	public boolean deployApp(String appId, DeployEnum.PlatformType platform, String type)
	{
		return false;
	}
}
