package ttf.lost.common.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(2))
            .setReadTimeout(Duration.ofSeconds(2))
            .additionalInterceptors((request, body, execution) -> {
                new RetryTemplate().setRetryPolicy(new SimpleRetryPolicy(1));
                try {
                    return new RetryTemplate().execute(context -> execution.execute(request, body));
                } catch (Throwable throwable) {
                    throw new RuntimeException(throwable);
                }
            })
            .build();
    }
}
