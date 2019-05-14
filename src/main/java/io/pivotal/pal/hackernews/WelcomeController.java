package io.pivotal.pal.hackernews;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class WelcomeController {
    RestTemplate restTemplate;

    public WelcomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String sayHello() {
        Story story = getTopStory();
//        HttpEntity<Story> topStory = new HttpEntity<Story>(story);
//        return "<html><body><h1>Title: " + story.getTitle() + "<h1></body></html>";
        String storyTitleAndLink = "<h1><a href=" + story.getUrl() + ">Title: " + story.getTitle() + "</a></h1>";
        return storyTitleAndLink;
    }

    public Story getTopStory(){
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                "https://hacker-news.firebaseio.com/v0/topstories.json",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {});
        List<Integer> topStories = response.getBody();
        Integer topStory = topStories.get(0);
        String topStoryInfoRequestUrl = "https://hacker-news.firebaseio.com/v0/item/" + String.valueOf(topStory) + ".json";
        Story storyInfo = restTemplate.getForObject(
                topStoryInfoRequestUrl,
                Story.class);

        return storyInfo;

//        return topStories;
//        return args -> {
//            List<Long> quote = restTemplate.getForObject(
//                    "https://gturnquist-quoters.cfapps.io/api/random", List<Long>.class);
//            log.info(quote.toString());
//        };
    }
}
