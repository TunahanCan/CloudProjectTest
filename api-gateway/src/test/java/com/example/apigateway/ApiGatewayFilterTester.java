package com.example.apigateway;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.apigateway.filter.AuthenticationFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
public class ApiGatewayFilterTester {

    /*
    @Autowired
    private CustomGatewayFilterFactory factory;

    private ServerWebExchange exchange;
    private GatewayFilterChain filterChain = mock(GatewayFilterChain.class);
    private ArgumentCaptor<ServerWebExchange> captor = ArgumentCaptor.forClass(ServerWebExchange.class);

    @BeforeEach
    void setup() {
        when(filterChain.filter(captor.capture())).thenReturn(Mono.empty());
    }

    @Test
    void customTest() {
        MockServerHttpRequest request = MockServerHttpRequest.get(DUMMY_URL).build();
        exchange = MockServerWebExchange.from(request);
        GatewayFilter filter = factory.apply(YOUR_FACTORY_CONFIG);
        filter.filter(exchange, filterChain);
        // filter.filter(exchange, filterChain).block(); if you have any reactive methods

        ServerHttpRequest actualRequest = captor.getValue().getRequest();

        // Now you can assert anything in the actualRequest
        assertEquals(request, actualRequest);
    }*/


    @Test
    public void testDeneme(){
        assertEquals(1, 1);
    }

}
