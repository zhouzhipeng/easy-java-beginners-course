import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        String  url= "http://www.chengxuz.com/dongman/500.html";

        String htmlContent = new String(new URL(url).openStream().readAllBytes(), StandardCharsets.UTF_8);

        System.out.println(htmlContent);

    }
}
