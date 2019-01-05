package testsuite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import my.com.mimos.reusable.Keywords;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	  MethodListener.class })
public class Master {
		{
        System.setProperty("atu.reporter.config", "atu.properties");
		}
		@BeforeClass
		public void bc() {
			Keywords.initialize();
		}
		
		@AfterClass
		public void ac() {
			Keywords.quit();
		}
}
