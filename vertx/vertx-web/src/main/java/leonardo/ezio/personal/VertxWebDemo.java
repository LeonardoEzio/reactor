package leonardo.ezio.personal;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * @Description : 基于Vertx实现的Web示例
 * @Author : LeonardoEzio
 * @Date: 2022-04-06 15:52
 */
public class VertxWebDemo extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        //通过 vertx 创建一个http服务
        HttpServer httpServer = vertx.createHttpServer();

        httpServer.requestHandler(request -> {
            // 获取到response对象
            HttpServerResponse response = request.response();

            // 设置响应头
            response.putHeader("Content-type", "text/html;charset=utf-8");

            // 响应数据
            response.end("Hello World");
        });

        httpServer.listen(8888,server ->{
            if (server.succeeded()){
                System.out.println("服务启动成功");
            }
            if (server.failed()){
                System.out.println("服务启动失败");
            }
        });

    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new VertxWebDemo());
    }
}
