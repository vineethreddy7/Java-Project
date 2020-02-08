//bankaccount.java
package javaproject;

abstract class bankaccount {//creating super class bankaccount with 4 attributes
		protected int accno;
		protected String name;
		protected int age;
		protected int bal;
		
		//the constructors
		public bankaccount(int accno,String name,int age,int bal)
		{
			this.accno=accno;
			this.name=name;
			this.age=age;
			this.bal=bal;
		}
		
		//getters
		public int getaccno(){return accno;}
		public int getbal(){return bal;}
		public String getname() {return name;}
		public int getage() {return age;}
		
		//setters
		public void setaccno(int accno){this.accno=accno;}
		public void setbal(int bal) {this.bal=bal;}
		public void setage(int age) {this.age=age;}
		public void setname(String name) {this.name=name;}
		
		//to string
		public String toString()
		{
			return accno+" "+name+" "+age+" "+bal;
		}
		abstract int balance(int b);//creating abstract method
		
		
		
}
