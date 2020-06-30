package com.velen.etl.dataflow.collection.service;

import com.velen.etl.common.entity.VerifyEnum;
import com.velen.etl.dataflow.collection.channel.MyProcessor;
import javafx.util.Pair;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.ArrayList;
import java.util.List;

/**
 * bindings:
 *   input:
 *      destination: stream-demo
 *   output:
 *      destination: stream-demo-trans
 */
@EnableBinding(MyProcessor.class)
public class ProcessorService
{

	private List<Pair<VerifyEnum.InputParseType, String>> rules = new ArrayList();

	/**
	 * 设置过滤规则
	 */
	public List<Pair<VerifyEnum.InputParseType, String>> setFilter(VerifyEnum.InputParseType type, String formula)
	{
		Pair<VerifyEnum.InputParseType, String> pair = new Pair(type, formula);
		if(!this.rules.contains(pair))
		{
			this.rules.add(pair);
		}

		return this.rules;
	}

	/**
	 * 设置过滤规则
	 */
	public List<Pair<VerifyEnum.InputParseType, String>> setFilters(List<Pair<VerifyEnum.InputParseType, String>> filters)
	{
		for(Pair pair : filters)
		{
			if(!this.rules.contains(pair))
			{
				this.rules.add(pair);
			}
		}
		return this.rules;
	}

	/**
	 * 删除规则
	 */
	public List<Pair<VerifyEnum.InputParseType, String>> delete(VerifyEnum.InputParseType type, String formula)
	{
		Pair<VerifyEnum.InputParseType, String> pair = new Pair(type, formula);
		this.rules.remove(pair);
		return this.rules;
	}

	/**
	 * 清理规则
	 */
	public void clear()
	{
		this.rules.clear();
	}

	/**
	 * 消息输出
	 */
	/*@ServiceActivator(inputChannel = MyProcessor.INPUT, outputChannel = MyProcessor.OUTPUT)
	public String transform(String payload)
	{
		System.out.println("消息中转站："+ payload);
		return payload + " trans";
	}*/

	/**
	 * 这种写法与 @ServiceActivator 效果相同
	 */
	@StreamListener(MyProcessor.INPUT)
	@SendTo(MyProcessor.OUTPUT)
	public String transform(String payload)
	{
		// 多线程的处理
		//return new SimpleDateFormat(("yyyy-mm-dd HH:mm:ss")).format(message);
		return "OK";
	}

}
