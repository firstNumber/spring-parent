package com.weilai.task.quartz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.zhidian.task.quartz.dao.QuartzDataBaseDao;

@Service
public class InitService implements InitializingBean {
	private Logger logger = Logger.getLogger(InitService.class);
	@Autowired
//	private QuartzDataBaseDao quartzDataBaseDaoImpl;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("************加载定时器配置文件*****************");
		List<String> list = PaseXml();
		logger.info("************删除数据库中不存在的定时器任务*****************");
//		quartzDataBaseDaoImpl.deleteQuartzData(list);
		logger.info("************加载定时器配置文件和删除数据库中不存在的定时器任务完成*****************");
	}

	private List<String> PaseXml() {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(InitService.class.getClassLoader().getResourceAsStream(
					"spring/spring-quartz.xml"));
			Element root = document.getRootElement();
			for (Iterator<Element> i = root.elementIterator("bean"); i.hasNext();) {
				Element bean = i.next();
				List<Attribute> listAttr = bean.attributes();
				for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
					if (attr.getName().equals("name") && attr.getValue().equals("quartzScheduler")) {
						return getElementNode(bean);
					}
				}
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<String>();
	}

	private List<String> getElementNode(Element bean) {
		List<String> result = new ArrayList<String>();
		for (Iterator<Element> j = bean.elementIterator("property"); j.hasNext();) {
			Element property = j.next();
			List<Attribute> list = property.attributes();
			for (Attribute attrProperty : list) {// 遍历当前节点的所有属性
				if (attrProperty.getName().equals("name") && attrProperty.getValue().equals("triggers")) {
					for (Iterator<Element> x = property.elementIterator("list"); x.hasNext();) {
						Element listElement = x.next();
						for (Iterator<Element> y = listElement.elementIterator("ref"); y.hasNext();) {
							Element ref = y.next();
							List<Attribute> listRef = ref.attributes();
							for (Attribute attrRef : listRef) {// 遍历当前节点的所有属性
								String refName = attrRef.getName();// 属性名称
								String refValue = attrRef.getValue();//
								logger.info("属性名称：" + refName + "  ,属性值：" + refValue);
								result.add(refValue);
							}
						}
						return result;
					}
				}
			}
		}
		return result;
	}
}
