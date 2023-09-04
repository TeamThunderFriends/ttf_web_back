package ttf.lost.infrastructure.api.equipment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ttf.lost.common.utils.LostArkAPIResponseParser;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LostArkUserEquipmentAPI implements EquipmentAPIClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<EquipmentDto> getArmoryEquipment(String characterName) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAyODUxNjIifQ.mLx-qiuSLLxlzMNJB4Pqw9FHk_h93AL7_CPGeac2IF9StTsrzFPqDRdsg4NzHP0kj0x6LQ3_BPghd6IF50nEs24HvR4vrhzXJ2Sx-8LXOIHt0nM6PTrPBb9ChHSMaXkR4KR3jpOjwlVzSDCioFEjs1SEo4lpCnGgmjKGXdirvoL8GF4EWVM0c5hu0GlRdGIv9UujQzcVJIJjioq2wd_w63DyGOpQg19Wd_7NfvzqiqraZUkTCpN4YLWqnGQisvzzaGy_qagmyFwfe0Hchq33h-ln-3Z7PCgES8J-jbhSQSaCQFk2lVaKSxhcjLEFuNY3xlU1drjapNL1DQolZuzI2A";
        String baseURL
                = "https://developer-lostark.game.onstove.com/armories/characters/{value}?filters=equipment";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(baseURL)
                .buildAndExpand(characterName);

        log.info("uri : {}", uri.toUriString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        httpHeaders.set("Authorization", "bearer " + accessToken);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response
                = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, entity, String.class);

        String body = response.getBody();
        log.info("body : {}", body);
        return LostArkAPIResponseParser.parseJsonArray(body, EquipmentDto.class, objectMapper);
    }


}
