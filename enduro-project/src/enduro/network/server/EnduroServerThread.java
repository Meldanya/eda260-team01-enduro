package enduro.network.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Server thread to be ran in the main server program, contains information
 * whether to write start or goal files
 */
public class EnduroServerThread extends Thread {
	private final static int START = 0;
	private final static int GOAL = 1;

	private Socket socket = null;
	private PrintWriter timesOut;
	private BufferedReader socketIn;
	private Scanner outputFileScanner;
	private File output;
	int number;

	/**
	 * Sets up the server thread
	 * 
	 * @param socket
	 *            Socket to connect on
	 * @param number
	 *            int representing which thread in order this is
	 * @throws IOException
	 */
	public EnduroServerThread(Socket socket, int number) throws IOException {
		super("EnduroServerThread " + number);
		this.number = number;
		this.socket = socket;
		// out = new PrintWriter(socket.getOutputStream(), true);
		socketIn = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
	}

	/**
	 * Method to run the server thread
	 */
	public void run() {
		int status = -1;
		String s = null;
		FileWriter writer = null;
		try {
			s = socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (status == -1) {
			try {
				if (s.equals("Goal")) {
					status = GOAL;
					writer = new FileWriter("maltider1.txt", true);
					output = new File("maltider1.txt");
				} else if (s.equals("Start")) {
					status = START;
					writer = new FileWriter("starttider1.txt", true);
					output = new File("starttider1.txt");
				} else
					System.out.println("Unexpected file format from client");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (s.equals("Goal")) {
			System.out.println("Client " + number + " handles finish times");
		} else if (s.equals(s.equals("Start"))) {
			System.out.println("Client " + number + " handles start times");
		}
		timesOut = new PrintWriter(new BufferedWriter(writer), true);

		while (true) {
			try {
				s = socketIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (s != null) {
				if (s.equals("quit"))
					break;
				writeToFile(s);
			}
		}
		timesOut.close();
		try {
			System.out.println("Server says: Goodbye!");
			socketIn.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(String s) {
		boolean found = false;
		try {
			outputFileScanner = new Scanner(output);
			while (outputFileScanner.hasNext()) {
				String existing = outputFileScanner.nextLine();
				if (existing.equals(s))
					found = true;
			}
		} catch (FileNotFoundException e) {
		}
		if (!found) {
			timesOut.println(s);
			timesOut.flush();
			System.out.println("Client " + number + " sends: " + s);
		} else
			System.out.println("Client " + number
					+ " sent redundant data. Time was already in file: " + s);
	}

}
