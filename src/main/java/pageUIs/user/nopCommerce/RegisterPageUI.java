package pageUIs.user.nopCommerce;

public class RegisterPageUI {
    public static final String GENDRE_MALE_RADIO = "//input[@id='gender-male']";
    public static final String FISTNAME_TEXTBOX = "//input[@id='FirstName']";
    public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
    public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
    public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
    public static final String CONFIRMPASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
    public static final String REGISTER_BUTTON = "//button[@id='register-button']";
    public static final String SUCCESS_MESSAGE = "//div[@class='result' and text()='Your registration completed']";
    public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
    public static final String MESSAGE_EMAIL_ALREADY_EXITS = "//div[@class='message-error validation-summary-errors']//li";
    public static final String MESSAGE_ERROR_PASSWORD_LESS_THAN_6_CHAR = "//span[@id='Password-error']//li[text()='must have at least 6 characters']";
    public static final String MESSAGE_ERROR_PASSWORD_RULE = "//span[@id='Password-error']/p[text()='Password must meet the following rules: ']";
}
