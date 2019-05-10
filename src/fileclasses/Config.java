package fileclasses;

public class Config {
		private int year;
		private int month;
		private String incomingLeng; 
		private String outgoinLeng;
		
		public Config(int year, int month, String incomingLeng, String outgoinLeng) {
	        this.year = year;
	        this.month = month;
	        this.incomingLeng = incomingLeng;
	        this.outgoinLeng = outgoinLeng;
	    }

		public int getYear() {
			return year;
		}

		public int getMonth() {
			return month;
		}

		public String getIncomingLeng() {
			return incomingLeng;
		}

		public String getOutgoinLeng() {
			return outgoinLeng;
		}
}
