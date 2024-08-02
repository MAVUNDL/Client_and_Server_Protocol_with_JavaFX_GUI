package SERVER;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_class
{
    private int query_counter = 0;
    private ServerSocket server = null;

    public Server_class()
    {
        try
        {
            // initialize server
            server = new ServerSocket(8888);
            System.out.println("Server started on port 8888");
        }
        catch (IOException e)
        {
            System.out.println("Server could not be created");
        }
    }

    public void initiate_connection()
    {
        // ensuring the server is always listening for connections
        while (true)
        {
            try
            {
                Socket serverSoc = server.accept();
                System.out.println("Client connected");
                Thread server_thread = new Thread(() -> handle_client(serverSoc), "Server_thread");
                server_thread.start();
            }
            catch (IOException e)
            {
                System.out.println("Server I/O Exception: " + e.getMessage());
            }
        }
    }

    private void handle_client(Socket serverSoc)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(serverSoc.getInputStream()));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(serverSoc.getOutputStream()), true))
        {

            // indication message that the server is started
            writer.println("Ready for incoming connections...");

            while (true)
            {
                // get input from client
                String message = reader.readLine();
                if (message == null || message.equals("DONE"))
                {
                    writer.println("“0# GOOD BYE - [" + query_counter + "] queries answered");
                    break;
                }
                System.out.println("Client: " + message);
                // communicate with client
                communicate_with_client(writer, message);
                // if the number of queries reach 4, close the connection
                if (query_counter == 4)
                {
                    writer.println("GOOD BYE - [" + query_counter + "] queries answered");
                    break;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Server I/O Exception: " + e.getMessage());
        }
        finally
        {
            try
            {
                serverSoc.close();
            }
            catch (IOException e)
            {
                System.out.println("Failed to close socket: " + e.getMessage());
            }
        }
    }

    public void communicate_with_client(PrintWriter writer, String message)
    {
        if (message.equals("START"))
        {
            writer.println("REQUEST or DONE");
            writer.flush();
        }
        else if (message.contains("[Johannesburg]"))
        {
            writer.println("0# [Clear Skies in Joburg]");
            writer.flush();
            query_counter++;
        }
        else if (message.contains("[Durban]"))
        {
            writer.println("0# [Sunny and Warm in Durban]");
            writer.flush();
            query_counter++;
        }
        else if (message.contains("[Cape Town]"))
        {
            writer.println("0# [Cool and Cloudy in Cape Town]");
            writer.flush();
            query_counter++;
        }
        else
        {
            writer.println("No data available, Please try again later");
        }
    }
}
