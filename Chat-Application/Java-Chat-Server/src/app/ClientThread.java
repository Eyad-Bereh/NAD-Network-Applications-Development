package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import com.google.gson.Gson;

class ClientThread implements Runnable {
    private static final int MESSAGE_SIZE = 1024;
    private static ArrayList<Socket> clients = new ArrayList<Socket>();

    private Socket clientSocket;
    
    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        clients.add(clientSocket);
        Message message = new Message();
        message.setProperty("sender", "Server");
        message.setProperty("content", "Let's welcome [" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "] !");
        message.setProperty("timestamp", LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(3)));
        message.setType(Message.NORMAL_MESSAGE);

        try {
            updateClients(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Client: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort()
                + " has connected at: " + LocalDateTime.now());
    }

    public void updateClients(Message msg) throws IOException {
        byte[] chatMessage = new Gson().toJson(msg, Message.class).getBytes();
        
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).isClosed()) {
                clients.remove(i);
                continue;
            }
            DataOutputStream os = new DataOutputStream(clients.get(i).getOutputStream());
            os.write(chatMessage);
            os.flush();
        }
    }

    public void updateUsersCount() throws IOException {
        Message usersCount = new Message();
        usersCount.setType(Message.USERS_COUNT_MESSAGE);
        usersCount.setProperty("count", clients.size());

        byte[] usersCountMessage = new Gson().toJson(usersCount, Message.class).getBytes();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).isClosed()) {
                clients.remove(i);
                continue;
            }
            DataOutputStream os = new DataOutputStream(clients.get(i).getOutputStream());
            os.write(usersCountMessage);
            os.flush();
        }
    }

    public void run() {
        try {
            DataInputStream is;
            byte[] bytes = new byte[MESSAGE_SIZE];
            Message incomingMessage;
            Message outgoingMessage;

            while (true) {
                updateUsersCount();
                is = new DataInputStream(clientSocket.getInputStream());
                incomingMessage = new Message();
                outgoingMessage = new Message();

                if (is.read(bytes) == -1) {
                    break;
                }

                String validJSON = new String(bytes).trim();
                System.out.println("Recieved: (" + validJSON + ") from client: " + clientSocket.getInetAddress() + ":"
                        + clientSocket.getPort() + " at: " + LocalDateTime.now());

                incomingMessage = new Gson().fromJson(validJSON, Message.class);

                String sender = (String)incomingMessage.getProperty("sender");
                String content = (String)incomingMessage.getProperty("content"); 
                //long timestamp = (long)incomingMessage.getProperty("timestamp");
                outgoingMessage.setProperty("sender", sender);
                outgoingMessage.setProperty("content", content);
                outgoingMessage.setProperty("timestamp", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                outgoingMessage.setType(Message.NORMAL_MESSAGE);

                updateClients(outgoingMessage);
                bytes = new byte[MESSAGE_SIZE];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
