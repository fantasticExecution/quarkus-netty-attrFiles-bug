# quarkus-netty-attrfiles-bug

This quarkus project is for showcasing a possible bug in quarkus/netty regarding multipart http-requests.

It seems, for each part of a multipart request, an Attr_ temp file is created and not deleted properly after the request is done.

# Analyzing the bug
1) Check your temp dir for Attr_ files, e.g. `ls /tmp/ | grep Attr_`
2) Execute the quarkus test that will execute a http multipart request `./mvn test`
3) Check you temp dir again, more Attr_ exist even if you jvm is shut down completely