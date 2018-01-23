package org.usfirst.frc.team2473.framework;


import java.io.IOException;

import org.usfirst.frc.team2473.utility.UtilitySocket;

/**
 * Main socket class. Automatically pings back to the server to inform of its connection.
 * Can be configured to perform other actions like request values from the server and read them.
 * 
 * @author wang.patrick57@gmail.com
 * @author JosephM
 */
public class DatabaseAndPingThread extends Thread {
	private UtilitySocket uSocket;
	private boolean alive;
	
	private final boolean DEBUG = true;
	
	/**
	 * Creates a SocketPingThread.
	 * @throws IOException If an I/O error occurs when creating the socket
	 */
	public DatabaseAndPingThread(String ip, int port) throws IOException {
		uSocket = new UtilitySocket(ip, port);
		System.out.println("connected to server");
		alive = true;
	}
	
	/**
	 * The method that is run every tick. It runs everyTick(), responds to pings from the server,
	 * and processes sent values generally by putting them in Database
	 */
	@Override
	public void run() {
		while(alive) {
			try {
				long startTime = System.currentTimeMillis();
				String received = uSocket.getLine();
				ifDebugPrint("received: " + received);
				
				while(received != null) {
					if(received.equals("s")) {
						ifDebugPrint("server ping received");
						uSocket.sendLine("c");
					} else {
						//do other stuff from what is received, generally putting information in Database
						//format of what is received should generally be in the format of (key + " " + value)
						System.out.println(received);
						int[] spaces = new int[3];
						int idx = 0;
						for(int i = 0; i < received.length(); i++) {
							if(received.charAt(i) == ' ') {
								spaces[idx] = i;
								idx++;
								if(idx > 2)
									break;
							}
						}
						
						Database.getInstance().setNumeric(
								received.substring(0, spaces[0]),
								Double.parseDouble(
										received.substring(spaces[0]+1, spaces[1])
								)
						);
						Database.getInstance().setNumeric(
								received.substring(spaces[1]+1, spaces[2]),
								Double.parseDouble(
										received.substring(spaces[2]+1)
								)
						);
					}
					
					received = uSocket.getLine();
					ifDebugPrint("received: " + received);
				}
				
				everyTick();
				Thread.sleep(20 - (System.currentTimeMillis()-startTime));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(getClass().getSimpleName() + " loop ended");
	}
	
	/**
	 * This method is called every tick!!!
	 */
	public void everyTick() {
		requestValues();
	}
	
	/**
	 * This method requests values from the server.
	 */
	public void requestValues() {
		uSocket.sendLine("r");
		ifDebugPrint("requesting values");
	}
	
	/**
	 * This method closes the UtilitySocket and kills the SocketPingThread
	 */
	public void end() {
		System.out.println(getClass().getSimpleName() + " ending");
		alive = false;
		
		try {
			uSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void ifDebugPrint(String s) {
		if(DEBUG) System.out.println(s);
	}
}