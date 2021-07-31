package springboot.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.demo.bean.Novel;
import springboot.demo.dao.NovelMapper;
import springboot.demo.service.NovelService;

@Service
public class NovelServiceImpl implements NovelService {
	Logger log = LoggerFactory.getLogger(NovelServiceImpl.class);
	
	@Autowired
	NovelMapper novelMapper;

	@Override
	public List<Novel> list() {
		log.info("log in service");
		return null;
	}

	@Override
	public Novel getNovelById(int id) {
		log.info("getNovelById---id: "+id);
		return novelMapper.selectByPrimaryKey(id);
	}

}
