import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        // URL of the e-commerce website to scrape
        String url = "https://example.com/products";

        try {
            // Connect to the website and get the HTML document
            Document doc = Jsoup.connect(url).get();

            // Extract product information
            Elements products = doc.select(".product"); // Adjust this selector based on the actual structure of the website

            // Open a CSV file to store the data
            FileWriter csvWriter = new FileWriter("products.csv");
            csvWriter.append("Name,Price,Rating\n"); // CSV header

            // Loop through each product and extract relevant information
            for (Element product : products) {
                String name = product.select(".name").text(); // Adjust the selector for product name
                String price = product.select(".price").text(); // Adjust the selector for product price
                String rating = product.select(".rating").text(); // Adjust the selector for product rating

                // Write the data to the CSV file
                csvWriter.append(name + "," + price + "," + rating + "\n");
            }

            // Close the CSV writer
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Scraping completed. Data saved to products.csv.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}