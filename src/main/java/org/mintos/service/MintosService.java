package org.mintos.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class MintosService {

    private final Server server;

    public MintosService(int port) {
        this.server = new Server(port);
    }

    public void start() throws Exception {
        final ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MintosServlet.class, "/weather");
        server.setHandler(servletHandler);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}
