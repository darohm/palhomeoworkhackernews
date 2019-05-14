package io.pivotal.pal.hackernews;

import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.web.client.RestTemplateBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class HackerNewsApplication {
    private static final Logger log = LoggerFactory.getLogger(HackerNewsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HackerNewsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
