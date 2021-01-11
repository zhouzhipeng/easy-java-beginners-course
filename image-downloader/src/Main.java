import java.io.FileOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        String  url= "http://www.chengxuz.com/dongman/500.html";

        String htmlContent = new String(new URL(url).openStream().readAllBytes(), StandardCharsets.UTF_8);

        System.out.println(htmlContent);

        String regex="http.+\\.jpg";


        //正则
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlContent);
        while(matcher.find()){
            String imgUrl=matcher.group();
            System.out.println(imgUrl);

            byte[] bytes = new URL(imgUrl).openStream().readAllBytes();
            //将bytes 写入文件
            //
            String filename="/Users/zhouzhipeng/IdeaProjects/easy-java-beginners-course/image-downloader/images/"+
                    System.currentTimeMillis()+".jpg";
            new FileOutputStream(filename).write(bytes);

        }


    }
}
