import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientSocket {
    public static void main(String[] args) {
        try {
            byte[] receiveData = new byte[1024];
            DatagramSocket clientSocket = new DatagramSocket();

            System.out.println("Enter a character: ");
            String sendMessage = new Scanner(System.in).nextLine();
            byte[] sendData = sendMessage.getBytes();
            InetAddress ipServer = InetAddress.getByName("localhost");
            int portServer = 8888;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServer, portServer);

            clientSocket.send(sendPacket);
            System.out.println("Complete sending data to Server!");

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            System.out.println("Complete receiving data from Server!");
            String message = new String(receiveData, 0, receivePacket.getLength());
            System.out.println("<Server>: " + message);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
