import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Processing {

  // execute script
    public String getInformationFromURL(String URL, CloseableHttpClient client) throws IOException {
        HttpGet request = new HttpGet(URL);
        CloseableHttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String result = rd.readLine();
        System.out.println(result);
        response.close();
        return result;
    }


    //choose value using pattern
    public String[] getElement(String informationFromURL, String pattern){
        String[] split = informationFromURL.split(pattern);
        String[] currentArray= new String[20];
        for (int i = 0; i < 20; i++) {
            String[] tempArray = split[i].split(",");
            currentArray[i]=tempArray[0].replaceAll("\"","");
        }
        return currentArray;
    }

}
