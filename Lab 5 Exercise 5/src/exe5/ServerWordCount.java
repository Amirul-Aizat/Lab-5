package exe5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerWordCount {

	
	public static void main(String[] args) {
		
		final int SERVERPORT = 4001;
		
		int bufferSize = 1024;
		
		System.out.println("Server-Side Application");
		
		try {
			
			DatagramSocket serverSocket = new DatagramSocket(SERVERPORT);
			
			byte[] inData = new byte[bufferSize];
			
			DatagramPacket inputPacket = new DatagramPacket(	inData,	inData.length);
			
			System.out.println("Ready to receive connection...");
			
			serverSocket.receive(inputPacket);
			
			String data = new String(inputPacket.getData());
			
			System.out.println("Data from the client: "+ data);
			// to count the word
			String wordCounted = Integer.toString(countWords(data));
			// to send it back
			byte outDataBuffer[] = new byte[bufferSize];
			
			outDataBuffer = wordCounted.getBytes();
			
			
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
			DatagramPacket outputPacket = new DatagramPacket(outDataBuffer, 
					outDataBuffer.length, senderAddress,senderPort);
			
			System.out.println("Sending it back to client");
			serverSocket.send(outputPacket);
	
			serverSocket.close();
			
		}catch (Exception ex) {
			System.out.println("Something went wront");
			ex.printStackTrace();
		}finally{
			
			System.out.println("done with the server side");
		}
		
	}
	
	public static int countWords(String text) {
		
		String trim = text.trim();
        if (trim.isEmpty())
                return 0;
        return trim.split("\s+").length; 
        
	}
}

