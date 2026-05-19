package com.laolang.ghost.mybatis;

import cn.hutool.json.JSONUtil;
import java.io.IOException;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HelloTest {

    SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public void beforeAll() {
        try (Reader reader = Resources.getResourceAsReader("com/laolang/ghost/mybatis/mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }
        catch (IOException e) {
            log.error("初始化 sqlSessionFactory 出错.", e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testOne() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysDictTypeMapper sysDictTypeMapper = sqlSession.getMapper(SysDictTypeMapper.class);
            SysDictType dictType = sysDictTypeMapper.selectById(1L);
            Assertions.assertNotNull(dictType);
            log.info("dictType:{}", JSONUtil.toJsonStr(dictType));
        }
    }

}
