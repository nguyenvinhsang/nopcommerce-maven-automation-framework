package pageUIs.user.nopCommerce;

public class BaseUI {
    public static final String DYNAMIC_FOOTER_LINK="//div[@class='footer-upper']//a[text()='%s']";
    public static final String DYNAMIC_HEADER_LINK="//div[@class='header-links']//a[text()='%s']";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BOX ="//input[@id='%s']/following-sibling::span/span";
    public static final String DYNAMIC_TEXT_BOX="//input[@id='%s']";
    public static final String DYNAMIC_TEXT_BOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::input";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(text(),'%s')]";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::span/span";
    public static final String DYNAMIC_MENU_MY_ACCOUNT_BY_TEXT = "//div[@class='master-wrapper-content']//a[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::select";
    public static final String ICON_LOADING = "//span[@id='states-loading-progress']";
    public static final String DYNAMIC_SUMMARY_ERROR_MASSAGE = "//div[@class='message-error validation-summary-errors']//li";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BOX_NOT_DISPLAY_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::span[@class='field-validation-valid']";
    public static final String DYNAMIC_PRODUCT_TITLE_NAME = "//h2[@class='product-title']/a[text()='%s']";
    public static final String DYNAMIC_LINK_BY_TEXT = "//a[text()='%s']";
}
