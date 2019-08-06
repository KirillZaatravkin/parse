import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

//create CSV file

public class CSV {

    private CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional()

        };
    }


    public void createCSVFile(ArrayList<ProductModel> productModels) throws IOException {
        StringWriter writer = new StringWriter();
        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        String[] header = new String[]{"productId", "productImage", "minPrice", "maxPrice", "oriMinPrice", "oriMaxPrice", "discount", "startTime", "endTime", "orders", "shopUrl", "productTitle","sellerId","productDetailUrl"};
        csvBeanWriter.writeHeader(header);
        for (ProductModel product : productModels) {
            csvBeanWriter.write(product, header, getProcessors());
        }
        csvBeanWriter.close();
        System.out.println(writer.toString());
    }
}

