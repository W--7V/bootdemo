package springboot.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import springboot.demo.bean.Novel;
import springboot.demo.dao.NovelMapper;
import springboot.demo.service.NovelService;

@Service
public class NovelServiceImpl implements NovelService {
    private static Logger LOGGER = LoggerFactory.getLogger(NovelServiceImpl.class);

    @Autowired
    NovelMapper novelMapper;

    @Override
    public List<Novel> list() {
        LOGGER.info("log in service");
        return null;
    }

    @Override
    public Novel getNovelById(int id) {
        LOGGER.info("getNovelById---id: " + id);
        return novelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Novel incrementById(int id) {
        Novel novel = novelMapper.selectByPrimaryKey(id);
        LOGGER.info("novel ver: {}", novel.getVer());
        novel.setVer(novel.getVer() + 1);
        novelMapper.updateByPrimaryKeySelective(novel);

        if(id == 1){
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public Novel testSqlInject(String query) {
        Novel novel = novelMapper.selectByQuery(query);
        LOGGER.info("queryed novel name:{}",novel.getNovelName());
        return novel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String testTransactional(String query) {
        incrementById(1);
        return "ok";
    }
}
