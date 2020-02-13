package com.offcn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")

public class TestList {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 右压栈：后添加的对象排在后边
     */
    @Test
    public void testSetValue1(){
        redisTemplate.boundListOps("namelist1").rightPush("刘备");
        redisTemplate.boundListOps("namelist1").rightPush("关羽");
        redisTemplate.boundListOps("namelist1").rightPush("张飞");
    }

    /**
     * 显示右压栈集合
     */
    @Test
    public void testGetValue1(){
        List list = redisTemplate.boundListOps("namelist1").range(0, 10);
        System.out.println(list);
    }

    /**
     * 左压栈：后添加的对象排在前边
     */
    @Test
    public void testSetValue2(){
        redisTemplate.boundListOps("namelist2").leftPush("刘备");
        redisTemplate.boundListOps("namelist2").leftPush("关羽");
        redisTemplate.boundListOps("namelist2").leftPush("张飞");
    }

    /**
     * 显示左压栈集合
     */
    @Test
    public void testGetValue2(){
        List list = redisTemplate.boundListOps("namelist2").range(0, 10);
        System.out.println(list);
    }


    /**
     * 查询集合某个元素
     */
    @Test
    public void testSearchByIndex(){
        String s = (String) redisTemplate.boundListOps("namelist1").index(1);
        System.out.println(s);
    }

    /**
     * 移除集合某个元素
     */
    @Test
    public void testRemoveByIndex(){
// 第一个参数 要删除元素的个数 （0全部删除） 第二个参数 元素值

        redisTemplate.boundListOps("namelist1").remove(1, "关羽");
    }

}
