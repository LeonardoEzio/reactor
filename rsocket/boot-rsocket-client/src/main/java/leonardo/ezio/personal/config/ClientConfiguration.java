package leonardo.ezio.personal.config;

import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import java.net.InetSocketAddress;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 14:22
 */
@Configuration
public class ClientConfiguration {

//    @Bean
//    public RSocket rSocket(){
//        return RSocketFactory
//                .connect()
//                .mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
//                .frameDecoder(PayloadDecoder.ZERO_COPY)
//                .transport(TcpClientTransport.create(7000))
//                .start()
//                .block();
//    }
//
//    @Bean
//    RSocketRequester rSocketRequester(RSocketStrategies strategies){
//        return RSocketRequester.wrap(rSocket(), MimeTypeUtils.ALL,MimeTypeUtils.ALL, strategies);
//    }

    /**======================== 上述两种配置无法使用 ========================*/

    @Bean
    RSocketRequester rSocketRequester(RSocketStrategies strategies) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",7000);
        return RSocketRequester.builder()
                .rsocketFactory(factory -> factory
                        .dataMimeType(MimeTypeUtils.ALL_VALUE)
                        .frameDecoder(PayloadDecoder.ZERO_COPY))
                .rsocketStrategies(strategies)
                .connect(TcpClientTransport.create(address))
                .retry().block();
    }
}
