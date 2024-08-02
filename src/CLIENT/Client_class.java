package CLIENT;

import GUI.Application_GUI;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class Client_class {
    private Application_GUI gui;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client_class(Application_GUI gui) {
        this.gui = gui;
        try {
            // create a socket
            socket = new Socket("localhost", 8888);
            // create the streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // true for auto-flush
        } catch (IOException e) {
            System.out.println("Client : " + e.getMessage());
        }
    }

    public void initiate_communication()
    {
        Thread client_thread = new Thread(() -> {
            try {
                if (socket.isConnected()) {
                    // first get what the server says after connecting
                    String server_str = in.readLine();
                    Platform.runLater(() -> gui.messageBlock.appendText("server: " + server_str + "\n"));

                    while (true)
                    {
                        // get communication from server
                        String message_from_server = in.readLine();

                        // update the textarea
                        if (message_from_server != null)
                        {
                            Platform.runLater(() -> gui.messageBlock.appendText("server: " + message_from_server + "\n"));
                        }
                        // check conditions to break the loop
                        if (message_from_server != null && (message_from_server.equals("GOOD BYE - [4] queries answered") || message_from_server.contains("0# GOOD BYE")))
                        {
                            break;
                        }
                    }
                } else {
                    System.out.println("Server is offline");
                }
            } catch (IOException e) {
                System.out.println("Client IOException: " + e.getMessage());
            } finally {
                try {
                    // then close the streams and socket
                    if (socket != null)
                        socket.close();
                    if (in != null)
                        in.close();
                    if (out != null)
                        out.close();
                } catch (IOException e) {
                    System.out.println("From client closing streams : " + e.getMessage());
                }
            }
        }, "client thread");
        client_thread.start();

        // Handle user input
        gui.sendButton.setOnAction(event -> {
            String query = gui.client_message.getText();
            if (!query.isEmpty()) {
                out.println(query);
                out.flush();
                Platform.runLater(() -> {
                    gui.messageBlock.appendText("Client: " + query + "\n");
                    gui.client_message.clear();
                });
            }
        });
    }
}
