package springboot.demo.system.websocketByNetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("logging", new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
        ch.pipeline().addLast("http-codec", new HttpServerCodec());//设置解码器
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());//用于大数据的分区传输
        ch.pipeline().addLast("handler", new NioWebSocketHandler());//自定义的业务handler

        // 以下为要支持wss所需处理
//        KeyStore ks = KeyStore.getInstance("JKS");
//        InputStream ksInputStream = new FileInputStream("/Users/liukun/ca/demo.liukun.com.keystore");
//        ks.load(ksInputStream, "123456".toCharArray());
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        kmf.init(ks, "123456".toCharArray());
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), null, null);
//        SSLEngine sslEngine = sslContext.createSSLEngine();
//        sslEngine.setUseClientMode(false);
//        sslEngine.setNeedClientAuth(false);
//        // 需把SslHandler添加在第一位
//        ch.pipeline().addFirst("ssl", new SslHandler(sslEngine));
    }
}
