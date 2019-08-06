import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<ProductModel> arrayListProduct= new ArrayList<ProductModel>();
        CloseableHttpClient client = HttpClients.createDefault();
        Processing processing = new Processing();

        //csvResult - result of work  method main
        CSV csvResult =new CSV();
        String[] information = new String[6];
        int j = 0;
        for (int offcet = 0; offcet <= 100; offcet = offcet + 20) {
            String URL = "https://gpsfront.aliexpress.com/queryGpsProductAjax.do?callback=jQuery1830210686789858054_1564941263000&widget_id=5547572&platform=pc&limit=20&offset=" + offcet + "&phase=1&productIds2Top=&postback=72e7624e-338b-4e57-9e00-4dce88e8a303&_=1564941392951";
            information[j] = processing.getInformationFromURL(URL, client);
            j++;
            try {
                //slep, that site not to  block
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.close();

           //parse the result of script from Alli
        for (int i = 0; i < information.length; i++) {
            String[] productID = processing.getElement(information[i], "\"productId\":");
            String[] productImage = processing.getElement(information[i], "\"productImage\":");
            String[] minPrice = processing.getElement(information[i], "\"minPrice\":");
            String[] maxPrice = processing.getElement(information[i], "\"maxPrice\":");
            String[] oriMaxPrice= processing.getElement(information[i], "\"oriMaxPrice\":");
            String[] oriMinPrice= processing.getElement(information[i], "\"oriMinPrice\":");
            String[] discount = processing.getElement(information[i], "\"discount\":");
            String[] startTime = processing.getElement(information[i], "\"startTime\":");
            String[] endTime = processing.getElement(information[i], "\"endTime\":");
            String[] orders = processing.getElement(information[i], "\"orders\":");
            String[] shopURL= processing.getElement(information[i], "\"shopUrl\":");
            String[] productTitle = processing.getElement(information[i], "\"productTitle\":");
            String[] sellerId  = processing.getElement(information[i], "\"sellerId\":");
            String[] productDetailUrl  = processing.getElement(information[i], "\"productDetailUrl\":");


            for(int k=1; k<20; k++){
                ProductModel currentModel =new ProductModel();
                currentModel.setProductId(productID[k]);
                currentModel.setProductImage(productImage[k]);
                currentModel.setProductDetailUrl(productDetailUrl[k]);
                currentModel.setMaxPrice(maxPrice[k]);
                currentModel.setMinPrice(minPrice[k]);
                currentModel.setSellerId(sellerId[k]);
                currentModel.setDiscount(discount[k]);
                currentModel.setOriMaxPrice(oriMaxPrice[k]);
                currentModel.setOriMinPrice(oriMinPrice[k]);
                currentModel.setStartTime(startTime[k]);
                currentModel.setEndTime(endTime[k]);
                currentModel.setOrders(orders[k]);
                currentModel.setShopURL(shopURL[k]);
                currentModel.setProductTitle(productTitle[k]);
                arrayListProduct.add(currentModel);

            }
        }
        csvResult.createCSVFile(arrayListProduct);
    }
}
