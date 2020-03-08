package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.geo.GeoResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class GeoProxy extends ServiceProxy {

    private static final String IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG = "Ip2Geo service not available";

    final String ipFindUrl;

    public GeoProxy() {
        ipFindUrl= conf.getString("mintos.ip-find.url");
    }

    public CompletableFuture<GeoResponse> getGeoResponse(final String ipAddress) {
        CompletableFuture<GeoResponse> result = new CompletableFuture<>();
        if (Math.random() < 0.5) {
            result.completeExceptionally(new IllegalStateException(IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG));
        } else {
            return CompletableFuture.supplyAsync(() -> {
                final URIBuilder uriBuilder;
                try {
                    uriBuilder = new URIBuilder(ipFindUrl).addParameter("ip", ipAddress);
                    return get(GeoResponse.class, uriBuilder);
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                    throw new IllegalStateException(IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG);
                }
            });
        }
        return result;
    }
}
