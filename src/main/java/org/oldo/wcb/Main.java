package org.oldo.wcb;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import org.oldo.wcb.vertx.RequestHandler;
import org.oldo.wcb.vertx.WebVerticle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final Handler<RoutingContext> handler = new RequestHandler();
        final Verticle webServer = new WebVerticle(9999, handler);

        final BlockingQueue<AsyncResult<String>> queue = new ArrayBlockingQueue<>(1);
        Vertx.vertx().deployVerticle(webServer, queue::offer);

        final AsyncResult<String> result = queue.take();
        if (result.failed()) {
            throw new RuntimeException(result.cause());
        }
    }

}
