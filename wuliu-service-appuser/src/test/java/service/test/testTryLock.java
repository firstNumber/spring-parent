package service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weilai.common.util.LockUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml" })
public class testTryLock {
	@Test
	public void testtry() {

		final String batchNum = "G123456";
		boolean tryflag = LockUtil.tryLock("weialiappUser.accept", batchNum);
		// String value = "\"" + batchNum + "\"";
		if (!tryflag) {
			System.out.println("多人抢单 订单正在分派");
		}
		LockUtil.unlock("weialiappUser.accept", batchNum);
	}

	private String getVlue() {
		int random = (int) ((Math.random() * 9 + 1) * 100000);
		long now = System.currentTimeMillis();
		return String.valueOf(now) + String.valueOf(random);
	}
}
