package com.github.february.rakuten.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.february.rakuten.collector.entity.AccessTokenHistory;

public interface AccessTokenHistoryRepo extends JpaRepository<AccessTokenHistory, Integer> {
	
	AccessTokenHistory findTop1BySiteOrderByCreateTimeDesc(String site);

}
