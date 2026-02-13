# Soon I know AOP

This is the code for my blog post.

TODO: Add link to the post.

## Backstory

I'm a consultant and I was in an interview with a potential fintech client. They asked me about AOP and how have I used it in the past and what do I know about it. I didn't really know so that part didn't go so well, but here we are learning what is AOP and what one can do with it.

## Description

Three nodes or services (service-a-api, service-b-lol and service-c-db). The things need to run locally in Docker at first and maybe later in Azure.

Services will be written in Kotlin and Spring Boot is used.

There are also Prometheus and Grafana to show the results.

### service-a-api

This is a simple API service that will be called and the sequence begins.

### service-b-lol

This is just a dummy service to add a bit more complexity to the system. Fintech systems are quite complex I suppose.

### service-c-db

This service just writes the transaction to db and the flow goes back to client though other services.

### Prometheus

Gathers the data (execution times and errors).

### Grafana

Shows the data from Prometheus in visual form.
