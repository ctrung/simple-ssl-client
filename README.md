# simple-ssl-client

Mini client SSL Java pour **débugger la connectivité SSL** 
(eg. handshake, chaine de certificats, etc...).

D'après une idée de gratinierer sur 
https://stackoverflow.com/questions/3220419/openssl-s-client-using-a-proxy comme alternative 
à openssl v<1.1.0 qui ne supporte pas l'option -proxy.

Par défaut, gère aussi les redirections (HTTP 302).

Testé sous java 8. 

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
