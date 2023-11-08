import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerSocket {

    public static boolean checkKey(String key) {
        Character lowerC = Character.toLowerCase(key.charAt(0));
        if(lowerC == 'a' || lowerC == 'o' || lowerC == 'i' || lowerC == 'e' || lowerC == 'u') {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        try(DatagramSocket serverSocket = new DatagramSocket(8888) ) {
            do {
                System.out.println("Waiting for client send data to Server...");
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("Complete receive data from Client!");
                String message = new String(receiveData, 0, receivePacket.getLength());
                System.out.println("<Client>: " + message);

                String sendMessage;
                try {
                    double test = Double.parseDouble(message);
                    sendMessage = "Khong duoc nhap so";
                } catch (Exception e){
                    if (message.trim().length() != 1) {
                        sendMessage = "Gia tri khong dung!";
                    } else {
                        sendMessage = checkKey(message) ? "Nguyen am" : "Phu am";
                    }
                }
                byte[] sendData = sendMessage.getBytes();
                InetAddress ipClient = receivePacket.getAddress();
                int portClient = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipClient, portClient);

                serverSocket.send(sendPacket);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
