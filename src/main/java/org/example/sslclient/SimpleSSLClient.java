package org.example.sslclient;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Usage with proxy :
 * <br/>
 * <br/>
 *
 * <code>java -Djavax.net.debug=all \<br/>
 * -cp ssl-client-1.0-SNAPSHOT-jar-with-dependencies.jar \<br/>
 * org.example.sslclient.SimpleSSLClient \<br/>
 * &lt;proxyHost : eg. proxy.dom&gt; &lt;proxyPort : eg. 80&gt; &lt;http|https&gt; &lt;host, eg: repo.itextsupport.com&gt; &gt; ssl.log 2&gt;&1
 * </code>
 *
 *
 * <br/>
 * <br/>
 * Usage without proxy :
 * <br/>
 * <br/>
 *
 * <code>java -Djavax.net.debug=all \<br/>
 * -cp ssl-client-1.0-SNAPSHOT-jar-with-dependencies.jar \<br/>
 * org.example.sslclient.SimpleSSLClient \<br/>
 * noproxy 0 &lt;http|https&gt; &lt;host, eg: repo.itextsupport.com&gt; &gt; ssl.log 2&gt;&1
 * </code>
 */
public class SimpleSSLClient {

    private static final String NO_PROXY = "noproxy";

    public static void main(String[] args) throws IOException, URISyntaxException {

        String proxyHost = args[0];
        int proxyPort = Integer.parseInt(args[1]);

        String scheme = args[2];
        String host = args[3];

        DefaultProxyRoutePlanner proxy = null;
        if (!proxyHost.equals(NO_PROXY)) {
            proxy = new DefaultProxyRoutePlanner(new HttpHost(proxyHost, proxyPort));
        }

        HttpClientBuilder httpBuilder = HttpClients.custom();
        if (proxy != null) {
            httpBuilder.setRoutePlanner(proxy);
            System.out.println("Connecting to " + scheme + "://" + host + " (with proxy " + proxyHost + ":" + proxyPort + ")");
        } else {
            System.out.println("Connecting to " + scheme + "://" + host + " (without proxy)");
        }

        URI uri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .build();
        HttpGet httpget = new HttpGet(uri);
        httpBuilder.build().execute(httpget);
    }
}
