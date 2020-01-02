package chatroom.Client_v0;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View {
	protected Stage stage;
	private Model model;
	
	Label lblIPAdress = new Label("IP Adress");
	TextField txtIPAdress = new TextField();
	Label lblPort = new Label("Port");
	TextField txtPort = new TextField();
	Label lblName = new Label("Name");
	TextField txtName = new TextField();
	Button btnConnect = new Button("Connect");
	
	TextArea txtTexts = new TextArea();
	
	TextField txtMessage = new TextField();
	Button btnSend = new Button("Send");
	
	public View(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
		
		BorderPane root = new BorderPane();
		root.setId("root");
		
		HBox topBox = new HBox();
		topBox.setId("TopBox");
		topBox.getChildren().addAll(lblIPAdress, txtIPAdress, lblPort, txtPort, lblName, txtName, btnConnect);
		root.setTop(topBox);
		txtIPAdress.setId("IP");
        txtPort.setId("Port");
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(txtTexts);
		root.setCenter(scrollPane);
		txtTexts.setWrapText(true);
		
		HBox bottomBox = new HBox();
		bottomBox.setId("BottomBox");
		bottomBox.getChildren().addAll(txtMessage, btnSend);
		root.setBottom(bottomBox);
	
		Scene scene = new Scene(root, 850, 900);
		scene.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Client");
	}
	
	protected void start() {
		stage.show();
	}
	
	public void stop() {
        stage.hide();
    }
	
	public Stage getStage() {
        return stage;
    }
}