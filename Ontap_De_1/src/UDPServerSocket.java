import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerSocket {
    public static String convertNumberToString(int a) {
        switch (a) {
            case 1:
                return "mot";
            case 2:
                return "hai";
            case 3:
                return "ba";
            case 4:
                return "bon";
            case 5:
                return "nam";
            case 6:
                return "sau";
            case 7:
                return "bay";
            case 8:
                return "tam";
            case 9:
                return "chin";
            default:
                return "Gia tri khong dung!";
        }
    }
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(8888)) {
            System.out.println("Waiting for client send data...");
            do {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("Complete receiving data from client!");
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("<Client>: " + message);

                InetAddress ipServer = receivePacket.getAddress();
                int port = receivePacket.getPort();
                try {
                    int a = Integer.parseInt(message);
                    String sendMessage = convertNumberToString(a);

                    byte[] sendData = sendMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServer, port);

                    serverSocket.send(sendPacket);
                    System.out.println("Complete sending message to Client!");
                } catch (NumberFormatException ex) {
                    System.out.println("Gia tri khong dung!");
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
