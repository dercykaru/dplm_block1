package ru.netology.javacore;

import org.junit.jupiter.api.*;

//---
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodosTests {

    private Todos todosTest;

    @BeforeEach
    public void initTodos() {
        todosTest = new Todos();
        String one = "Отдохнуть";
        String two = "Совершить подвиг";
        String three = "Покорить Марс";
        String todo1 = "Встать с дивана";

        todosTest.addTask(one);
        todosTest.addTask(two);
        todosTest.addTask(three);
        todosTest.addTask(todo1);
    }

    //Тест для работы функции addTask:
    @Test
    public void testAdd() {
        String expectResult = "Надеть суперкостюм";

        todosTest.addTask("Надеть суперкостюм");
        String result = todosTest.todoList1.get(4);

        Assertions.assertEquals(expectResult, result);
    }//end_testAdd

    //Тест на проверку удаления элементов:
    @Test
    public void testRemove() {
        int expectResult = 3;

        todosTest.removeTask("Покорить Марс");
        int result = todosTest.todoList1.size();

        Assertions.assertEquals(expectResult, result);
    }

    //Тест на сортировку и выдачу результата:
    @Test
    public void testGetAll() {
        String expectResult = "Встать с дивана Отдохнуть Покорить Марс Совершить подвиг ";

        String result = todosTest.getAllTasks();

        Assertions.assertEquals(expectResult, result);
    }

}
