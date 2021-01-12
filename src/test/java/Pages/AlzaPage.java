package Pages;

import models.Customer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

import static Base.BaseClass.BASE_URL;

public class AlzaPage {

    WebDriver driver;
    public String PRODUCT_NAME;
    public String PRODUCT_NAME2;
    public String PRODUCT_NAME_SUBSTRING;
    public String PRODUCT_NAME_ORDER1;
    public String GET_PRICE;
    public String TRIM_PRICE;
    public Float PRODUCT_PRICE;
    public String GET_PRICE_ORDER1;
    public String TRIM_PRICE_ORDER1;
    public Float PRODUCT_PRICE_ORDER1;
    public String MESSAGE;
    public String EXPECTED_MESSAGE = "Tovar bol pridaný do košíka";
    public String BASKET_COUNT;
    public String DELIVERY_HEADLINES;
    public String DELIVERY_HEADLINES_SUBSTRING_EXPECTED = "Doprava";
    public String GET_DELIVERY_PRICE;
    public String TRIM_DELIVERY_PRICE;
    public String REPLACE_DELIVERY_PRICE;
    public Float DELIVERY_PRICE;
    public String PAYMENT_DETAILS_FREE;
    public String PAYMENT_DETAILS_FREE_EXPECTED = "zadarmo";
    public String TITLE_ORDER3_PAGE;
    public String TITLE_ORDER3_PAGE_EXPECTED = "Dodacie údaje | Alza.sk";
    public String GET_TOTAL_AMOUNT;
    public String TRIM_TOTAL_AMOUNT;
    public String REPLACE_TOTAL_AMOUNT;
    public Float TOTAL_AMOUNT;
    public Float EXPECTED_TOTAL_AMOUNT;


    @FindBy(xpath = "//input[@id='edtSearch']")
    private WebElement SEARCH_BOX;

    @FindBy(xpath = "//div[@id='btnSearch']")
    private WebElement SEARCH_BUTTON;

    @FindBy(xpath = "//div[@class='bestitem index-0']/div[2]")
    private WebElement PRODUCT;

    @FindBy(xpath = "//div[@class='row bottom']")
    private WebElement BUTTON;

    @FindBy(xpath = "//a[@id='varBToBasketButton']")
    private WebElement TO_BASKET_BUTTON;

    @FindBy(xpath = "//a[@class='btnx normal green arrowedRight floatRight order2']")
    private WebElement TO_DELIVERY_DETAILS;

    @FindBy(xpath = "//span[@class='btnx normal grey js-close-button close-button']")
    private WebElement DECLINE_BUTTON;

    @FindBy(xpath = "//div[@class='deliveryInfoContainer']")
    private WebElement DELIVERY_CHECKBOX;

    @FindBy(xpath = "//input[@data-deliveryid='684']")
    private WebElement DELIVERY_TYPE_CHECKBOX;

    @FindBy(xpath = "//a[@class='btnx normal green dialogButton confirmDialog']")
    private WebElement AGREE_BUTTON;

    @FindBy(xpath = "//div[@class='paymentCheckboxContainer']")
    private WebElement PAYMENT_DETAILS;

    @FindBy(xpath = "//a[@id='confirmOrder2Button']")
    private WebElement CONFIRM_ORDER_2;

    @FindBy(xpath = "//a[@class='btnx normal green arrowedRight floatRight order4 next js-order3-continue']")
    private WebElement PAY_BUTTON;

    @FindBy(xpath = "//a[@class='btnx normal grey arrowedLeft floatLeft prev']")
    private WebElement BACK_BUTTON;


    public AlzaPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL);
    }

    public void findProduct() throws InterruptedException {

        SEARCH_BOX.sendKeys("televizor");
        SEARCH_BUTTON.click();
        Thread.sleep(2000);
        PRODUCT.click();
        Thread.sleep(2000);

    }

    public void createNameSubstring() {
        PRODUCT_NAME = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        PRODUCT_NAME_SUBSTRING = PRODUCT_NAME.substring(7);
        System.out.println("Product Name: " + PRODUCT_NAME_SUBSTRING);
    }

    public void productPrice() {
        GET_PRICE = driver.findElement(By.xpath("//span[@class='bigPrice price_withVat']"))
                .getText();
        TRIM_PRICE = GET_PRICE.substring(0, GET_PRICE.length() - 2);
        PRODUCT_PRICE = Float.parseFloat(TRIM_PRICE);
        System.out.println("Product price without currency symbol: " + PRODUCT_PRICE);
    }

    public void assertMessageOnCrossSellPage() throws InterruptedException {
        BUTTON.click();
        Thread.sleep(2000);
        MESSAGE = driver.findElement(By.xpath("//a[@class='productInfo__texts__message']")).getText();
        Assert.assertEquals(EXPECTED_MESSAGE, MESSAGE);
        System.out.println("Message: " + MESSAGE);
    }

    public void assetProductNameOnCrossSellPage() {
        PRODUCT_NAME2 = driver
                .findElement(By.xpath("//a[@class='productInfo__texts__productName']")).getText();
        System.out.println("Product name on Cross Sell Page: " + PRODUCT_NAME2);
        Assert.assertTrue(PRODUCT_NAME2.contains(PRODUCT_NAME_SUBSTRING));
    }

    public void continueToBasket() throws InterruptedException {
        TO_BASKET_BUTTON.click();
        Thread.sleep(2000);
        BASKET_COUNT = driver.findElement(By.xpath("//span[@class='count']")).getText();
        System.out.println("Products in basket: " + BASKET_COUNT);
        Assert.assertEquals("1", BASKET_COUNT);
    }

    public void assertProductNameAndPriceOnOrder1Page() {
        PRODUCT_NAME_ORDER1 = driver.findElement(By.xpath("//a[@class='mainItem']")).getText();
        System.out.println("Page Order1 Product Name: " + PRODUCT_NAME_ORDER1);
        Assert.assertTrue(PRODUCT_NAME_ORDER1.contains(PRODUCT_NAME_SUBSTRING));
        GET_PRICE_ORDER1 = driver.findElement(By.xpath("//td[@class='c5']")).getText();
        TRIM_PRICE_ORDER1 = GET_PRICE_ORDER1.substring(0, GET_PRICE_ORDER1.length() - 2);
        PRODUCT_PRICE_ORDER1 = Float.parseFloat(TRIM_PRICE_ORDER1);
        System.out.println("Page Order1 Product Price: " + PRODUCT_PRICE_ORDER1);
        Assert.assertEquals(PRODUCT_PRICE, PRODUCT_PRICE_ORDER1);
    }

    public void AssertDeliveryMessageAndPrice() throws InterruptedException {
        TO_DELIVERY_DETAILS.click();
        Thread.sleep(2000);
        DECLINE_BUTTON.click();
        Thread.sleep(3500);
        DELIVERY_HEADLINES = driver.findElement(By.xpath("//span[@class='m m2 sel']/strong")).getText();
        System.out.println("page Order2, step Doprava a platba: " + DELIVERY_HEADLINES);
        Assert.assertTrue(DELIVERY_HEADLINES.contains(DELIVERY_HEADLINES_SUBSTRING_EXPECTED));
        DELIVERY_CHECKBOX.click();
        Thread.sleep(2000);
        DELIVERY_TYPE_CHECKBOX.isSelected();
        GET_DELIVERY_PRICE = driver.findElement(By.xpath("//span[@class='delivery-item__price']")).getText();
        TRIM_DELIVERY_PRICE = GET_DELIVERY_PRICE.substring(0, GET_DELIVERY_PRICE.length() - 2);
        REPLACE_DELIVERY_PRICE = TRIM_DELIVERY_PRICE.replace(',', '.');
        DELIVERY_PRICE = Float.parseFloat(REPLACE_DELIVERY_PRICE);
        System.out.println("Delivery Price = " + DELIVERY_PRICE);
        AGREE_BUTTON.click();
        Thread.sleep(3500);
    }

    public void paymentDetails() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PAYMENT_DETAILS);
        Thread.sleep(500);
        PAYMENT_DETAILS.click();
        Thread.sleep(2500);
        PAYMENT_DETAILS_FREE = driver.findElement(By.xpath("//span[@class='paymentPrice']")).getText();
        System.out.println("Payment details - online card payment is: " + PAYMENT_DETAILS_FREE);
        Assert.assertEquals(PAYMENT_DETAILS_FREE, PAYMENT_DETAILS_FREE_EXPECTED);
        CONFIRM_ORDER_2.click();
        Thread.sleep(2000);
    }

    public void assertOrder3PageTitle() {
        TITLE_ORDER3_PAGE = driver.getTitle();
        System.out.println("Page title is: " + TITLE_ORDER3_PAGE);
        Assert.assertTrue(TITLE_ORDER3_PAGE.contains(TITLE_ORDER3_PAGE_EXPECTED));
    }

    public void assertTotalAmount() {
        EXPECTED_TOTAL_AMOUNT = (PRODUCT_PRICE + DELIVERY_PRICE);
        GET_TOTAL_AMOUNT = (driver
                .findElement(By.xpath("//div[@class='priceSummaryInnerBlock']/table/tbody/tr[2]/td[2]/span"))).getText();
        TRIM_TOTAL_AMOUNT = GET_TOTAL_AMOUNT.substring(0, GET_TOTAL_AMOUNT.length() - 2);
        REPLACE_TOTAL_AMOUNT = TRIM_TOTAL_AMOUNT.replace(',', '.');
        TOTAL_AMOUNT = Float.parseFloat(REPLACE_TOTAL_AMOUNT);
        System.out.println("Total Amount is: " + TOTAL_AMOUNT);
        System.out.println(EXPECTED_TOTAL_AMOUNT + "=" + TOTAL_AMOUNT + "FUNGUJE TO!!!");
    }
    public void ifTotalAmountIsOkPayIt() throws InterruptedException {
        if (TOTAL_AMOUNT < 500.0) {
            System.out.println("if funguje");
            driver.findElement(By.xpath("//input[@id='userEmail']")).clear();
            driver.findElement(By.xpath("//input[@id='userEmail']"))
                    .sendKeys("zuzana.semanova@accenture.com");
            Thread.sleep(3000);
        }
        else  {
            BACK_BUTTON.click();
        }}


    public void fillInForm(Customer customer){
             driver.findElement(By.xpath("//input[@id='name']"))
            .sendKeys(customer.getMeno());
       driver.findElement(By.xpath("//input[@id='street']"))
               .sendKeys(customer.getUlica());
       driver.findElement(By.xpath("//input[@id='city']"))
                .sendKeys(customer.getMesto());
       driver.findElement(By.xpath("//input[@id='zip']"))
                .sendKeys(customer.getZIP());
        driver.findElement(By.xpath("//input[@id='inpTelNumber']"))
                .sendKeys(customer.getTel_num());
        }

    }
