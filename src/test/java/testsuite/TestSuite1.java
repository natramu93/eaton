package testsuite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import my.com.mimos.reusable.Keywords;
import my.com.mimos.utils.ExcelUtil;
import po.HomePage;
import po.UserPage;

public class TestSuite1 extends Master{
	@BeforeMethod
	public void bm() {
		Keywords.navigate("http://magento.com");
		Keywords.click(HomePage.user_icon);
	}
	
	@Test(dataProvider="positive_data")
	public void positive_login(String email, String pwd) {
		Keywords.type(UserPage.email_ip	, email);
		Keywords.type(UserPage.pwd_ip, pwd);
		Keywords.click(UserPage.signin_btn);
	}
	
	@DataProvider
	public Object[][] positive_data() throws FileNotFoundException, IOException{
		return ExcelUtil.DataTable("Data.xlsx", "positive_login");
	}
}
