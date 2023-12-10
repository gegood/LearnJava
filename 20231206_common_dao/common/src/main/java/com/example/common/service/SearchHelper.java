package com.example.common.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class SearchHelper {
    
    private SearchHelper() {
        
    }
    
    public static Pageable page(Integer page, Integer size) {
        return PageRequest.of(page == null || page < 1 ? 0 : page - 1, size == null ? Service.DEFAULT_SIZE : size);
    }

    public static String addWildcard(String keyword) {
        return '%' + keyword + '%';
    }

}
