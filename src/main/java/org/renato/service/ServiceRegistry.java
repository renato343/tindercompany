package org.renato.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 14/03/17.
 */
public final class ServiceRegistry {

    private static ServiceRegistry instance = null;
    private Map<String, Service> serviceMap = new HashMap<>();

    private ServiceRegistry() {
    }

    public static synchronized ServiceRegistry getInstance() {

        if (instance == null) {
            instance = new ServiceRegistry();
        }
        return instance;
    }

    public void add(Service service) {

        if (!serviceMap.containsKey(service.getName())) {
            serviceMap.put(service.getName(), service);
        }
    }


    public Service getService(String name) {
        return serviceMap.get(name);
    }

    public void removeService(Service service) {
        serviceMap.remove(service.getName());
    }


}
