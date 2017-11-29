package com.zero.customer;

import java.util.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zero.common.util.RedisHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @see http://357029540.iteye.com/blog/2389154 学习zset的使用
 * @author yezhaoxing
 * @date 2017/09/16
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisTest {

    @Resource
    private RedisHelper<String, Map<Integer, Integer>> redisHelper;
    @Resource
    private RedisHelper<String, Integer> zsetRedisHelper;

    @Test
    public void testRedisPerformance() {
        Map<Integer, Integer> testMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            testMap.put(i, i);
        }
        redisHelper.set("test_redis_performance", testMap);
    }

    @Test
    public void testGet() {
        Date beginDate = new Date();
        Map<Integer, Integer> testMap = redisHelper.get("test_redis_performance");
        Integer integer = testMap.get("1");
        log.info("cost time = {}", new Date().getTime() - beginDate.getTime());
        System.out.println(integer);
    }

    @Test
    public void testZSet() {
        // zsetRedisHelper.zSet("rank", 5, 10);
        Set<ZSetOperations.TypedTuple<Integer>> set = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            set.add(new DefaultTypedTuple<>(i, (double) i));
        }
        zsetRedisHelper.zSet("rank_3", set);
    }

    @Test
    public void testZIncrease() {
        zsetRedisHelper.zIncrease("rank", 2, 10);
    }

    @Test
    public void testZRank() {
        Date beginDate = new Date();
        log.info("rank={}", zsetRedisHelper.zRank("rank_3", 50000));
        log.info("cost time = {}", new Date().getTime() - beginDate.getTime());
    }

    @Test
    public void testZGet() {
        Date beginDate = new Date();
        log.info("{}", zsetRedisHelper.zGet("rank", 100));
        log.info("cost time = {}", new Date().getTime() - beginDate.getTime());
    }
}
