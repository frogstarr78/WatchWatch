import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;


/**
 * NtpClient - an NTP client for Java.  This program connects to an NTP server
 * and prints the response to the console.
 * 
 * The local clock offset calculation is implemented according to the SNTP
 * algorithm specified in RFC 2030.  
 * 
 * Note that on windows platforms, the curent time-of-day timestamp is limited
 * to an resolution of 10ms and adversely affects the accuracy of the results.
 * 
 * 
 * This code is copyright (c) Adam Buckley 2004
 *
 * This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.  A HTML version of the GNU General Public License can be
 * seen at http://www.gnu.org/licenses/gpl.html
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details.
 *  
 * @author Adam Buckley
 */
public class SntpClient {
	String serverName;
	NtpMessage msg;
	DatagramPacket packet;
	DatagramSocket socket;
	InetAddress address;
	byte[] buf;
	double destinationTimestamp;
	double roundTripDelay;
	double localClockOffset;

	public SntpClient() {
		this.serverName = null;
	}
	public SntpClient(String serverName) {
		// Send request
		this.setServerName(serverName);
		this.request();
	}

	public void setServerName(String serverName) { this.serverName = serverName; }
	public String getServerName() { return this.serverName; }

	public void request() {
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName(this.serverName);
			buf = new NtpMessage().toByteArray();
			packet = new DatagramPacket(buf, buf.length, address, 123);
		} catch (java.net.SocketException s_e) {
			System.out.println("Socket Error " + s_e.toString());	
			s_e.printStackTrace();
		} catch (java.net.UnknownHostException u_e) {
			System.out.println("Unknown Host " + u_e.toString());	
			u_e.printStackTrace();
		}
		
		// Set the transmit timestamp *just* before sending the packet
		// ToDo: Does this actually improve performance or not?
		NtpMessage.encodeTimestamp(packet.getData(), 40, (System.currentTimeMillis()/1000.0) + 2208988800.0);
		try {
			socket.send(packet);
		} catch (java.io.IOException io_e) {
			System.out.println("IO Error " + io_e.toString());	
			io_e.printStackTrace();
		}
		
		
		// Get response
		System.out.println("NTP request sent, waiting for response...\n");
		packet = new DatagramPacket(buf, buf.length);
		try {
			socket.receive(packet);
		} catch (java.io.IOException io_e) {
			System.out.println("IO Error " + io_e.toString());	
			io_e.printStackTrace();
		}
		
		// Immediately record the incoming timestamp
		destinationTimestamp = (System.currentTimeMillis()/1000.0) + 2208988800.0;
		
		
		// Process response
		msg = new NtpMessage(packet.getData());
		
		// Corrected, according to RFC2030 errata
		roundTripDelay = (destinationTimestamp-msg.originateTimestamp) - (msg.transmitTimestamp-msg.receiveTimestamp);
		localClockOffset = ((msg.receiveTimestamp - msg.originateTimestamp) + (msg.transmitTimestamp - destinationTimestamp)) / 2;

		socket.close();
	}

	public String getReceivedTimestampAsString() {
		return NtpMessage.timestampToString(msg.receiveTimestamp);
	}

	public String getDestinationTimestampAsString() {
		return NtpMessage.timestampToString(destinationTimestamp);
	}

	public String getRoundTripDelay() {
		return new DecimalFormat("0.00").format(roundTripDelay*1000) + " ms";
	}

	public String getLocalClockOffset() {
		return new DecimalFormat("0.00").format(localClockOffset*1000) + " ms";
	}

	public void printResults() {
		// Display response
		System.out.println("Receive timestamp from server:   " + getReceivedTimestampAsString() + "\n");
		System.out.println("NTP server: " + this.serverName);
		System.out.println(msg.toString());
		System.out.println("Dest. timestamp:     " + getDestinationTimestampAsString());
		System.out.println("Round-trip delay: "    + getRoundTripDelay());
		System.out.println("Local clock offset: "  + getLocalClockOffset());
	}

	public static void main(String[] args) throws IOException {
		String serverName;
		
		// Process command-line args
		if(args.length==1) {
			serverName = args[0];
		} else {
			printUsage();
			return;
		}
		
		SntpClient client = new SntpClient(serverName);
		client.printResults();	
	}
	
	
	
	/**
	 * Prints usage
	 */
	static void printUsage()
	{
		System.out.println(
			"NtpClient - an NTP client for Java.\n" +
			"\n" +
			"This program connects to an NTP server and prints the response to the console.\n" +
			"\n" +
			"\n" +
			"Usage: java NtpClient server\n" +
			"\n" +
			"\n" +
			"This program is copyright (c) Adam Buckley 2004 and distributed under the terms\n" +
			"of the GNU General Public License.  This program is distributed in the hope\n" +
			"that it will be useful, but WITHOUT ANY WARRANTY; without even the implied\n" +
			"warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU\n" +
			"General Public License available at http://www.gnu.org/licenses/gpl.html for\n" +
			"more details.");
					
	}
}
