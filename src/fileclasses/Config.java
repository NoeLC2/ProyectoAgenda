package fileclasses;

import java.time.Month;
import java.time.Year;

public class Config {
		private Year year;
		private Month month;
		private String inputLang;
		private String outputLang;
		
		public Config(Year year, Month month, String incomingLeng, String outgoinLeng) {
	        this.year = year;
	        this.month = month;
	        this.inputLang = incomingLeng;
	        this.outputLang = outgoinLeng;
	    }

		public Year getYear() {
			return year;
		}

		public Month getMonth() {
			return month;
		}

		public String getInputLang() {
			return inputLang;
		}

		public String getOutputLang() {
			return outputLang;
		}
}
