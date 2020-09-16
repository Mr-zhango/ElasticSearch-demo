package com.iistd.dao;



import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.iistd.entity.Search;

public interface SearchDao extends ElasticsearchRepository<Search, String>{


}
