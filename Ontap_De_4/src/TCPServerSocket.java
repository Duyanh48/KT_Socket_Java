import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerSocket {
    public static int UCLN(int a, int b) {
        while (a * b != 0) {
            if(a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return a + b;
    }
    public static void main(String[] args) {
        try {
            ServerSocket sk = new ServerSocket(8888);
            System.out.println("Waiting for client connect...");
            do {
                Socket client_sk = sk.accept();
                System.out.println("Having connection from " + client_sk.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(client_sk.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client_sk.getOutputStream()));

                String receive = in.readLine();
                String[] arrInput = receive.split(" ", 0);
                for (String number : arrInput) {
                    System.out.println("<Client>: " + number);
                }

                int a = Integer.parseInt(arrInput[0].trim());
                int b = Integer.parseInt(arrInput[1].trim());
                out.println(UCLN(a, b));
                out.flush();

                out.close();
                in.close();
                client_sk.close();
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
