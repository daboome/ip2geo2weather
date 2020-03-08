package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.IdentifiableEntity;
import org.mintos.model.geo.GeoResponse;
import org.mintos.util.HibernateUtil;

import javax.persistence.Id;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class GeoProxy extends ServiceProxy {

    // Mutable for the sake of unit testing
    private Double failureRate;

    private static final String IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG = "Ip2Geo service not available";

    final String ipFindUrl;

    public GeoProxy() {
        failureRate = conf.getDouble("mintos.failure-rate.ip2geo");
        ipFindUrl= conf.getString("mintos.ip-find.url");
    }

    public CompletableFuture<GeoResponse> getGeoResponse(final String ipAddress) {
        CompletableFuture<GeoResponse> result = new CompletableFuture<>();
        if (Math.random() < failureRate) {
            result.completeExceptionally(new IllegalStateException(IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG));
        } else {
            return CompletableFuture.supplyAsync(() -> {
                final URIBuilder uriBuilder;
                try {
                    uriBuilder = new URIBuilder(ipFindUrl).addParameter("ip", ipAddress);
                    GeoResponse geoResponse = get(GeoResponse.class, uriBuilder);
                    IdentifiableEntity identifiableEntity = new IdentifiableEntity(geoResponse);
                    identifiableEntity.setIpAddress(ipAddress);
                    HibernateUtil.INSTANCE.saveOrUpdateInTransaction(identifiableEntity);
                    geoResponse.setId(identifiableEntity.getId());
                    return geoResponse;
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                    throw new IllegalStateException(IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG);
                }
            });
        }
        return result;
    }

    public static String getIp2GeoServiceNotAvailableErrMsg() {
        return IP_2_GEO_SERVICE_NOT_AVAILABLE_ERR_MSG;
    }

    // Mutating method for the sake of unit testing
    public void setFailureRate(Double failureRate) {
        this.failureRate = failureRate;
    }
}
