package pageUIs.user.nopCommerce;

public class BaseUI {
    public static final String DYNAMIC_HEADER_LINK="//div[@class='header-links']//a[contains(.,'%s')]";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BOX ="//input[@id='%s']/following-sibling::span/span";
    public static final String DYNAMIC_TEXT_BOX="//input[@id='%s']";
    public static final String DYNAMIC_TEXT_BOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::input";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(text(),'%s')]";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::span/span";
    public static final String DYNAMIC_MENU_MY_ACCOUNT_BY_TEXT = "//div[@class='master-wrapper-content']//a[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::select";
    public static final String DYNAMIC_DROPDOWN_BY_SPAN = "//span[contains(text(),'%s')]/following-sibling::select";
    public static final String ICON_LOADING = "//span[@id='states-loading-progress']";
    public static final String DYNAMIC_SUMMARY_ERROR_MASSAGE = "//div[@class='message-error validation-summary-errors']//li";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BOX_NOT_DISPLAY_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::span[@class='field-validation-valid']";
    public static final String DYNAMIC_PRODUCT_TITLE_NAME = "//h2[@class='product-title']/a[text()='%s']";
    public static final String PRODUCT_TITLE_NAME = "//h2[@class='product-title']/a";
    public static final String DYNAMIC_LINK_BY_TEXT = "//a[text()='%s']";
    public static final String SEARCH_TEXT_BOX = "//input[@id='small-searchterms']";
    public static final String MESSAGE_PRODUCT_WRAPPER_WARNING = "//div[@class='products-wrapper']/div";
    public static final String DYNAMIC_CHECK_BOX_OR_RADIO_BY_LABEL = "//label[contains(text(),'%s')]/preceding-sibling::input";
    public static final String DYNAMIC_SIDE_2_BY_TEXT = "//div[@class='side-2']//strong[text()='%s']/parent::div[@class='title']/following-sibling::div//a[contains(.,'%s')]";
    public static final String DYNAMIC_HEADER_MENU = "(//div[@class='header-menu']//a[contains(text(),'Computers')])[1]";
    public static final String PRODUCT_PRICE = "//span[@class='price actual-price']";
    public static final String SUCCESS_MESSAGE = "//div[@class='bar-notification success']/p";
    public static final String BUTTON_CLOSE_SUCCESS_MESSAGE = "//div[@class='bar-notification success']/span";
    public static final String PAGE_BODY_NO_DATA = "//div[@class='page-body']/div[@class='no-data']";
    public static final String DYNAMIC_ADD_COMPARE_BUTTON = "//a[text()='%s']/parent::h2[@class='product-title']//following-sibling::div[@class='add-info']//button[@class='button-2 add-to-compare-list-button']";
    public static final String DYNAMIC_FOOTER_LINK = "//div[@class='footer']//a[text()='%s']";
    public static final String DYNAMIC_TABLE = "//table[@class='%s']";
}
