# simple-ssl-client

Mini client SSL en Java pour débugger la connectivité SSL (eg. handshake, chaine de certificats, etc...). 

Testé en java 8. Par défaut, gère aussi les redirections (HTTP 302).

## Build

mvn clean compile assembly:single

## Usage

````shell

java -Djavax.net.debug=all \
     -cp <ssl-client.jar> \
     org.example.sslclient.SimpleSSLClient \
     <proxyHost> \
     <proxyPort> \
     <protocol, http|https> \
     <host>
````

Exemples 
````shell

# avec proxy

java -Djavax.net.debug=all \
     -cp ssl-client-1.0-SNAPSHOT-jar-with-dependencies.jar \
     org.example.sslclient.SimpleSSLClient \
     proxy.intra.domad 8080 \
     https repo.itextsupport.com/releases/com/itextpdf/typography/3.0.1/typography-3.0.1.jar

# sans proxy

java -Djavax.net.debug=all \
     -cp ssl-client-1.0-SNAPSHOT-jar-with-dependencies.jar \
     org.example.sslclient.SimpleSSLClient \
     noproxy 0 \
     https repo.itextsupport.com/releases/com/itextpdf/typography/3.0.1/typography-3.0.1.jar


````
