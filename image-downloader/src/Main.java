import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        String  url= "http://www.chengxuz.com/dongman/500.html";

        String htmlContent = new String(new URL(url).openStream().readAllBytes(), StandardCharsets.UTF_8);

        System.out.println(htmlContent);

        String regex="http.+\\.jpg";



        long startTime= System.currentTimeMillis();

        //正则
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlContent);

        int maxCount=0;

        String imageSaveDir="/Users/zhouzhipeng/IdeaProjects/easy-java-beginners-course/image-downloader/images/";
        File directory = new File(imageSaveDir);
        Arrays.stream(directory.listFiles()).forEach(file->file.delete());
        directory.mkdir();


        AtomicInteger currentCount=new AtomicInteger(0);
        while(matcher.find()){
            maxCount++;  //maxCount=maxCount+1;
            String imgUrl=matcher.group();
            System.out.println(imgUrl);

            new Thread(() ->{

                try {

                    //耗费网络io
                    byte[] bytes = new URL(imgUrl).openStream().readAllBytes();
                    //将bytes 写入文件
                    //
                    //耗费磁盘io
                    String filename=imageSaveDir+System.currentTimeMillis()+".jpg";
                    new FileOutputStream(filename).write(bytes);
                    System.out.println(filename);

                    currentCount.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }


        while(currentCount.get()<maxCount){
            Thread.sleep(10);
        }


        int actualCount= directory.listFiles().length;

        long endTime= System.currentTimeMillis();
        System.out.println("总图片数量："+maxCount+", 实际下载数量:"+actualCount+", 总耗时:"+ (endTime-startTime)/1000);


    }
}
