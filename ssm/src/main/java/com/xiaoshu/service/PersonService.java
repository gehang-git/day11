package com.xiaoshu.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class PersonService {

	@Autowired
	private PersonMapper mapper;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private CompanyMapper cMapper;
	public List<Company> findCompany() {
		List<Company> list = cMapper.selectAll();
		return list;
	}
	public PageInfo<Person> findAll(Person person, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Person> list= mapper.findAll(person);
		return new PageInfo<>(list);
	}
	public void delete(int parseInt) {
		mapper.deleteByPrimaryKey(parseInt);
	}
	public void update(Person person) {
		mapper.updateByPrimaryKeySelective(person);
	}
	public void add(Person person) {
		mapper.insert(person);
		Jedis jedis = jedisPool.getResource();
		jedis.set(person.getGender(), person.getpName());
		System.out.println("redis缓存中加入数据");
	}

}
