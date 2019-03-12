package com.nuanshui.frms.test;
//com.nuanshui.frms.test.csservice.impl.FrmsapiServiceImpl

import com.nuanshui.frms.test.utils.http.SslContextUtils;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableEurekaClient
//for job config ,controller and jdbctemplate
@ComponentScan({"com.nuanshui.frms.test.config", "com.nuanshui.frms.test.controller",
        "com.nuanshui.frms.test.repository", "com.nuanshui.frms.test.aop",
        "com.nuanshui.frms.test.service", "com.nuanshui.frms.test.apiservice",
        "com.nuanshui.frms.test.appservice", "com.nuanshui.frms.test.csservice",
        "com.nuanshui.frms.test.asyncService"
        })
@MapperScan({"com.nuanshui.frms.test.mapper","com.nuanshui.frms.test.csmapper",
        "com.nuanshui.frms.test.appmapper",
        })
@EnableJpaAuditing
@EntityScan(value = "com.nuanshui.frms.test.entity")
@EnableJpaRepositories("com.nuanshui.frms.test.repository.example")
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class TestApplication extends SpringBootServletInitializer {

    @Value("${resttemplate.conectTimeout}")
    private int connectTimeout;
    @Value("${resttemplate.readTimeout}")
    private int readTimeout;
    @Value("${resttemplate.proxy.host}")
    private String proxyHost;
    @Value("${resttemplate.proxy.port}")
    private int proxyPort;

    @Value("${feign.connectTimeoutMillis}")
    private String connectTimeoutMillis;

    @Value("${feign.readTimeoutMillis}")
    private String readTimeoutMillis;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(/**connectTimeoutMillis**/new Integer(connectTimeoutMillis), /** readTimeoutMillis **/new Integer(readTimeoutMillis));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启lll动类
        return builder.sources(new Class[] {TestApplication.class});
    }
    @Bean
    public Proxy proxy() {
        if (StringUtils.isNotEmpty(proxyHost)) {
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        }
        return null;
    }

    /**
     * 不安全的，在HTTPS时会忽略证书验证，只能在个别情况下使用，
     *
     * @return rest template
     */
    @Bean(name = "unsafeRestTemplate")
    public RestTemplate unsafeRestTemplate(Proxy proxy) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                if (connection instanceof HttpsURLConnection) {
                    SslContextUtils.createInstance().initHttpsConnect((HttpsURLConnection) connection);
                }
                super.prepareConnection(connection, httpMethod);
            }
        };
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        //代理
        if (proxy != null) {
            requestFactory.setProxy(proxy);
        }
        RestTemplate restTemplate = getBaseRestTemplate(requestFactory);
        return restTemplate;
    }
    @Bean(name={"restTemplate"})
    public RestTemplate productRestTemplate()
    {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(this.readTimeout);
        requestFactory.setConnectTimeout(this.connectTimeout);
        return getBaseRestTemplate(requestFactory);
    }
    /**
     * 生成基本的Rest template
     *
     * @param requestFactory 请求的相关参数
     * @return rest template
     */
    private RestTemplate getBaseRestTemplate(SimpleClientHttpRequestFactory requestFactory) {
        RestTemplate res = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converterList = res.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (item.getClass() == StringHttpMessageConverter.class) {
                converterTarget = item;
                break;
            }
        }

        if (converterTarget != null) {
            converterList.remove(converterTarget);
        }
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(converter);
        return res;
    }
}
