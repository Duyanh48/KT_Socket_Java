import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerSocket {
    public static String giaiPhuongTrinhBacNhat(double a, double b) {
        if (b == 0 && a == 0) {
            return "Phuong trinh co vo so nghiem!";
        } else if (a == 0) {
            return "Phuong trinh co vo nghiem!";
        } else {
            return "Phuong trinh co nghiem: x = " + (-b / a);
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket sk = new ServerSocket(8888);
            System.out.println("Waiting connection...");

            while (true) {
                Socket client_sk = sk.accept();
                System.out.println("Have a connection from: " + client_sk.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(client_sk.getInputStream()));
                PrintWriter out  = new PrintWriter(new OutputStreamWriter(client_sk.getOutputStream()));

                String receive = in.readLine();
                System.out.println("<Client>: " + receive);

                double a, b;
                a = Double.parseDouble(receive.split("-")[0].trim());
                b = Double.parseDouble(receive.split("-")[1].trim());
                out.println(giaiPhuongTrinhBacNhat(a, b));
                out.flush();

                in.close();
                out.close();
                client_sk.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
