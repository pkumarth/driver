package com.queue.server.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.queue.server.model.Topic;
import com.queue.server.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicApi {
	@Autowired
	private TopicService topicService;

	@PostMapping("/")
	public void save(@RequestBody Topic topic) {
		topicService.save(topic);
	}

	@GetMapping(value = "/{topic}/{part}")
	public List<Topic> find(@PathVariable String topic, @PathVariable String part) {
		return topicService.find(topic, part);
	}

}
