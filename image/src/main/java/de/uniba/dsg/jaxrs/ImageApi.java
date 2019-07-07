package de.uniba.dsg.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import de.uniba.dsg.jaxrs.resources.ImageResource;
@ApplicationPath("/")
public class ImageApi extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ImageResource.class);
        return resources;
    }
}
