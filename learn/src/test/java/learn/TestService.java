package learn;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.LearnApplication;
import com.learn.springboot.cache.CacheTestService;
import com.learn.springboot.email.SendEmail;
import com.learn.springboot.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
@Slf4j
public class TestService {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SendEmail sendEmail;
	
	@Autowired
	private CacheTestService cacheTestService;

	@Test
	public void testMonTemp(){
		orderService.testMonTemp();
	}
	
	@Test
	public void sendMail() throws Exception{
		try {
//			sendEmail.sendSimpleMail();
//			sendEmail.sendAttachmentsMail();
			sendEmail.sendInlineMail();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
}
