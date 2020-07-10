package com.velen.etl.dispatcher.service.impl;

import com.velen.etl.dispatcher.service.DataFlowStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.rest.client.DataFlowOperations;
import org.springframework.cloud.dataflow.rest.resource.StreamAppStatusResource;
import org.springframework.cloud.dataflow.rest.resource.StreamDefinitionResource;
import org.springframework.cloud.dataflow.rest.resource.StreamDeploymentResource;
import org.springframework.cloud.skipper.domain.Deployer;
import org.springframework.cloud.skipper.domain.PackageIdentifier;
import org.springframework.cloud.skipper.domain.Release;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class DataFlowStreamServiceImpl implements DataFlowStreamService
{
	@Autowired
	private DataFlowOperations dataFlowOperations;

	@Override
	public PagedModel<StreamDefinitionResource> list()
	{
		return dataFlowOperations.streamOperations().list();
	}

	@Override
	public StreamDefinitionResource createStream(String name, String definition, String description, boolean deploy)
	{
		// check parameter
		assert name != null;
		assert definition != null;

		return dataFlowOperations.streamOperations().createStream(name,definition, description, deploy);
	}

	@Override
	public void undeploy(String name)
	{
		// check parameter
		assert name != null;

		dataFlowOperations.streamOperations().undeploy(name);
	}

	@Override
	public void undeployAll()
	{
		dataFlowOperations.streamOperations().undeployAll();
	}

	@Override
	public void destroy(String name)
	{
		// check parameter
		assert name != null;

		dataFlowOperations.streamOperations().destroy(name);
	}

	@Override
	public void destroyAll()
	{
		dataFlowOperations.streamOperations().destroyAll();
	}

	@Override
	public void updateStream(String streamName, String releaseName, PackageIdentifier packageIdentifier, Map<String, String> updateProperties, boolean force, List<String> appNames)
	{
		// check parameter
		assert streamName != null;
		assert releaseName != null;
		assert packageIdentifier != null;

		dataFlowOperations.streamOperations().updateStream(streamName, releaseName, packageIdentifier, updateProperties, force, appNames);
	}

	@Override
	public void rollbackStream(String streamName, int version)
	{
		assert streamName != null;

		dataFlowOperations.streamOperations().rollbackStream(streamName, version);
	}

	@Override
	public StreamDefinitionResource getStreamDefinition(String streamName)
	{
		assert streamName != null;

		return dataFlowOperations.streamOperations().getStreamDefinition(streamName);
	}

	@Override
	public String getManifest(String streamName, int version)
	{
		assert streamName != null;

		return dataFlowOperations.streamOperations().getManifest(streamName, version);
	}

	@Override
	public Collection<Release> history(String streamName)
	{
		assert streamName != null;

		return dataFlowOperations.streamOperations().history(streamName);
	}

	@Override
	public Collection<Deployer> listPlatforms()
	{
		return dataFlowOperations.streamOperations().listPlatforms();
	}

	@Override
	public StreamAppStatusResource validateStreamDefinition(String streamDefinitionName) throws OperationNotSupportedException
	{
		assert streamDefinitionName != null;

		return dataFlowOperations.streamOperations().validateStreamDefinition(streamDefinitionName);
	}

	@Override
	public void scaleApplicationInstances(String streamName, String appName, Integer count, Map<String, String> properties)
	{
		assert streamName != null;
		assert appName != null;
		assert count != null;

		dataFlowOperations.streamOperations().scaleApplicationInstances(streamName, appName, count, properties);
	}

	@Override
	public StreamDeploymentResource info(String name)
	{
		assert name != null;

		return dataFlowOperations.streamOperations().info(name);
	}

	@Override
	public void deploy(String name, Map<String, String> properties)
	{
		assert name != null;

		dataFlowOperations.streamOperations().deploy(name, properties);
	}

	@Override
	public boolean isDeployed(String name)
	{
		StreamDeploymentResource resource = info(name);
		return resource.getStatus() != null && resource.getStatus().equalsIgnoreCase("deployed");
	}

	@Override
	public boolean exist(String name)
	{
		assert name != null;

		String dsl = getDefinition(name);

		return dsl != null && !dsl.isEmpty();
	}

	@Override
	public String getDefinition(String name)
	{
		assert name != null;

		try
		{
			StreamDefinitionResource resource = getStreamDefinition(name);
			return resource.getOriginalDslText();
		}
		catch(Exception e)
		{
		}

		return null;
	}
}
