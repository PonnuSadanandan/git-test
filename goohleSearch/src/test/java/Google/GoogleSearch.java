package Google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleSearch {
    WebDriver driver ;
    String keywordTobeSearched = "apple";
    By googleSearchFieldID = By.cssSelector("input.gLFyf.gsfi");
    By linkClassNames = By.xpath("//a[contains(@href,apple)]/div/cite");
    //By searchButtonID = By.xpath("//input[@value = 'Google Search']");
    @Test
    public void testSearchKeyword() {
        navigateHomwPage();
        searchForKeyword();
        clickOnFirstLink();

    }

    private void clickOnFirstLink() {
       List<WebElement> searchList = driver.findElements(linkClassNames);
       String urlToBeClicked = searchList.get(0).getText();
        System.out.println(urlToBeClicked);
        searchList.get(0).click();
        boolean check = driver.getCurrentUrl().contains(urlToBeClicked);
        Assert.assertTrue(check);
    }

    private void searchForKeyword() {
        Actions action = new Actions(driver);
        //driver.findElement(googleSearchFieldID).sendKeys(keywordTobeSearched);
        action.moveToElement(driver.findElement(googleSearchFieldID)).sendKeys(keywordTobeSearched).sendKeys(Keys.ENTER).build().perform();
    }

    private void navigateHomwPage() {
        driver.get("https://www.google.com/");

    }

    @BeforeTest
    public void Setup(){
        String path = "/Users/ponnusadanandan/Downloads/chromedriver";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
    }
}
