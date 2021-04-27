package exe5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//implementing udp connection for client
public class ClientWordCount {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);// create object scanner to get input from user
				
		final int SERVERPORT = 4001; // server port where client will connect with
		
		int bufferSize = 1024; 
		
		
		System.out.println("Client-Side Application for counting word");
		
		try {
			
			DatagramSocket clientSocket = new DatagramSocket();// socket for client
			
			InetAddress serverAddress = InetAddress.getByName("localhost");// get the server dns
			
			byte outDataBuffer[] = new byte[bufferSize];// creating corresponding buffer to send data
			
			String sentence = sc.nextLine();
			outDataBuffer = sentence.getBytes(); // convert the sentence in bytes 
			DatagramPacket outPacket = new DatagramPacket(	outDataBuffer, 
															outDataBuffer.length, 
															serverAddress, 
															SERVERPORT);// creating a udp packet
			
			System.out.println("Sending the word to the server. Size : "+ outDataBuffer.length);
			clientSocket.send(outPacket);// send the packet to the server
			//RECEIVE PART
			
			byte[] inData = new byte[bufferSize];// create the corresponding buffer to get the data
			DatagramPacket inputPacket = new DatagramPacket(inData, inData.length);// create a udp packet to store the client data using the buffer
			
			clientSocket.receive(inputPacket);// receive the packet
			String wordCount = new String(inputPacket.getData());// unpack the packet into string
			System.out.println("Receive the reply form server");
			
			System.out.println("The word count for "+ sentence + " is : "+ wordCount);// display the result
			
			
			clientSocket.close(); // close the socket
		}catch (Exception ex) {
			
			System.out.println("Something went wrong");
			ex.printStackTrace();
			
		}
		finally{
			
			sc.close();
			System.out.println("the program terminated");
			
		}
	}
}
