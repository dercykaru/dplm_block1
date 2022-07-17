package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

public class TodoServer {
    int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    //Функция запуска сервера и обработки им запросов:
    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            //Принимаем подключения в цикле:
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream());
                     //без autoFlush!!
                ) {
                    String input = in.readLine();
                    parserFromJson(input);

                    String output = todos.getAllTasks();
                    out.println(output);

                }
            }//end_while

            //...
        } catch (IOException ex) {
            System.out.println("SERVER CANT connect.....");
            ex.printStackTrace();
        }
    }//end_start

    //Функция распознавания команды из json:
    public void parserFromJson(String inputFrClient) {
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(inputFrClient);
        JsonObject jsonObj = (JsonObject) obj;

        //Определение типа команды (добавить или удалить) и выполнение:
        String commandType = String.valueOf(jsonObj.get("type"));
        String currTask = String.valueOf(jsonObj.get("task"));
        System.out.println();

        switch (commandType) {
            case "\"ADD\"":
                todos.addTask(currTask);
                System.out.println("COMMAND = add, task = " + currTask);
                break;
            case "\"REMOVE\"":
                todos.removeTask(currTask);
                System.out.println("COMMAND = remove, task = " + currTask);
                break;
        }//end_switch

    }//end_parserFromJson

}
