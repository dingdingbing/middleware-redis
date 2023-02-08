import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/6/30 16:45
 */
public class LoginUtil {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        String username = System.getProperty("username");
        System.out.println(username);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("uri", "d3d3LmdzdGF0aWMuY29tL2dlbmVyYXRlXzIwNA=="));
        nameValuePairs.add(new BasicNameValuePair("username", args[0]));
        nameValuePairs.add(new BasicNameValuePair("password", "%B9x%FB%1A%9D%5C"));
        nameValuePairs.add(new BasicNameValuePair("terminal", "pc"));
        nameValuePairs.add(new BasicNameValuePair("login_type", "login"));
        String s = doPost("http://1.1.2.254:90/login", nameValuePairs);
        System.out.println(s);
    }

    public static String doPost(String urlStr, List<NameValuePair> nameValuePairList) {
        HttpPost httpPost = new HttpPost(urlStr);
        String result;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "UTF-8"));
            HttpResponse res = httpClient.execute(httpPost);
            if (res.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

                return null;
            } else {
                result = EntityUtils.toString(res.getEntity(), "UTF-8");// 返回json格式：
                return result;
            }
        } catch (Exception e) {

        } finally {
            httpPost.releaseConnection();
        }
        return null;
    }
}
