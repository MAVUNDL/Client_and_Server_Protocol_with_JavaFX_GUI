package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Application_GUI
{
    public  TextField client_message;
    public  TextArea messageBlock;
    public  Button sendButton;

    public Application_GUI()
    {
        messageBlock = new TextArea();
        client_message = new TextField();
        sendButton = new Button("Send");
    }

    private VBox header_and_information()
    {
        // creating Headers and footers
        VBox layout = new VBox();
        // application name
        Label application_name = new Label("Simple weather information server");
        application_name.setUnderline(true);
        application_name.setFont(new Font("Arial", 25));
        // information about the application
        Text information = new Text();
        String info = "This server uses a custom protocol to provide weather information based on requests sent to it";
        information.setText(info);
        information.setFont(Font.font("Arial", 18));

        layout.getChildren().addAll(application_name, information);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(35);

        return layout;
    }

    private TextArea chatBox()
    {
        messageBlock.setEditable(false);
        messageBlock.setWrapText(true);
        messageBlock.setMinHeight(350);
        messageBlock.setMaxWidth(700);

        return messageBlock;
    }

    private VBox the_chat()
    {
        VBox chat = new VBox();
        //
        client_message.setMinWidth(300);
        client_message.setMinHeight(40);
        //
        HBox horizontal = new HBox();
        horizontal.getChildren().addAll(new Label("Command: "),client_message, sendButton);
        horizontal.setSpacing(30);
        horizontal.setAlignment(Pos.CENTER);
        //
        chat.getChildren().addAll(chatBox(), horizontal);
        chat.setAlignment(Pos.CENTER);
        chat.setSpacing(30);

        return chat;
    }

    public Scene application_interface()
    {
        // root layout node
        VBox root = new VBox();
        // add all nodes
        root.getChildren().addAll(header_and_information(), the_chat());
        root.setSpacing(30);

        return new Scene(root, 1000, 600);
    }

}
