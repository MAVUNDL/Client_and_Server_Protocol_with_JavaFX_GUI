import CLIENT.Client_class;
import GUI.Application_GUI;
import SERVER.Server_class;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {

        Application_GUI gui = new Application_GUI();
        stage.setScene(gui.application_interface());
        stage.show();

        Thread server_thread = new Thread((Runnable) () -> {
            Server_class server = new Server_class();
            server.initiate_connection();
            System.out.println("Server started");
        }, " server");
        server_thread.start();

        Thread.sleep(1000);

        Thread client_thread = new Thread((Runnable) () -> {
            Client_class client = new Client_class(gui);
            client.initiate_communication();
            System.out.println("Client started");
        }, "client");
        client_thread.start();
    }
}
