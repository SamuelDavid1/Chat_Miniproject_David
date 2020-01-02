package chatroom.Server_v0;

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
	
	Label lblPort = new Label("Port");
	TextField txtPort = new TextField();
	Button btnStart = new Button("Start");
	
	TextArea txtClients = new TextArea();
	
	
	public View(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
		
		BorderPane root = new BorderPane();
		root.setId("root");
		
		HBox topBox = new HBox();
		topBox.setId("TopBox");
		topBox.getChildren().addAll(lblPort, txtPort, btnStart);
		root.setTop(topBox);
		txtPort.setId("Port");
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(txtClients);
		root.setCenter(scrollPane);
		txtClients.setWrapText(true);
	
		Scene scene = new Scene(root, 850, 900);
		scene.getStylesheets().add(getClass().getResource("Server.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Server");
	}
	
	protected void start() {
		stage.show();
	}
	
	public Stage getStage() {
        return stage;
    }
	
	public void stop() {
        stage.hide();
    }
	
	protected void updateClients() {
		StringBuffer sb = new StringBuffer();
		for (Client c : model.clients) {
			sb.append(c.toString());
			sb.append("\n");
		}
		txtClients.setText(sb.toString());
	}
}