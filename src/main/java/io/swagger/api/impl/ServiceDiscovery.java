package io.swagger.api.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceDiscovery {
    private static Map<String, ServiceLocation> cache = new HashMap<>();

    private ServiceDiscovery() {

    }

    public static ServiceLocation get(String name) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }

        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name + "-service.properties"));
            ServiceLocation sl = new ServiceLocation();
            sl.setServiceName(name);
            sl.setProtocol(prop.getProperty("protocol"));
            sl.setHostname(prop.getProperty("hostname"));
            sl.setBasePath(prop.getProperty("basepath"));
            sl.setPort(Integer.valueOf(prop.getProperty("port")));
            cache.put(name, sl);
            return sl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
