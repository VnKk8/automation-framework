package com.example.automationframework.pageobjects;

import com.example.automationframework.annotations.LazyProtoComponent;
import com.example.automationframework.asserters.Book;
import com.example.automationframework.enums.CommonKey;
import com.example.automationframework.models.bookapi.response.GetBookAvailabilityResponse;
import com.example.automationframework.pageobjects.core.BasePage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

@Getter
@LazyProtoComponent
@RequiredArgsConstructor
public class BookStorePage extends BasePage {

    private static final String bookViewButton =
            "//h3[contains(text(), '%s') and @class='book-title']/parent::*//button";
    private static final By dropDownButton =
            By.xpath("//button[@class='dropdown-toggle btn btn-secondary']");
    private static final By maxCapacity =
            By.xpath("//div[@class='dropdown-menu show']/a[text()='15']");
    private static final By availableCopies = By.xpath("//div[@class='book-detail-availableCopies-container']");
    private static final By addQuantity = By.xpath("//button[text()='+']");
    private static final By buyButton = By.xpath("//button[@class='buy-now-btn']");

    public void orderBook(GetBookAvailabilityResponse book) {
        selectMaxCapacity();
        clickOnBookTittle(book);
        addQuantityAndOrder();
    }

    public void verifyBookIsOrdered(GetBookAvailabilityResponse bookBeforeOrder,
                                    GetBookAvailabilityResponse bookAfterOrder) {
        Book.assertBookIsOrdered(bookBeforeOrder, bookAfterOrder);
    }

    private void selectMaxCapacity() {
        seleniumExecutor.clickOnElement(dropDownButton);
        seleniumExecutor.clickOnElement(maxCapacity);
    }

    private void clickOnBookTittle(GetBookAvailabilityResponse book) {
        seleniumExecutor.clickOnElement(bookViewButton, book.getBook().getTitle());
        String availableCopiesUI = seleniumExecutor.getTextFromElement(availableCopies);
        cucumberContext.saveOrUpdate(CommonKey.AVAILABLE_COPIES, availableCopiesUI);
    }

    private void addQuantityAndOrder() {
        seleniumExecutor.clickOnElement(addQuantity);
        seleniumExecutor.clickOnElement(buyButton);
    }
}
