package chatroom.Client_v0;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		view.btnConnect.setOnAction( event -> {
			view.btnConnect.setDisable(true);
			String ipAddress = view.txtIPAdress.getText();
			int port = Integer.parseInt(view.txtPort.getText());
			String name = view.txtName.getText();
			model.connect(ipAddress, port, name);
		});
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
		view.btnSend.setOnAction( event -> model.sendMessage(view.txtMessage.getText()));
		
		model.newestMessage.addListener( (o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.txtTexts.appendText(newValue + "\n");
		} );
	}
}
