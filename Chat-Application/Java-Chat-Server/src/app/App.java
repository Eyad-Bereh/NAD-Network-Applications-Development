package app;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {
        int PORT_NUMBER       = 45012;
        int THREAD_POOL_SIZE  = 10;

        if (args.length == 1) {
            PORT_NUMBER = Integer.parseInt(args[0]);
        }
        else if (args.length == 2) {
            THREAD_POOL_SIZE = Integer.parseInt(args[1]);
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        try {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    Runnable clientThread = new ClientThread(clientSocket);
                    pool.submit(clientThread);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                serverSocket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            pool.shutdown();
        }
    }
}