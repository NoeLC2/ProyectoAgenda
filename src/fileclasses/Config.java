package fileclasses;

public class Config {
		private int year;
		private int month;
		private String inputLang;
		private String outputLang;
		
		public Config(int year, int month, String incomingLeng, String outgoinLeng) {
	        this.year = year;
	        this.month = month;
	        this.inputLang = incomingLeng;
	        this.outputLang = outgoinLeng;
	    }

		public int getYear() {
			return year;
		}

		public int getMonth() {
			return month;
		}

		public String getInputLang() {
			return inputLang;
		}

		public String getOutputLang() {
			return outputLang;
		}
}
