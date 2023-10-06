package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.dto.ChatGptRequest;
import com.demo.dto.ChatGptResponse;

@RestController
@RequestMapping("/bot")
public class BotController {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.url}")
	private String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/chat")
	public String chat(@RequestParam("prompt")String prompt) {
		ChatGptRequest request=new ChatGptRequest(model,prompt);
		ChatGptResponse response = restTemplate.postForObject(url, request, ChatGptResponse.class);
		return response.getChoices().get(0).getMessage().getContent();
		
	}

}
