# Assignment
For this assignment, you will design a chat server and client!  Pay careful attention to the protocol.

- Chat Server
  - Will accept connections from clients on port 5190
  - Will relay anything sent to the server to all clients (including the one who originally sent the message), with the sender's name prepended to the message. 
  - Clients, upon connecting, have to announce their username
  - Client username should NOT be forwarded to the other clients
  - The server should not introduce itself to the client

- Chat Client
  - Will have a top box which is displays all the messages from the clients
  - Will have a bottom input which will allow text to be written to the server 
  - Will also include a "SEND" button. 
  - Upon starting the client, you should ask which server to connect to and what username to use. 
  - When the client connects to the server it sends the username as the first message. 
  - After the first message is sent, anything received from the server is displayed in the top box and anything written in the bottom box (after the send button is pushed) is sent to the server.
  
# Demo
Main code files are located at [/src/com/example/hw4_chat_server](https://github.com/mkarroqe/Java-CS3913/tree/master/HW4_Chat_Server/src/com/example/hw4_chat_server)

![](demo/final-demo.gif)
