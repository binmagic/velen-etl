package io.spring.dataflow.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

//@EnableBinding(Processor.class)
@MapperScan("io.spring.dataflow.sample.mapper")
@SpringBootApplication(scanBasePackages = "io.spring.dataflow")
public class LoggingProcessorApplication {

	/*@Transformer(inputChannel = Processor.INPUT,
			outputChannel = Processor.OUTPUT)
	public Object transform(Long timestamp) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:yy");
		String date = dateFormat.format(timestamp);
		System.out.println(date + "------------------------------logging-proccessor-------------------------------  executed");
		return date;
	}*/
	public static void main(String[] args) {
		SpringApplication.run(LoggingProcessorApplication.class, args);
	}
}
