
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weilai.task.core.UserTaskService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
public class TaskTest {
	@Autowired
	private UserTaskService userTaskService;
	
	@Test
	public void userSettlement(){
		try{
		String invoke =userTaskService.invoke("userTaskService", "pushUser");
		System.out.println(invoke);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
}
