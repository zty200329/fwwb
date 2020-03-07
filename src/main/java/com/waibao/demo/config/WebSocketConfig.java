package com.waibao.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zty
 * @Date 2020/3/6 下午8:49
 * @Description:
 * @EnableWebSocketMessageBroker ： 配置了WebSocket，还配置了基于代理的STOMP消息
 * 重载registerStompEndpoints方法：注册STOMP端点，即WebSocket客户端需要连接到WebSocket握手端点。可以理解成信息交互中心。这是一个端点，客户端在订阅或发布消息到目的地路径前，要连接该端点，才能接收或发送消息
 * 重载configureMessageBroker方法：设置应用程序的目的地前缀，当以应用程序为目的地的消息将会直接路由到带@MessageMapoping注解的控制器方法
 */
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


//实现接口来配置Websocket请求的路径和拦截器。
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
