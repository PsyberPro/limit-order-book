package za.co.rmb.processor.service.impl;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import za.co.rmb.processor.service.InstanceLocator;

@Service
public class InstanceLocatorImpl implements InstanceLocator {

    private EurekaClient eurekaClient;

    @Autowired
    public InstanceLocatorImpl(final EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Retryable(
            value = {Exception.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 20000)
    )
    public String getInstanceUrl(final String applicationName, final boolean secure) {
        try {
            final InstanceInfo instance = eurekaClient.getNextServerFromEureka(applicationName, secure);
            return instance.getHomePageUrl();

        } catch (final Exception e) {
            throw new RuntimeException("No instances of " + applicationName + " found on Eureka");
        }
    }
}
