package org.mintos;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.mintos.service.MintosService;

public class Application {

    public static void main(String[] args) throws Exception {
        final Config conf = ConfigFactory.load();
        int serverPort = conf.getInt("mintos.server-port");
        new MintosService(serverPort).start();
    }
}
