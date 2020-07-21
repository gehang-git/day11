package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper extends BaseMapper<Person> {

	List<Person> findAll(Person person);

}