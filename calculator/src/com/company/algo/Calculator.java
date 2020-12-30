package com.company.algo;

import com.company.data.InputData;

import java.util.Arrays;

import static com.company.data.Context.*;
import static com.company.data.Context.operator;
import static com.company.EventManager.Event.*;
import static com.company.EventManager.publish;
import static com.company.EventManager.subscribe;

public class Calculator {
    InputData inputData;

    int calculate(){
        int result=-1;
        if(inputData.getOperator().equals("+")){
            result = inputData.getInput() +inputData.getInput2();
        }else if(inputData.getOperator().equals("-")){
            result = inputData.getInput() - inputData.getInput2();
        }else if(inputData.getOperator().equals("*")){
            result = inputData.getInput() * inputData.getInput2();
        }else if(inputData.getOperator().equals("/")){
            result = inputData.getInput() / inputData.getInput2();
        }

        return result;
    }


    public void init(){

        subscribe(INPUT_COMPLETED, obj -> {

            //根据输入的operator 进行运算并获取结果
            inputData = (InputData) obj;
            int result = calculate();
//            打印结果
            System.out.println(result);

            publish(TEXT_UPDATE, result);
        });


        //回显
        subscribe(BUTTON_CLICKED, obj -> {
            String str = obj.toString();

            System.out.println(str);

            outputText += str;

            publish(TEXT_UPDATE, outputText);
        });

        //记录下操作符
        subscribe(BUTTON_CLICKED, obj -> {
            String str = obj.toString();
            if (Arrays.asList("-", "+", "*", "/").contains(str)) {
                operator = str;
                input = outputText.substring(0, outputText.length() - 1);
            }
        });


        //计算结果
        subscribe(BUTTON_CLICKED, obj -> {
            String str = obj.toString();
            if (str.equals("=")) {
                //假设已经限制了用户只能输入两个操作数和一个操作符
                input2 = outputText.substring((input + operator).length(), outputText.length() - 1);

                InputData inputData = new InputData(Integer.parseInt(input), Integer.parseInt(input2), operator);
                publish(INPUT_COMPLETED, inputData);

            }
        });
    }

}
