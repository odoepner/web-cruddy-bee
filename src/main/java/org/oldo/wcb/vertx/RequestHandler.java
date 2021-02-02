package org.oldo.wcb.vertx;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

public class RequestHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext ctx) {
        final HttpServerResponse response = ctx.response();

        response.setChunked(true);
        response.putHeader("content-type", "text/plain");

        response.write("ctx.request().params()\n");
        for (Map.Entry<String, String> entry : ctx.request().params()) {
            response.write("key:" + entry.getKey() + ", value:" + entry.getValue() + "\n");
        }

        response.write("ctx.request().formAttributes()\n");
        for (Map.Entry<String, String> entry : ctx.request().formAttributes()) {
            response.write("key:" + entry.getKey() + ", value:" + entry.getValue() + "\n");
        }

        response.end();
    }
}
