package za.co.rmb.processor.service;

public interface InstanceLocator {
    String getInstanceUrl(final String applicationName, final boolean isSecured);
}
