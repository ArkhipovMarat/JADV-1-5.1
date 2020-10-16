import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", PORT);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.print("Enter fibbo number: ");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) break;
                System.out.println("Server: " + in.readLine());
            }
        }
    }
}
