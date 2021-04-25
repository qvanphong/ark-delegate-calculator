package tech.qvanphong.arkdc.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.qvanphong.arkdc.services.ArkDelegatesService;

@Component
public class DelegateUpdateScheduler {

    private final ArkDelegatesService delegatesService;

    @Autowired
    public DelegateUpdateScheduler(ArkDelegatesService delegatesService) {
        this.delegatesService = delegatesService;
    }

    // Update every 5 minutes.
    @Scheduled(fixedRate = 60000 * 5)
    public void updateDelegate() {
        delegatesService.setDelegates(delegatesService.fetch51Delegates());
    }
}
