package org.test;

import io.vertx.core.AbstractVerticle;

/**
 * Created by yzw on 7/27/16.
 */
public class TestVert extends AbstractVerticle {
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {

            System.out.println("get version");
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(8080);
    }

    public static void main(String[] args) {
        Runner.runExample(TestVert.class);
    }
}
