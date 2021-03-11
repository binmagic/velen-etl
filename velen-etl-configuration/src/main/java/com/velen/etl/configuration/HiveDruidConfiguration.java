package com.velen.etl.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * datasource:
 *   hive:
 *     url: jdbc:hive2://119.254.118.20:10000/default
 *     type: com.alibaba.druid.pool.DruidDataSource
 *     username: root
 *     password: sixmonth
 *     driver-class-name: org.apache.hive.jdbc.HiveDriver
 *     initial-size: 1
 *     min-idle: 1
 *     max-idle: 5
 *     max-active: 50
 *     max-wait: 10000
 *     time-between-eviction-runs-millis: 10000
 *     min-evictable-idle-time-millis: 300000
 *     validation-query: select 'x'
 *     test-while-idle: true
 *     test-on-borrow: false
 *     test-on-return: false
 *     pool-prepared-statements: true
 *     max-open-prepared-statements: 20
 *     filters: stat
 */
@Configuration
@ConfigurationProperties(prefix = "durid.hive", ignoreInvalidFields = true)
@ConditionalOnProperty(prefix="durid.hive", name = "url")
@Setter
@Getter
@RefreshScope
public class HiveDruidConfiguration
{
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private int initialSize;
	private int minIdle;
	private int maxActive;
	private int maxWait;
	private int timeBetweenEvictionRunsMillis;
	private int minEvictableIdleTimeMillis;
	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;

	@Bean(name = "hiveDruidDataSource")
	@Qualifier("hiveDruidDataSource")
	public DataSource dataSource()
	{
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setDriverClassName(driverClassName);

		// pool configuration
		ds.setInitialSize(initialSize);
		ds.setMinIdle(minIdle);
		ds.setMaxActive(maxActive);
		ds.setMaxWait(maxWait);
		ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		ds.setValidationQuery(validationQuery);
		ds.setTestWhileIdle(testWhileIdle);
		ds.setTestOnBorrow(testOnBorrow);
		ds.setTestOnReturn(testOnReturn);
		ds.setPoolPreparedStatements(poolPreparedStatements);
		ds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		return ds;
	}

	@Bean(name = "hiveDruidTemplate")
	public JdbcTemplate hiveDruidTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}

}