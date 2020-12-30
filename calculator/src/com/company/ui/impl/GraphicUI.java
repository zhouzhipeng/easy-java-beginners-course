package com.company.ui.impl;

import com.company.ui.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.company.EventManager.Event.BUTTON_CLICKED;
import static com.company.EventManager.Event.TEXT_UPDATE;
import static com.company.EventManager.publish;
import static com.company.EventManager.subscribe;

public class GraphicUI implements UI {
    public void show(){
        new JFrame() {{
            setTitle("计算器");    //设置窗体的标题
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置窗体退出时操作
            setBounds(100, 100, 240, 300);    //设置窗体位置和大小
            setContentPane(new JPanel() {{
                setBorder(new EmptyBorder(5, 5, 5, 5));    //设置面板的边框
                setLayout(new BorderLayout(0, 0));    //设置内容面板为边界布局
                add(new JPanel() {{
                    add(new JTextField() {{
                        setHorizontalAlignment(SwingConstants.RIGHT);    //文本框中的文本使用右对齐
                        setColumns(18);    //设置文本框的列数是18
                        setEnabled(false);

                        subscribe(TEXT_UPDATE, obj -> {
                            this.setText(obj.toString());
                        });
                    }});
                }}, BorderLayout.NORTH);    //将面板放置在边界布局的北部
                add(new JPanel() {{
                    setLayout(new GridLayout(4, 4, 5, 5));    //面板使用网格4X4布局

                    for (String symbol : new String[]{
                            "7", "8", "9", "+",
                            "4", "5", "6", "-",
                            "3", "2", "1", "*",
                            "0", ".", "=", "/"}) {
                        add(new JButton(symbol) {{
                            addActionListener(e -> {
                                System.out.println(this.getText());
                                publish(BUTTON_CLICKED, this.getText());
                            });
                        }});
                    }
                }}, BorderLayout.CENTER);    //将面板放置在边界布局的中央
            }});

            //最后一行再调用显示窗口.
            setVisible(true);
        }};
    }
}
