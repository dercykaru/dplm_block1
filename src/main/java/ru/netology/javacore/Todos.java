package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    //Поле списка дел:
    protected List<String> todoList1 = new LinkedList<>();

    //Конструктор пустой:
    public Todos() {
    }

    //Функция ввода новой задачи:
    public void addTask(String task) {
        todoList1.add(task);
    }

    //Функция удаления задачи из списка:
    public void removeTask(String task) {
        todoList1.remove(task);
    }

    //Функция выдачи всего списка дел (в уже отсортированном виде!):
    public String getAllTasks() {
        //сортируем имеющийся список:
        Collections.sort(todoList1);

        //Формирование списка в виде единое строки (через StringBuilder):
        StringBuilder sb = new StringBuilder();
        for (String s : todoList1) {
            sb.append(s);
            sb.append(" ");
        }
        String sss = sb.toString();
        return sss;
    }//end_getAllTask

}
