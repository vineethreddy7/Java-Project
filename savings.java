//savings.java
package javaproject;

class savings extends bankaccount {			//creating sub class savings from super class bankaccount
		public savings(int accno,String name,int age,int bal)
		{
			super(accno,name,age,bal); //calling super class attributes
		}
		
		//getters
		public int getaccno(){return super.accno;}
		public int getbal(){return super.bal;}
		public String getname() {return super.name;}
		public int getage() {return super.age;}

		
		//setters
		public void setaccno(int accno){this.accno=super.accno;}
		public void setbal(int bal) {this.bal=super.bal;}
		public void setage(int age) {this.age=super.age;}
		public void setname(String name) {this.name=super.name;}

		
		//to string
		public String toString()
		{
			return super.toString();
		}
		
		public int balance(int b) //method to calculate balance after deducting fees
		{
			System.out.println("3% fees will be applicable");
			return b-(b*3/100);
		}
}
