package pageUIs.user.nopCommerce;

public class MyAccountPageUI {
    public static final String DYNAMIC_GENDER_RADIO = "//label[text()='%s']/preceding-sibling::input[@name='Gender']";
    public static final String DROPDOWN_DAY_BIRTH = "//select[@name='DateOfBirthDay']";
    public static final String DROPDOWN_MONTH_BIRTH = "//select[@name='DateOfBirthMonth']";
    public static final String DROPDOWN_YEAR_BIRTH = "//select[@name='DateOfBirthYear']";
    public static final String PRODUCT_REVIEW_TITLE = "(//div[@class='review-title']/strong)[1]";
    public static final String PRODUCT_REVIEW_TEXT = "//div[@class='review-text'][1]";
    public static final String PRODUCT_TITLE = "(//div[@class='review-info' ]//a)[1]";
}
