package tech.qvanphong.arkdc.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qvanphong.arkdc.models.Datum;
import tech.qvanphong.arkdc.models.Root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ArkDelegatesService {
    private List<Datum> delegates;

    @Value("${delegateLivesUrlAPI}")
    private String delegateLiveUrlAPI;

    @Value("${delegateLivesUrl}")
    private String delegateLiveUrl;

    @Value("${delegateDetailUrl}")
    private String delegateDetailUrl;

    public List<Datum> get51Delegates() {
        if (delegates == null || delegates.isEmpty()) {
            setDelegates(fetch51Delegates());
        }
        return delegates;
    }

    public void setDelegates(List<Datum> delegates) {
        this.delegates = delegates;
    }

    public String getDelegateLiveUrl() {
        return delegateLiveUrl;
    }

    public List<Datum> fetch51Delegates() {
        // Set header to avoid return of 403;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Root> response = restTemplate.exchange(delegateLiveUrlAPI, HttpMethod.GET,entity,Root.class);
        Root root = response.getBody();

        return root == null || root.getData() == null ? new ArrayList<>() : root.getData();
    }

    public String getDelegateDetailUrl() {
        return delegateDetailUrl;
    }
}
