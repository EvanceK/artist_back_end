package com.artist.service.impl;


import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.artist.service.LogService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LogServiceImpl implements LogService{

	   private final Logger LOG = LoggerFactory.getLogger(LogServiceImpl.class);
	   private final RestTemplate restTemplate;
	    private final String baseUrl;
	@Override
	public void log() {
		   LOG.info("LogServiceImpl.log invoked");	
		   }
	
	 public LogServiceImpl(RestTemplate restTemplate, @Value("${loki.base.url}") String baseUrl) {
	        this.restTemplate = restTemplate;
	        this.baseUrl = baseUrl;
	    } 
	 public List<String> queryLogs(String expected) throws Exception {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

	        String query = "{level=\"INFO\"} |= `DemoService.log invoked`";

	        // 获取当前 UTC 时间
	        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
	        String current_time_utc = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));

	        LocalDateTime tenMinsAgo = currentDateTime.minusMinutes(10);
	        String start_time_utc = tenMinsAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));

	        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
	                .queryParam("query", query)
	                .queryParam("start", start_time_utc)
	                .queryParam("end", current_time_utc)
	                .build()
	                .toUri();

	        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<String> messages = new ArrayList<>();
	        String responseBody = response.getBody();
	        JsonNode jsonNode = objectMapper.readTree(responseBody);
	        JsonNode result = jsonNode.get("data").get("result").get(0).get("values");

	        result.iterator().forEachRemaining(e -> {
	            Iterator<JsonNode> elements = e.elements();
	            elements.forEachRemaining(f -> messages.add(f.toString()));
	        });

	        // 断言是否包含预期消息
	        if (messages.stream().noneMatch(e -> e.contains(expected))) {
	            throw new Exception("Expected log message not found");
	        }

	        return messages;
	    }
	}

