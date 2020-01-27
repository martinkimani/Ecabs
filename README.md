# Ecabs
this project implements exchange to exchange binding and routing in rabbitMQ in java

I used factory design pattern to enable select the correct bean to inject because the project can save data in the database by either directly from REST API to the repository and also through a Pub/Sub setup which includes two exchanges and four queues and messages are rerouted across the two exchanges and published to two queues each per message. i.e a messages queue that only prints alll messages and another bound to the other exchange where a consumer consumes it and calls the db repositories.

there is an angular front end client check it out https://github.com/martinkimani/EcabsFrontend. its just a CRUD that implements these APIs.
