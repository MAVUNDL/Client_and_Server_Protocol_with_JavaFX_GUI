# Client_and_Server_Protocol_with_JavaFX_GUI
A client and server protocol using sockets. Include as graphical user interface built with JavaFX


# Server README Document
Overview
This server is designed to provide weather information to clients upon request. It runs on port 8888 and allows clients to make up to 4 requests per session.

# Server Startup
- To start the server, run the server application. The server will display "Ready for incoming connections..." when it is started.

# Client Connection
- Upon connection, the server responds with "HELLO - you may make 4 requests and I'll provide weather information". To start a session, the client sends a "START" message, and the server responds with "REQUEST or DONE".
  
# Request Format
- To submit a request, the client sends a message in the following format: "REQUEST [location]". The server analyzes the message and responds accordingly.

# Response Format
 - The server responds using the "0# [Weather Info]" message format. The response depends on the location requested:
   Johannesburg: "Clear Skies in Joburg"
- Durban: "Sunny and Warm in Durban"
- Cape Town: "Cool and Cloudy in Cape Town"
- Any other location: randomly responds with "No data available", "Please try again later", or "[Location] data outdated"

# Session Termination
- If the client sends a "DONE" message, the server responds with "0# GOOD BYE - [x] queries answered" and terminates the connection. The connection is also terminated after 4 requests.

# Testing
 - For testing purposes, you may use any suitable port number. Ensure the server is running and test with a client application.
