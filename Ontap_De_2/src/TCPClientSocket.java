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
            System.out.println("Successfully connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()));

            double a, b;
            while (true){
               try {
                   System.out.print("Nhap a: ");
                   a = new Scanner(System.in).nextDouble();
                   System.out.print("Nhap b: ");
                   b = new Scanner(System.in).nextDouble();
                   out.println(String.format("%f - %f", a, b));
                   out.flush();

                   String receive = in.readLine();
                   System.out.println("<Server>: " + receive);

                   in.close();
                   out.close();
                   sk.close();
               } catch (Exception e){
                   System.out.println("Invalid value");
               }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
