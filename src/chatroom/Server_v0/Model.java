package chatroom.Server_v0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import chatroom.commons.ChatMsg;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	protected final ObservableList<Client> clients = FXCollections.observableArrayList();
	
	private final Logger logger = Logger.getLogger("");
	private ServerSocket listener;
	private volatile boolean stop = false;
	
	public void startServer(int port) {
		logger.info("Start server");
		try {
			listener = new ServerSocket(port, 10, null);
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (!stop) {
						try {
							Socket socket = listener.accept();
							Client client = new Client(Model.this, socket);
							clients.add(client);
						} catch (Exception e) {
							logger.info(e.toString());
						}
					}
				}
			};
			Thread t = new Thread(r, "ServerSocket");
			t.start();
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}
	
	public void stopServer() {
		logger.info("Stop all clients");
		for (Client c : clients) c.stop();

		logger.info("Stop server");
		stop = true;
		if (listener != null) {
			try {
				listener.close();
			} catch (IOException e) {
				// Uninteresting
			}
		}
	}
	
	public void broadcast(ChatMsg outMsg) {
		logger.info("Broadcasting message to clients");
		for (Client c : clients) {
			c.send(outMsg);
		}
	}
}