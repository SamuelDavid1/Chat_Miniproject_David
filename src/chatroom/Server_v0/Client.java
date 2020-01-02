package chatroom.Server_v0;

import java.io.IOException;
import java.net.Socket;

import chatroom.commons.ChatMsg;
import chatroom.commons.JoinMsg;
import chatroom.commons.Message;

public class Client {

	private Socket socket;
	private String name;
	private Model model;

	protected Client(Model model, Socket socket) {
		this.model = model;
		this.socket = socket;

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while(true) {
					Message msg = Message.receive(socket);
					if (msg instanceof ChatMsg) {				
						model.broadcast((ChatMsg) msg);
					} else if (msg instanceof JoinMsg) {
						Client.this.name = ((JoinMsg) msg).getName();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
	
	public void send(ChatMsg msg) {
		msg.send(socket);
	}

	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
		// Uninteresting
		}
	}
	
	@Override
	public String toString() {
		return name + ": " + socket.toString();
	}
}
