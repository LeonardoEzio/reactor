package leonardo.ezio.personal;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @Description : vertx web路由示例
 * @Author : LeonardoEzio
 * @Date: 2022-04-06 16:55
 */
public class VertxWebRouterDemo extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        // 创建HttpServer
        HttpServer server = vertx.createHttpServer();

        // 创建路由对象
        Router router = Router.router(vertx);

        // 监听/index地址
        router.route("/index").handler(request -> {
            request.response().end("INDEX SUCCESS");
        });
        router.route("/index2").handler(request -> {
            request.response().end("INDEX 2 SUCCESS");
        });

        // 把请求交给路由处理--------------------(1)
        server.requestHandler(router).listen(8888,ser->{
            if (ser.succeeded()){
                System.out.println("服务启动成功！！");
            }
        });

    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new VertxWebRouterDemo());
    }
}
