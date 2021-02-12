package com.rad.clientservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullClient",types = Client.class)
public interface ClientProjection {
    public Long getId();
    public String getName();
    public String getEmail();
}
