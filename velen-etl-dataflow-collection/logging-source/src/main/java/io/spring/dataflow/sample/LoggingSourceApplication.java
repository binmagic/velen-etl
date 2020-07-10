package io.spring.dataflow.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

//@EnableBinding(Source.class)
@SpringBootApplication
public class LoggingSourceApplication
{
	/*@Bean
	@InboundChannelAdapter(
			value = Source.OUTPUT,
			poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1")
	)
	public MessageSource<Long> timeMessageSource() {

		System.out.println(new Date() +"======================logging-source========================== execued");
		return () -> MessageBuilder.withPayload(new Date().getTime()).build();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(LoggingSourceApplication.class, args);
	}
}
