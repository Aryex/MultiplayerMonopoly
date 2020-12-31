package GameModel;

import UI.GameBoardUi;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameHost extends Game {
    ExecutorService pool;
    Server server;
    Stage window;
    Scene returnToScene;
    GameBoardUi Ui;

    public GameHost(GameBoardUi ui, Stage window, Scene returnToScene) {
        this.Ui = ui;
        this.pool = Executors.newFixedThreadPool(20);
        this.server = new Server(7070);
        this.pool.execute(server);

        ui.onBackBtnClicked(e->{server.shutdown();window.setScene(returnToScene);});

    }

    private class Server extends Thread {
        private ServerSocket serverSocket;
        private int port;
        List<ClientHandler> clientHandlerList;

        public Server(int port) {
            this.port = port;
        }

        public void run() {
            System.out.println("Host running...");
            int count = 0;
            try {
                serverSocket = new ServerSocket(port);
                while (true) {
                    new ClientHandler(serverSocket.accept()).start();
                    System.out.println("Client number " + count++);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("finally");
                shutdown();
            }
        }

        public void shutdown() {
            try {
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * A clientHandler thread receives a socket from which it communicate with the client
         */
        private class ClientHandler extends Thread {
            private Socket clientSocket;
            private PrintWriter out;
            private BufferedReader in;

            public ClientHandler(Socket socket) {
                this.clientSocket = socket;
            }

            public void run() {
                String inputLine;
                try {
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Message: " + inputLine);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    shutdown();
                }
            }

            void shutdown() {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
