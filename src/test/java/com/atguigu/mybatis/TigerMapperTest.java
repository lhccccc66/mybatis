package com.atguigu.mybatis;

import com.atguigu.mybatis.entities.Tiger;
import com.atguigu.mybatis.mapper.TigerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TigerMapperTest {

     @Autowired
     TigerMapper tigerMapper;

     @Test
    public void test() {
         Tiger tiger = new Tiger();
         tiger.setTigerName("虎");
         tiger.setTigerSalary(500.00);
         tigerMapper.selectTigerByCondition(tiger);
     }
}
