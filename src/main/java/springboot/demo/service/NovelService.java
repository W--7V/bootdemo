package springboot.demo.service;

import java.util.List;

import springboot.demo.bean.Novel;

public interface NovelService {
	List<Novel> list();
	
	Novel getNovelById(int id);

	Novel incrementById(int id);
}
