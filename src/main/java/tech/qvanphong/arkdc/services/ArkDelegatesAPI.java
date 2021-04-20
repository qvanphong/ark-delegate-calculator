package tech.qvanphong.arkdc.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qvanphong.arkdc.models.Datum;
import tech.qvanphong.arkdc.models.Root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArkDelegatesAPI {
    public List<Datum> get51Delegates() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Root> response = restTemplate.exchange("https://arkdelegates.live/api/delegates?limit=51", HttpMethod.GET,entity,Root.class);
        Root root = response.getBody();

        return root == null || root.getData() == null ? new ArrayList<>() : root.getData();
    }
}
