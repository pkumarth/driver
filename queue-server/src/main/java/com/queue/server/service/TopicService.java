package com.queue.server.service;

import java.util.List;

import com.queue.server.model.Topic;

public interface TopicService {
	public List<Topic> find(String topic, String part);

	public void save(Topic topic);

}
