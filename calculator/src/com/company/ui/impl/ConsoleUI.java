package com.company.ui.impl;

import com.company.data.InputData;
import com.company.ui.UI;

import java.util.Scanner;

import static com.company.EventManager.Event.INPUT_COMPLETED;
import static com.company.EventManager.publish;

public class ConsoleUI implements UI {
    public void show(){
        new Thread(() -> {
            //从输入获取数字并构造calculator对象
            InputData inputData = getAllInputs();
            publish(INPUT_COMPLETED, inputData);
        }).start();
    }


    public static InputData getAllInputs() {
        int input = getNumberFromConsole();

        System.out.println(input);

        int input2 = getNumberFromConsole();

        System.out.println(input2);

        System.out.println("请输入运算符(+,-,*,/): ");
        Scanner scanner = new Scanner(System.in);
        String operator = scanner.next();

        return new InputData(input, input2, operator);
    }


    public static int getNumberFromConsole() {
        System.out.println("请输入一个数字");

        Scanner scanner = new Scanner(System.in);
        int input = -1;

        while (true) {
            try {
                scanner = new Scanner(System.in);
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("你输入的不是一个数字，请输入一个数字!!");
            }
        }

        return input;
    }
}
