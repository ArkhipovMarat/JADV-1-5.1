import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(PORT);

        while (!serverSocket.isClosed()) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) break;
                    out.printf("fibonachi[%s] = %s%n", line, fibo(Long.parseLong(line)));
                }

                serverSocket.close();

            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }

    public static long fibo(long number) {
        int x = 0;
        int y = 1;
        for (int i = 0; i < number; i++) {
            y = x + y;
            x = y - x;
        }
        return y;
    }
}
