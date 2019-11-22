package com.queue.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queue.server.dao.TopicDao;
import com.queue.server.model.Topic;

@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicDao topicDao;

	@Override
	public List<Topic> find(String topic, String part) {
		return topicDao.find(topic,part);
	}
	@Override
	public void save(Topic topic) {
		topicDao.save(topic);
	}
	

}
