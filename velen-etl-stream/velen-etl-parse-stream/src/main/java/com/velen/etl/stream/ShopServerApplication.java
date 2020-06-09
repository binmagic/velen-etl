package com.velen.etl.stream;

import com.velen.etl.stream.channel.ShopChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(ShopChannel.class)
public class ShopServerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ShopServerApplication.class, args);
	}
}
