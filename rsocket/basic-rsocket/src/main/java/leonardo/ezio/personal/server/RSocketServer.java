package leonardo.ezio.personal.server;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

/**
 * @Description : RSocket服务端
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 11:12
 */
public class RSocketServer {

    public static void main(String[] args) throws Exception{
        RSocketFactory.receive()
                .acceptor(((setup, sendingSocket) ->
                        Mono.just(
                                new AbstractRSocket() {
                                    @Override
                                    public Mono<Payload> requestResponse(Payload payload) {
                                        return Mono.just(DefaultPayload.create("hello:" + payload.getDataUtf8()));
                                    }
                                }
                        )))
                .transport(TcpServerTransport.create("localhost", 7005))
                .start()
                .subscribe();
        Thread.sleep(1000000000000L);
    }
}
