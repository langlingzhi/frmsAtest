package com.nuanshui.frms.test.utils.http;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * https不验证证书
 * Created by xingshi on 2017/09/26.
 */
@Slf4j
public class SslContextUtils {
    private TrustManager trustAllManager;
    SSLContext sslcontext;
    HostnameVerifier allHostsValid;

    private static SslContextUtils instance = new SslContextUtils();

    private SslContextUtils() {
        initContext();
    }

    public static SslContextUtils createInstance() {
        return instance;
    }

    private void initContext() {
        trustAllManager = new X509TrustManager() {

            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) {
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        };
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{trustAllManager}, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("", e);
        }
        // Create all-trusting host name verifier
        allHostsValid = (hostname, session) -> true;
    }

    public void initHttpsConnect(HttpsURLConnection httpsConn) {
        httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());
        httpsConn.setHostnameVerifier(allHostsValid);
    }
}
