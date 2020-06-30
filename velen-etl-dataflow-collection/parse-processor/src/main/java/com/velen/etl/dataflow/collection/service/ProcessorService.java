package com.velen.etl.dataflow.collection.service;

import com.velen.etl.dataflow.collection.channel.MyProcessor;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

/**
 * bindings:
 *   input:
 *      destination: stream-demo
 *   output:
 *      destination: stream-demo-trans
 */
//@EnableBinding(MyProcessor.class)
//@EnableBinding(Processor.class)
public class ProcessorService
{
	//@Autowired
	private Processor processor;

	/**
	 * 消息输出
	 */
	//@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String transform(String payload)
	{
		String output = payload + " trans";
		System.out.println("消息中转站："+ output);
		this.processor.output().send(MessageBuilder.withPayload(output).build());
		return output;
	}

	/**
	 * 通道消息转换
	 */
	//@StreamListener(Processor.INPUT)
	//@SendTo(Processor.OUTPUT)
	public Object transform(Object payload)
	{
		//return new SimpleDateFormat(("yyyy-mm-dd HH:mm:ss")).format(message);
		return payload;
	}


	interface KStreamProcessorX extends KafkaStreamsProcessor
	{

		@Input("userRegionsTableInput")
		KTable<?, ?> inputKTable();

		@Input("userClicksInput")
		KStream<?, ?> userClicks();
	}

}
