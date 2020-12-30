package com.company;

import com.company.algo.Calculator;
import com.company.ui.impl.ConsoleUI;
import com.company.ui.impl.GraphicUI;
import com.company.ui.UI;

public class Main {

    /*
      通过本节课程，你将学习到： `

      1.  了解控制台的输入和输出

      2. 了解整数类型和字符串类型

      3. 了解整数类型的基本运算操作

   */


    public static void main(String[] args) {

        boolean useGraphicUI = true; //TODO: 从配置读取

        UI ui = useGraphicUI ? new GraphicUI() : new ConsoleUI();
        ui.show();

        new Calculator().init();
    }


}
