package com.novoboot.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.novoboot.mongodb.collection.CustomerReview;

public interface FeedBackRepository extends MongoRepository<CustomerReview, String> {

//	public CustomerReview findFeednackName(String name);
}
