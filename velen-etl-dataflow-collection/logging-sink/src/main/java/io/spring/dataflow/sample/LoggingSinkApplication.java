package io.spring.dataflow.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

//@EnableBinding(Sink.class)
@SpringBootApplication
public class LoggingSinkApplication {

	/*@MessageEndpoint
	public static class LoggingMessageEndpoint {
		@ServiceActivator(inputChannel = Sink.INPUT)
		public void logIncomingMessages(@Payload String msg, @Headers Map<String, Object> headers) {
			System.out.println("logging-sink**************"+ msg);
			headers.entrySet().forEach(e -> System.out.println(e.getKey() + '=' + e.getValue()));
		}
	}*/

	/*@StreamListener(Sink.INPUT)
	public void loggerSink(String date) {
		System.out.println("logging-sink Received: " + date);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(LoggingSinkApplication.class, args);
	}
}
