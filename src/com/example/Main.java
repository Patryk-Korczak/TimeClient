package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner myScanner = new Scanner(System.in);
            //todo - error handling on entering port/ip/frequency
            System.out.println("Enter address: ");
            String address = myScanner.next();
            System.out.println("Enter port: ");
            int port = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Enter requests frequency (10-1000 ms): ");
            int freq = myScanner.nextInt();
            Client client = new Client(address, port);
            myScanner.nextLine(); // clear scanner after taking int input
            while (client.isConnected) {
                //System.out.println("Enter message: ");
                //String message = myScanner.nextLine();
                String message = "getTime";
                client.sendMessage(message);

                try {
                    Thread.sleep(freq);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}





