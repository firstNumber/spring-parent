
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 启动Dubbo服务用的MainClass.
 */
public class DubboProviderUser {

	private static final Log log = LogFactory.getLog(DubboProviderUser.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
			context.start();
		} catch (Exception e) {
			log.error("== DubboProvider context start error:", e);
		}
		synchronized (DubboProviderUser.class) {
			while (true) {
				try {
					DubboProviderUser.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:", e);
					log.info(e);
				}
			}
		}
	}

}