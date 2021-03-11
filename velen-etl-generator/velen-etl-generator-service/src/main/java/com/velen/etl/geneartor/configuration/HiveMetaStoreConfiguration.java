package com.velen.etl.geneartor.configuration;

import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.conf.MetastoreConf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "meta.hive", ignoreInvalidFields = true)
@ConditionalOnProperty(prefix="meta.hive", name = "url")
@Setter
@Getter
@RefreshScope
public class HiveMetaStoreConfiguration
{
	private String url;
	private int timeout;

	private String locationUri;


	@Bean(name = "hiveMetaStoreClient")
	public HiveMetaStoreClient hiveMetaStoreClient(@Qualifier("hiveConfiguration") org.apache.hadoop.conf.Configuration configuration) throws MetaException
	{
		HiveMetaStoreClient client = new HiveMetaStoreClient(configuration);

		return client;
	}

	@Bean(name = "hiveConfiguration")
	public org.apache.hadoop.conf.Configuration configuration()
	{
		org.apache.hadoop.conf.Configuration configuration = MetastoreConf.newMetastoreConf();

		MetastoreConf.setVar(configuration, MetastoreConf.ConfVars.THRIFT_URIS, url);
		MetastoreConf.setLongVar(configuration, MetastoreConf.ConfVars.CLIENT_SOCKET_TIMEOUT, timeout);

		return configuration;
	}

}
