# zipkin-trace

Proof of concept showing how to add MDC traceId and spanId to logging, when using HTTP and gRPC between services.

Start a zipkin container (e.g. openzipkin/zipkin:latest) and note exposed ports.
Edit the application.yml files to point to the correct zipkin port (the lowest numbered port).

There are currently three calls that can be made:
1. http://localhost:8080/v1/a
2. http://localhost:8080/v1/b
3. http://localhost:8080/v1/c

With call /v1/a, service A calls service B via gRPC and service B calls service C via HTTP
With call /v1/b, service A calls service B via gRPC ans service B calls service C via gRPC
With call /v1/c, service A calls service C via HTTP

In the logging of each service A,B,C one should see the traceId/spanId logged between square brackets.

In the common directory you can find much of the configuration of Brave to make it quick for new services to include tracing, by autowiring.