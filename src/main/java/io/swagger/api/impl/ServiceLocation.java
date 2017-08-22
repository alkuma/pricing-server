package io.swagger.api.impl;

public class ServiceLocation {
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    private String protocol;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    private String hostname;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    private int port;
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String basePath;
    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBaseUrl() {
        return protocol + "://" + hostname + ":" + String.valueOf(port) + "/" + basePath;
    }

}
