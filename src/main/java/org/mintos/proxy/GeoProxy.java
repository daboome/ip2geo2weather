package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.geo.GeoResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class GeoProxy extends ServiceProxy {

    final String ipFindUrl;

    public GeoProxy() {
        ipFindUrl= conf.getString("mintos.ip-find.url");
    }

    public GeoResponse getGeoResponse(final String ipAddress) throws URISyntaxException, IOException {
        final URIBuilder uriBuilder = new URIBuilder(ipFindUrl).addParameter("ip", ipAddress);
        return get(GeoResponse.class, uriBuilder);
    }
}
