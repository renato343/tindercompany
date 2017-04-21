package org.renato.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Renato on 28/02/17.
 */
public class User {

    String host;
    int port;
    Socket socket = null;

    PrintWriter out = null;

    BufferedReader in = null;


    private static String message() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();

    }

    private static String question() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();

    }

    private static int port(String portQuestion) {

        Scanner reader = new Scanner(System.in);
        System.out.print(portQuestion);
        return reader.nextInt();

    }

    public void userConstrutor() {

        host = "localhost";
        port = 9999;

        try {
            socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        userConstrutor();

        try {

            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String myMessage = "";

            while (!myMessage.equals("/close")) {

                String serverMessage = in.readLine();
                System.out.println(serverMessage);
                myMessage = message();
                out.println(myMessage);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    public static void main(String[] args) {

        User user = new User();

        user.run();



    }


}