package com.joshlong.samplerome;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.GenericHandler;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.feed.dsl.Feed;
import org.springframework.messaging.MessageHeaders;

import java.net.URL;

@SpringBootApplication
public class SampleRomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleRomeApplication.class, args);
    }

    @Bean
    IntegrationFlow springFeedIntegration() throws Exception {
        return IntegrationFlow//
                .from(Feed.inboundAdapter(
                        new URL("https://spring.io/blog/category/engineering.atom"), "spring-rss"
                ))
                .handle((GenericHandler<SyndEntry>) (payload, headers) -> {
                    System.out.println("got a new message " + payload);
                    return null;
                })
                .get();
    }
}
