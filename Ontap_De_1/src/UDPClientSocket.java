import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientSocket {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            byte[] receiveData = new byte[1024];
            do {
                try {
                    System.out.print("Nhap 1 so: ");
                    Integer number = new Scanner(System.in).nextInt();
                    byte[] sendData = number.toString().getBytes();
                    InetAddress ipClient = InetAddress.getByName("localhost");
                    int portClient = 8888;
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipClient, portClient);

                    clientSocket.send(sendPacket);

                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);

                    String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("<Server>: " + message);

                    clientSocket.close();
                } catch (Exception e) {
                    System.out.println("Gia tri khong dung!");
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
