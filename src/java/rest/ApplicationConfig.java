/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author javie
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    /**
     * Gets classes for web service application resources.  
     * @return A Set containing Class objects for resources.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    /**
     * Adds needed resource's classes to a Set of resources.
     * @param resources The resource's classes Set.
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.LeagueREST.class);
        resources.add(rest.StatsREST.class);
    }
    
}
