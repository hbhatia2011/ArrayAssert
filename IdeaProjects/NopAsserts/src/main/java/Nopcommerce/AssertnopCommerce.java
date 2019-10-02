package Nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AssertnopCommerce extends Utils {
        SoftAssert softAssert = new SoftAssert();
        Loadprop loadprop = new Loadprop();

        @BeforeMethod
        //Method to Launch browser
        public void setUp() {
            toLaunchChromeBrowser();
            driver.get(loadprop.getProperty("url"));
        }
        //@AfterMethod
        //Method to Quit the browser
        //public void quitbrowser(){
         //   closebrowser();
        //}

        @Test
        public void UsershouldbeabletocomparetwoProductsSuccessfully() {
            //click on Apple MacBook Pro 13-inch compare list
            clickOnElement(By.xpath("//input[contains(@onclick,'4\"')]"));
            //click on Homepage sign
            clickOnElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]"));
            //click on HTC to compare list
            clickOnElement(By.xpath("//input[contains(@onclick,'18\"')]"));
            //click on compare list link to check the added products
            clickOnElement(By.xpath("//p/a[@href=\"/compareproducts\"]"));
            //To clear compare list
            clickOnElement(By.xpath("//a[@class=\"clear-list\"]"));
            //Assert to test the actual and expected results
            String actualcompare = "You have no items to compare.";
            String expectedcompare = driver.findElement(By.xpath("//div[@class=\"no-data\"]")).getText();
            softAssert.assertEquals(expectedcompare,actualcompare);
        }

        @Test
        public void UserShouldbeabletoAddcommentstoNewReleasesandSeethecomments(){
            //click on New online Store News
            clickOnElement(By.xpath("//div[3]/a[@href=\"/new-online-store-is-open\"]"));
            //Enter the title of the comment
            EnterText(By.className("enter-comment-title"),loadprop.getProperty("Title"));
            //Enter the comment
            EnterText(By.className("enter-comment-text"),loadprop.getProperty("Comment"));
            //Submit comment
            clickOnElement(By.name("add-comment"));
            //to confirm the comment added successfully - Assert
            String Expectedresults="News comment is successfully added.";
            boolean Actualresult=driver.findElement(By.className("result")).isDisplayed();
            Assert.assertTrue(Actualresult,Expectedresults);
            //to view the comment is present on the bottom page
            Scrollupanddown(By.xpath("//div[497]/div[2]/div[3]/p[@class=\"comment-text\"]"));
        }

        @Test
        public void UserShouldbeabletoSearchNikekeywordintheSearchbar(){
         //Enter Nike in the Search tool
         EnterText(By.id("small-searchterms"),loadprop.getProperty("Search"));
         //click search button
         clickOnElement(By.xpath("//input[@value=\"Search\"]"));

         //Assert using the Array list
         List<WebElement> al = driver.findElements(By.xpath("//div[@class=\"item-grid\"]"));
            System.out.println(al.size());
            int count = 0;
            for (WebElement e : al) {
                if (e.getAttribute("outerHTML").contains(loadprop.getProperty("text"))) {
                    count++;
                    System.out.println(e.getText());
                    Assert.assertTrue((e.getText()).contains(loadprop.getProperty("text")));
                } else {
                    System.out.println("No Nike word; " + e.getText());
                }
                System.out.println(count);
                Assert.assertEquals(al.size(), count);
            }
        }
    }

