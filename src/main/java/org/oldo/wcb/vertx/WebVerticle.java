package org.oldo.wcb.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class WebVerticle extends AbstractVerticle {

    private final int port;
    private final Handler<RoutingContext> handler;

    public WebVerticle(int port, Handler<RoutingContext> handler) {
        this.port = port;
        this.handler = handler;
    }

    @Override
    public void start() {
        final Vertx vertx = getVertx();
        final HttpServer httpServer = vertx.createHttpServer();
        final Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route().handler(handler);
        httpServer.requestHandler(router).listen(port);
    }
}
