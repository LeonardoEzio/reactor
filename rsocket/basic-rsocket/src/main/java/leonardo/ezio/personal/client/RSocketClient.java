package leonardo.ezio.personal.client;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

/**
 * @Description : RSocket客户端
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 11:23
 */
public class RSocketClient {

    public static void main(String[] args) {
        RSocket r = RSocketFactory.connect()
                .transport(TcpClientTransport.create("localhost", 7005))
                .start()
                .block();

        r.requestResponse(DefaultPayload.create("World"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .doFinally(signalType -> r.dispose())
                .then()
                .block();
    }
}
