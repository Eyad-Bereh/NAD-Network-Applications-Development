import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DataRecievedEvent implements Runnable {
	private DataInputStream is;
	private Callback dataRecieved;
	private Callback serverDown;
	private boolean callbackIsset = false;
	
	public DataRecievedEvent(DataInputStream is) {
		this.is = is;
	}
	
	public void attachCallback(Callback dataRecieved, Callback serverDown) throws Exception {
		if (!callbackIsset) {
			this.dataRecieved = dataRecieved;
			this.serverDown = serverDown;
			callbackIsset = true;
		}
		else {
			throw new Exception("DataRecievedEvent.attachCallback: callback already attached.");
		}
	}
	
	@Override
	public void run() {
		try {
			byte[] bytes = new byte[1024];
			while (true) {
				bytes = new byte[1024];
				if (is.read(bytes) == -1) {
					serverDown.execute(null);
					break;
				}
				try {
					//Message message = new Gson().fromJson(new String(bytes).trim(), Message.class);
					Gson gson = new Gson();
					JsonReader jr = new JsonReader(new StringReader(new String(bytes)));
					jr.setLenient(true);
					Message message = gson.fromJson(jr, Message.class);
					dataRecieved.execute(message);
				}
				catch (Exception e) {
					e.printStackTrace();
					//System.out.println(new String(bytes).trim());
				}
				
			}
		}
		catch (IOException e) {}
	}
}
