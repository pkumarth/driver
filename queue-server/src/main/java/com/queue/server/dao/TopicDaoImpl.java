package com.queue.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.queue.server.model.Topic;

@Repository
public class TopicDaoImpl implements TopicDao {
	@Autowired
	private MongoTemplate template;

	@Override
	public void save(Topic topic) {
		template.save(topic);

	}
	@Override
	public List<Topic> find(String topic, String part) {
		Query query = new Query();
		query.addCriteria(Criteria.where("topic").is(topic));
		query.addCriteria(Criteria.where("part").is(part));
		return template.find(query, Topic.class);
	}

}
