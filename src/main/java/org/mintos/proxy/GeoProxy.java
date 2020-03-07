package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.geo.GeoResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class GeoProxy extends ServiceProxy {

    final String ipFindUrl;

    public GeoProxy() {
        ipFindUrl= conf.getString("mintos.ip-find.url");
    }

    public CompletableFuture<GeoResponse> getGeoResponse(final String ipAddress) {
        return CompletableFuture.supplyAsync(() -> {
            final URIBuilder uriBuilder;
            try {
                uriBuilder = new URIBuilder(ipFindUrl).addParameter("ip", ipAddress);
                return get(GeoResponse.class, uriBuilder);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
