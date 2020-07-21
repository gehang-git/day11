package com.xiaoshu.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.ContentcategoryMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.Contentcategory;
import com.xiaoshu.entity.User;

@Service
public class ContentService {

	@Autowired
	private ContentMapper mapper;

	public PageInfo<Content> findPage(Content content, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Content> list = mapper.findAll(content);
		return new PageInfo<Content>(list);
	}

	public void delete(int parseInt) {
		mapper.deleteByPrimaryKey(parseInt);
	}

	public Content findContentByTitle(String contenttitle) {
		Content content = new Content();
		content.setContenttitle(contenttitle);
		return mapper.selectOne(content);
	}

	public void update(Content content) {
		mapper.updateByPrimaryKeySelective(content);
	}

	public void add(Content content) {
		content.setCreatetime(new Date());
		mapper.insert(content);
	}
	@Autowired
	private ContentcategoryMapper cMapper;
	public List<Contentcategory> findAllCate() {
		return cMapper.selectAll();
	}

	public List<Content> findAll(Content content) {
		return mapper.findAll(content);
	}


}
