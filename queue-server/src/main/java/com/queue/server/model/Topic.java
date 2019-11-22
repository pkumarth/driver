package com.queue.server.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("topic")
public class Topic implements Serializable{
	private static final long serialVersionUID = 8279738760612229090L;
	@Id
	private String id;
	private String topic;
	private String part;
	private String msg;

}
