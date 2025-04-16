import com.sun.jdi.InternalException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class skelbiult {
    private WebDriver driver;

    @Test
    public void getAvgPrices() throws InterruptedException {
        driver();
        goToWebsite();
        acceptCookies();
        searchAd();
        countAd();
    }

    private void driver() {
        driver = new ChromeDriver();
    }

    private void goToWebsite() {
        driver.get("https://skelbiu.lt");
    }

    private void acceptCookies() {
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    private void searchAd() {
        driver.findElement(By.id("searchKeyword")).sendKeys("verpimo ratelis");
        driver.findElement(By.id("searchButton")).click();
    }

    private void countAd() throws InterruptedException {
            Thread.sleep(500);
                                                                         //*[@id="body-container"]/div[2]/div[1]/ul/li/span
        String adsQuatity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
        double sum = 0;
        int count = 0;
        boolean nextPage = true;
        while (nextPage) {
            WebElement container = driver.findElement(By.className("standard-list-container"));
            List<WebElement> contentBlocks = container.findElements(By.className("content-block"));
            Thread.sleep(1000);

            for (int i = 0; i < contentBlocks.size(); i++) {
                double price = getPrice(contentBlocks.get(i));
                if (price > 0) {
                    sum += price;
                    count++;
                }
            }
            nextPage = nextPage();
        }

        System.out.println("skelbimu pavyko rasti: " + adsQuatity + ", daikto vidurkio kaina: " + sum / count + ".");
    }

    public double getPrice(WebElement block) {
        try {
            String price = block.findElement(By.className("price")).getText();
            price = price.replaceAll("\\D+", "");
            return Double.parseDouble(price);
        } catch (Exception e) {
            System.out.println("nebuvo kainos");
        }
        return 0;
    }

    private boolean nextPage() {
        try { // spausti kita puslapi
            driver.findElement(By.linkText("»")).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

//        WebDriver driver = new ChromeDriver();
//        driver.get("https://skelbiu.lt");
//        Thread.sleep(500);
//        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
//
//        driver.findElement(By.id("searchKeyword")).sendKeys("telefonas");
//        driver.findElement(By.id("searchButton")).click();
//        Thread.sleep(500);
//        String adsQuatity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
//        System.out.println("Radome pirmame puslapyje " + adsQuatity + " skelbimus");
//
//        double sum = 0;
//        int count = 0;
//        while (true) {
//            WebElement container = driver.findElement(By.className("standard-list-container"));
//            List<WebElement> contentBlocks = container.findElements(By.className("content-block"));
//            Thread.sleep(1000);
//
//            for (int i = 0; i < contentBlocks.size(); i++) {
//                String price = "0";
//                try {
//                    price = contentBlocks.get(i).findElement(By.className("price")).getText();
//                } catch (Exception e) {
//                    System.out.println("nebuvo kainos");
//                }
//                price = price.replaceAll("\\D+", "");
//                try {
//                    sum += Double.parseDouble(price);
//                    count++;
//                } catch (Exception e) {
//                }
//            }
//            try { // spausti kita puslapi
//                driver.findElement(By.linkText("»")).click();
//            } catch (Exception e) {
//                break;
//            }
//        }


//        System.out.println("Kainu vidurkis: " + sum / count);
//        Thread.sleep(500);
// }

