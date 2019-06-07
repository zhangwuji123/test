package com.cloud.operation.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import com.cloud.operation.core.utils.ConstantUtil;

public class WebSocketHandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {
 
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, 
    		ServerHttpResponse response, 
    		WebSocketHandler wsHandler, 
    		Map<String, Object> attributes) throws Exception {
    	if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")){  
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");  
        } 
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute(ConstantUtil.SESSION_USERNAME);
                attributes.put(ConstantUtil.WEBSOCKET_USERNAME,userName);
            }
        }
        return true;
    }
 
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
 
    }
}