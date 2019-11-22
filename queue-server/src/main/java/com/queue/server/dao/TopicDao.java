package com.queue.server.dao;

import java.util.List;

import com.queue.server.model.Topic;

public interface TopicDao {
	public void save(Topic topic);
	public List<Topic> find(String topic, String part);

}
