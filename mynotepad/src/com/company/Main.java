package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here


//

        new JFrame() {{
            setTitle("我的文本编辑器");
            setSize(400, 600);

            setContentPane(new JPanel() {{
                setLayout(new BorderLayout());


                final long[] length = {0};


                add(new JPanel(){{
                    add(new JTextArea(60, 30){{

                        //this --> 当前对象
                        JTextArea area=this;
                        final File[] bindFile = {null};

                        //定时器 来定期的将text 写入到文件
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("run...");
                                try {
                                    if(bindFile[0] !=null) {
                                        new FileOutputStream(bindFile[0]).write(area.getText().getBytes());
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 1000, 1000);


                        //绑定文件拖拽事件
                        this.setTransferHandler(new TransferHandler()
                        {
                            private static final long serialVersionUID = 1L;
                            @Override
                            public boolean importData(JComponent comp, Transferable t) {
                                try {
                                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);

                                    String filepath = o.toString();
                                    if (filepath.startsWith("[")) {
                                        filepath = filepath.substring(1);
                                    }
                                    if (filepath.endsWith("]")) {
                                        filepath = filepath.substring(0, filepath.length() - 1);
                                    }
                                    System.out.println(filepath);


                                    //读取文件内容
                                    File file = new File(filepath);

                                    bindFile[0] =file;
                                    length[0] = file.length();
                                    EventManager.publish(EventManager.Event.FILE_DRAG_DROP, file);


                                    System.out.println(file.length());
                                    System.out.println(file.lastModified());

                                    String content = new String(new FileInputStream(file).readAllBytes());
                                    System.out.println(content);

                                    area.setText(content);
                                    return true;
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return false;
                            }
                            @Override
                            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                                for (int i = 0; i < flavors.length; i++) {
                                    if (DataFlavor.javaFileListFlavor.equals(flavors[i])) {
                                        return true;
                                    }
                                }
                                return false;
                            }
                        });

                    }});
                }}, BorderLayout.CENTER);



                add(new JPanel(){{
                    setLayout(new BorderLayout());

                    add(new JLabel("文件长度: 0"){{
                        EventManager.subscribe(EventManager.Event.FILE_DRAG_DROP,obj->{
                            File file=(File)obj;
                            this.setText("文件长度:"+ file.length());
                        });
                    }}, BorderLayout.CENTER);


                    add(new JLabel("修改时间:-"){{
                        EventManager.subscribe(EventManager.Event.FILE_DRAG_DROP,obj->{
                            File file=(File)obj;
                            String format = new SimpleDateFormat().format(new Date(file.lastModified()));

                            this.setText("修改时间:"+ format);
                        });
                    }}, BorderLayout.SOUTH);

                }}, BorderLayout.SOUTH);



            }});


            setVisible(true);
        }};

    }
}
