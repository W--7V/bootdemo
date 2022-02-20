package springboot.demo.service;

import java.util.List;

import org.apache.kafka.common.protocol.types.Field;
import springboot.demo.bean.Novel;

public interface NovelService {
	List<Novel> list();
	
	Novel getNovelById(int id);

	Novel incrementById(int id);

	Novel testSqlInject(String query);

	String testTransactional(String query);
}
