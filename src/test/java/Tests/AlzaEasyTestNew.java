package Tests;

import Base.BaseClass;
import Pages.AlzaPage;
import models.Customer;
import org.junit.Test;

public class AlzaEasyTestNew extends BaseClass {



    @Test
    public void testAlzaPage() throws InterruptedException {
        AlzaPage alzaPage = new AlzaPage();
        Customer customer1 = new Customer("Zuzana Semanova", "Ziarska", "Bratislava", "81103", "0917888000");
        alzaPage.openPage();
        alzaPage.findProduct();
        alzaPage.createNameSubstring();
        alzaPage.productPrice();
        alzaPage.assertMessageOnCrossSellPage();
        alzaPage.assetProductNameOnCrossSellPage();
        alzaPage.continueToBasket();
        alzaPage.assertProductNameAndPriceOnOrder1Page();
        alzaPage.AssertDeliveryMessageAndPrice();
        alzaPage.paymentDetails();
        alzaPage.assertOrder3PageTitle();
        alzaPage.assertTotalAmount();
        alzaPage.ifTotalAmountIsOkPayIt();
        alzaPage.fillInForm(customer1);

            }
}
