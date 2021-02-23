package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            String address = null;
            int port = 0;
            int freq = 0;
            Scanner myScanner = new Scanner(System.in);
            boolean ipCorrect = false;
            boolean portCorrect = false;
            boolean freqCorrect = false;
            Multicast multicast = new Multicast();
            multicast.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!(multicast.response == null)) {
                System.out.println("Addresses received from multicast discovery: ");
                System.out.println(multicast.response);
            }

            while (!ipCorrect) {
                System.out.println("Enter address: ");
                address = myScanner.next();
                if (address.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")) {
                    ipCorrect = true;
                } else {
                    System.out.println("Incorrect address format, please try again!");
                }
            }

            while (!portCorrect) {
                System.out.println("Enter port: ");
                port = myScanner.nextInt();
                if (port < 1 || port > 64738) {
                    System.out.println("Incorrect port, please try again!");
                } else {
                    portCorrect = true;
                }
            }
            myScanner.nextLine();

            while (!freqCorrect) {
                System.out.println("Enter requests frequency (10-1000 ms): ");
                freq = myScanner.nextInt();
                if (freq < 10 || freq > 1000) {
                    System.out.println("Incorrect frequency, values from 10-1000 range are accepted.");
                } else {
                    freqCorrect = true;
                }
            }
            Client client = new Client(address, port);
            myScanner.nextLine(); // clear scanner after taking int input
            while (client.isConnected) {
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





