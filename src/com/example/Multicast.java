package com.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Multicast extends Thread {
    DatagramSocket socket = null;
    DatagramPacket datagramPacket = null;
    InetAddress group = null;
    String message = "DISCOVERY";
    byte[] buffer = new byte[1024];
    byte[] buffer2 = new byte[1024];
    String response;

    Multicast() {
        try {
            socket = new DatagramSocket();
            group = InetAddress.getByName("224.0.0.0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 7);
        datagramPacket = new DatagramPacket(buffer2, buffer2.length);

        try {
            socket.send(packet);
            socket.receive(datagramPacket);

            this.response = new String(
                    datagramPacket.getData(), 0, datagramPacket.getLength());

        } catch (Exception e) {
            e.printStackTrace();
            socket.close();
        }

        socket.close();
    }
}
