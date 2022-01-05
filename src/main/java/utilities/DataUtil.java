package utilities;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;
	
	public static DataUtil getData() {
		return new DataUtil();
	}
	
	public DataUtil() {
		faker =new Faker();
	}
	
	public String getFistName() {
		return faker.name().firstName();
	}
	
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getUserName() {
		return faker.name().username();
	}
	
	public String getPassWord() {
		return faker.internet().password();
	}
	
	public String getPassWord(int minimumLength, int maximumLength,boolean includeUppercase,boolean includeSpecial) {
		return faker.internet().password(minimumLength, maximumLength, includeUppercase, includeSpecial);
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public int getRandomNumber(int maximum,int minimum) {
		return faker.number().numberBetween(minimum, maximum);
	}
	
	
}
