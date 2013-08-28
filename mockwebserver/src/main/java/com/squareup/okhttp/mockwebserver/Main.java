package com.squareup.okhttp.mockwebserver;

import com.squareup.okhttp.internal.SslContextBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;

public class Main {
  private Main() {
  }

  public static void main(String[] args) throws IOException, GeneralSecurityException {
    SSLContext sslContext = new SslContextBuilder("jwilson.local").build();

    MockWebServer server = new MockWebServer();
    server.useHttps(sslContext.getSocketFactory(), false);
    server.setDispatcher(new Dispatcher() {
      @Override public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        return new MockResponse().setBody("foo");
      }
    });
    server.play(3242);
  }
}
