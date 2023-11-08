import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientSocket {
    public static void main(String[] args) {
        try {
            Socket sk = new Socket("localhost", 8888);
            System.out.println("Complete connected to Server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()));

            try {
                System.out.print("Nhap so nguyen a: ");
                int a = new Scanner(System.in).nextInt();
                System.out.print("Nhap so nguyen b: ");
                int b = new Scanner(System.in).nextInt();

                out.println(a + " " + b);
                out.flush();

                String receive = in.readLine();
                System.out.println("<Server>: " + receive);
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
                out.close();
                in.close();
                sk.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
