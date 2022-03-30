import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class UDPServer 
{
    public static void main(String[] args) 
    {
        DatagramSocket aSocket = null;
        int port;
    	String IPAddress;

        String message;
        try{
        	IPAddress = args[0];
        	port = Integer.valueOf(args[1]);
        	
    		InetAddress aHost = InetAddress.getByName(IPAddress);
            aSocket = new DatagramSocket(port, aHost);
            while(true){
            	byte buffer[] = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println("Packet received! ");
                byte[] byteArray = request.getData();
                String str = new String(byteArray, StandardCharsets.UTF_8);
                System.out.println("Message sent from Client: " + str);
                message = str.toUpperCase();
                byteArray = message.getBytes();
                DatagramPacket reply = new DatagramPacket(byteArray,request.getLength(), request.getAddress(),request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());}
        catch (IOException e) {System.out.println("IO: " + e.getMessage());}
        finally{if (aSocket != null) aSocket.close();}
    }
}
