package de.uniba.dsg.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import de.uniba.dsg.jaxrs.resources.ChartResource;

import java.net.URI;
import java.util.Properties;

import javax.ws.rs.core.UriBuilder;

import de.uniba.dsg.Configuration;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/")
public class ChartApi extends Application{
    private static Properties properties = Configuration.loadProperties();

    public static void main(String[] args) {
        String serverUri = properties.getProperty("restServerUri");

        URI baseUri = UriBuilder.fromUri(serverUri).build();
        ResourceConfig config = ResourceConfig.forApplicationClass(ChartApi.class);
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Server ready to serve your JAX-RS requests...");
    }
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ChartResource.class);
        return resources;
    }
}
