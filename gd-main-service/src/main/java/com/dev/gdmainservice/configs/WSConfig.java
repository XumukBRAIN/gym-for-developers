package com.dev.gdmainservice.configs;

import com.dev.gdmainservice.repositories.GdNoteRepository;
import com.dev.gdmainservice.soap.NoteSoapService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WSConfig {
    private final Bus bus;

    @Autowired
    public WSConfig(Bus bus) {
        this.bus = bus;
    }

    @Bean
    public Endpoint nodeEndpoint(GdNoteRepository repository) {
        EndpointImpl endpoint = new EndpointImpl(bus, new NoteSoapService(repository));
        endpoint.publish("/note");
        return endpoint;
    }
}
