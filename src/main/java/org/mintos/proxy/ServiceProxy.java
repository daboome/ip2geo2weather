package org.mintos.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

abstract class ServiceProxy {

    private final ObjectMapper mapper = new ObjectMapper();
    final Config conf = ConfigFactory.load();

    <T> T get(final Class<T> clazz, final URIBuilder uriBuilder) throws IOException, URISyntaxException {
        final URI uri = uriBuilder.build();
        final Response response = Request.Get(uri).execute();
        return mapper.readValue(response.returnContent().asStream(), clazz);
    }
}
