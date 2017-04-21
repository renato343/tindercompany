package org.renato.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ricardo on 27-02-2017.
 */

public class Server {

    private static CopyOnWriteArrayList usersList = new CopyOnWriteArrayList();

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        boolean socketListener = true;

        try {
            serverSocket = new ServerSocket(9999);

            while (socketListener) {

                Socket clientSocket = new Socket();
                System.out.println("server á escuta");
                clientSocket = serverSocket.accept();

                Clienthandler clienthandler = new Clienthandler(clientSocket);

                Thread thread = new Thread(clienthandler);

                thread.start();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static class Clienthandler implements Runnable {

        private int currentCompany = -1;
        private int currentCadet = -1;
        private PrintWriter out = null;
        BufferedReader in = null;
        private Socket socket = null;
        String CompanyMessage;
        String CadetMessage;

        public Clienthandler(Socket socket) {
            this.socket = socket;
        }

        public void showCompanysMoto() {

            currentCompany++;
            if (currentCompany == MethodstoUseInCompanyOrCader.CompanyInfo.values().length) {
                currentCompany = 0;
            }
            for (int i = currentCompany; i < MethodstoUseInCompanyOrCader.CompanyInfo.values().length; i++) {

                out.println(MethodstoUseInCompanyOrCader.CompanyInfo.values()[i].description);
                break;

            }

        }

        public void showCadetsPitch() {

            currentCadet++;
            if (currentCadet == Cadet.CadetInfo.values().length) {
                currentCadet = 0;
            }
            for (int i = currentCadet; i < Cadet.CadetInfo.values().length; i++) {

                out.println(Cadet.CadetInfo.values()[i].saying);
                break;

            }

        }

        public void showCompanyFile() {

            for (int i = currentCompany; i < MethodstoUseInCompanyOrCader.CompanyInfo.values().length; i++) {
                out.println(MethodstoUseInCompanyOrCader.CompanyInfo.values()[i].file);
                break;
            }
        }

        public void showCadetsFile() {

            for (int i = currentCadet; i < Cadet.CadetInfo.values().length; i++) {
                out.println(Cadet.CadetInfo.values()[i].filepath);
                break;
            }
        }

        public void cadetsList() {

            try {

                showCadetsPitch();

                while (true) {

                    CadetMessage = in.readLine();

                    switch (CadetMessage) {
                        case ("next"):

                            showCadetsPitch();
                            break;

                        case ("moreinfo"):
                            showCadetsFile();

                            CadetMessage = in.readLine();
                            if (CadetMessage.equals("next")) {
                               // out.println("HERE'S ONE CADET ....CHOOSE,NEXT OR MORE INFO");
                                showCadetsPitch();
                                break;
                            } else {
                               // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MORE INFO");
                                showCadetsPitch();
                                break;
                            }

                        case ("match"):
                           // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MOREINFO");
                            showCadetsPitch();
                            break;

                    }
                    if (CadetMessage.equals("/close")) {

                        out.close();
                        in.close();
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    socket.close();
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void companyList() {

            try {
                showCompanysMoto();


                while (true) {

                    CompanyMessage = in.readLine();

                    switch (CompanyMessage) {
                        case ("next"):
                            //out.println("HERE'S ONE COMPANY...CHOOSE, NEXT OR MORE INFO");
                            showCompanysMoto();
                            break;

                        case ("moreinfo"):
                            showCompanyFile();
                           // out.println("CHOOSE NEXT OR MATCH");
                            CompanyMessage = in.readLine();

                            while (!CompanyMessage.equals("next") && !(!CompanyMessage.equals("match"))) {
                                //out.println("choose next or match");
                                CompanyMessage = in.readLine();
                            }
                            if (CompanyMessage.equals("next")) {
                                //out.println("HERE'S ONE COMPANY...CHOOSE, NEXT OR MORE INFO");
                                showCompanysMoto();
                                break;
                            } else {
                                //out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ANOTHER COMPANY...CHOOSE, NEXT OR MORE INFO");
                                showCompanysMoto();
                                break;
                            }

                        case ("match"):
                            //out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ANOTHER COMPANY...CHOOSE, NEXT OR MORE INFO");
                            showCompanysMoto();
                            break;

                    }
                    if (CompanyMessage.equals("/close")) {

                        out.close();
                        in.close();
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    socket.close();
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void run() {

            try {

                System.out.println("enter the run");

                out = new PrintWriter(socket.getOutputStream(), true);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("What are you?");

                String message = in.readLine();

                while (!message.equals("cadet") && !message.equals("company")) {
                    out.println("wrong command: write company or cadet");
                    message = in.readLine();
                }
                if (message.equals("cadet")) {

                    out.println("What is your name");
                    String name = in.readLine();
                    out.println("What is your email");
                    String email = in.readLine();
                    out.println("What is your motto");
                    String motto = in.readLine();

                   // ServiceRegistry.getInstance().add();
                    usersList.add(new Cadet(motto,name,email));

                    System.out.println("in if cadet of run");
                    companyList();
                } else {
                    System.out.println("in if company of run");
                    cadetsList();
                }


            } catch (
                    IOException e)

            {
                e.printStackTrace();
            }
        }
    }

}







