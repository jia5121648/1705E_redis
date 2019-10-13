package com.bw;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jiapengxuan.common.utils.UserUtils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class UserTest {
	@Autowired
	RedisTemplate redisTemplate;
		
	@Test
	public void test01() {
		for (int i = 0; i < 10; i++) {
			User user = new User();
	        user.setId(i+1);
	        user.setName(UserUtils.getName());
	        user.setBirthday(UserUtils.getBirthday());
	        user.setEmail(UserUtils.getMail());
	        user.setIphone(UserUtils.getPhone());
	        user.setGender(UserUtils.getSex());
	        System.out.println(user);
		}
		
	}
	
	@Test
	public void save() {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			User user = new User();
	        user.setId(i+1);
	        user.setName(UserUtils.getName());
	        user.setBirthday(UserUtils.getBirthday());
	        user.setEmail(UserUtils.getMail());
	        user.setIphone(UserUtils.getPhone());
	        user.setGender(UserUtils.getSex());
	        
	        map.put("key"+i,JSON.toJSONString(user));
	        redisTemplate.opsForHash().putAll("user"+i, map);
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化时间是："+(end-start));
	}
	
}
