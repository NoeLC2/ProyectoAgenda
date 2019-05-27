package fileclasses;

import java.time.Month;
import java.time.Year;

public class Config {
		private Year year;
		private Month month;
		private String inputLang;
		private String outputLang;
		
		public Config(Year year, Month month, String inputLang, String outputLang) {
	        this.year = year;
	        this.month = month;
	        this.inputLang = inputLang;
	        this.outputLang = outputLang;
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
