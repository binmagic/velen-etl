package etl;

import com.velen.etl.generator.App;
import javafx.application.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={App.class})
public class MongoDBApplicationTests
{
	@Before
	public void testBefore(){
		//验证结果集，提示
		//Assert.assertTrue("错误，正确的返回值为200", status == 200);
		//Assert.assertFalse("错误，正确的返回值为200", status != 200);
		System.out.println("before");
	}

	@After
	public void testAfter()
	{
		System.out.println("after");
	}

	@Test
	public void test()
	{
		System.out.println("start");

	}
}
