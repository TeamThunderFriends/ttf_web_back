package ttf.lost.common.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Value("${lost-ark.api-key}")
	private String LOST_ARK_API_KEY;

	@Bean
	public RestTemplate lostArkRestTemplate() {
		return new RestTemplateBuilder()
			.setConnectTimeout(Duration.ofSeconds(2))
			.setReadTimeout(Duration.ofSeconds(2))
			.defaultHeader("Authorization", LOST_ARK_API_KEY)
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
