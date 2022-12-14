package pl.seleniumdemo.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("27/04/2021", "29/04/2021");
        hotelSearchPage.setTravellers(1, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();


        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }

    @Test
    public void searchHotelWithoutNameTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("20/11/2022", "30/11/2022");
        hotelSearchPage.setTravellers(0, 1);
        hotelSearchPage.performSearch();
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
    }
}


