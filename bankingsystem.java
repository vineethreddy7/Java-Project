//bankingsystem.java
package javaproject;
import java.util.*;


import java.io.*;


public class bankingsystem { //creating class for implementation

	public static void main(String[] args) throws IOException { //main method with exception
			Scanner sc=new Scanner(System.in);
			
			ArrayList<Integer> accno=new ArrayList<Integer>();//creating array list for account number
			ArrayList<Integer> bal=new ArrayList<Integer>();//creating array list for balance
			ArrayList<String> name=new ArrayList<String>();//creating array list for names
			ArrayList<Integer> age=new ArrayList<Integer>();//creating array list for age
			ArrayList<String> type=new ArrayList<String>();//creating array list for type of account
			
			bankaccount b[]=new bankaccount[3];//initializing super class
			
			
			String filename="C:\\Users\\Public\\bank_details1.txt";//assigning file path and filename
			
			File f=new File(filename);
			
			if (f.createNewFile())//checking if file exists
			{
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
			
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			Random r=new Random();
			
			try(br){
				
				 String line=br.readLine();//reading line

				 
				 while(line!=null)
				 {
					 String details[]=line.split(",");//retrieving data from Comma Separated Values
					 accno.add(Integer.parseInt(details[0]));//adding account numbers to array list
					 name.add(details[1]);//adding names to array list
					 age.add(Integer.parseInt(details[2]));//adding age to array list
					 bal.add(Integer.parseInt(details[3]));//adding balances to array list
					 type.add(details[4]);//adding account types to array list
				 
					 line=br.readLine();
				 }
				 
			br.close();
			
			}catch (IOException ioe) {
	            ioe.printStackTrace();
			}

			int i,ch,j,dep=0,k;
			do {
			System.out.println("\nEnter 1 if you are a new customer and want to create a new account"+"\nEnter 2 if you are an existing customer\nEnter 0 to Confirm and Exit");
			ch=sc.nextInt();
	
			switch(ch)
			{
			case 0:System.out.println("Exiting the system");break;
			case 1: int c=r.nextInt(10000); //creating random account number; case to add new customer data
					boolean a=accno.contains(c);//checking if account number already exists
					if(a==true)
					{
							System.out.println("Technical Error! Try again");
							break;
						}
						else if(a==false)//if account number does not exist
						{
							accno.add(c);
							System.out.println("Enter your name");
							name.add(sc.next());
							System.out.println("Enter your age");
							age.add(sc.nextInt());
							System.out.println("Enter the amount you want to deposit");
							bal.add(sc.nextInt());
							System.out.println("There are four types of account you can choose from"+"\n1.Savings\n2.Chequein\n3.Joint\n4.Credit");
							System.out.println("Choose the account you want");
							int ac=sc.nextInt();
							
							switch(ac)//entering type of account
							{
							case 1:type.add("savings");break;
							case 2:type.add("chequein");break;
							case 3:type.add("joint");break;
							case 4:type.add("credit");break;
							}
							System.out.println("Account created successfully\nYour account number is "+c);
							break;
						}
					
					break;
			case 2: System.out.println("Enter account number");//case to perform various actions on existing accounts
					int am=sc.nextInt();
					do {
						System.out.println("\n1.Display Current Balance\n2.Deposit Money\n3.Draw Money\n4.Transfer money to others accounts\n5.Pay utility bills\n6.Make changes to your account\n7.View Profile\n0.Confirm and Exit\nEnter choice");
						ch=sc.nextInt();
						
						switch(ch)
						{
						case 0: System.out.println("Exiting the system");break;
						case 1:	j=option(accno,am);
								System.out.println("Your current balance is "+bal.get(j));//displaying current balance
								break;
						case 2: j=option(accno,am);//depositing amount to the account
								System.out.println("Enter amount you want to deposit");
								dep=bal.get(j)+sc.nextInt();
								bal.set(j,dep);
								System.out.println("Your new balance is "+bal.get(j));break;
						case 3: j=option(accno,am);//to draw the amount from the account
								System.out.println("Enter amount you want to draw");
								int amo=sc.nextInt();
								if(type.get(j).equals("savings"))
								{
								b[0]=new savings(accno.get(j),name.get(j),age.get(j),bal.get(j));
								dep=b[0].balance(bal.get(j))-amo;
							
								}
								else if(type.get(j).equals("chequein"))
								{
								b[0]=new chequein(accno.get(j),name.get(j),age.get(j),bal.get(j));
								dep=b[0].balance(bal.get(j))-amo;
								
								}
								else if(type.get(j).equals("joint"))
								{
								b[0]=new joint(accno.get(j),name.get(j),age.get(j),bal.get(j));
								dep=b[0].balance(bal.get(j))-amo;
								
								}
								else if(type.get(j).equals("credit"))
								{
								b[0]=new credit(accno.get(j),name.get(j),age.get(j),bal.get(j));
								dep=b[0].balance(bal.get(j))-amo;
								
								}
								bal.set(j,dep);
								System.out.println("Your new balance is "+bal.get(j));
								break;
						case 4: System.out.println("Enter the account you want to transfer the amount");//transferring amount from one account to another
								int as1=sc.nextInt();
								k=option(accno,as1);
								j=option(accno,am);
								System.out.println("Enter the amount you want to transfer");
								amo=sc.nextInt();
								dep=bal.get(j)-amo;
								bal.set(j, dep);
								dep=bal.get(k)+amo;
								bal.set(k,dep);
								System.out.println("Amount "+amo+" transfered from "+accno.get(j)+" to "+accno.get(k));
								
								break;
						case 5: j=option(accno,am);
								System.out.println("Enter the bill you want to pay");//case to pay the bills through an account
								String bill=sc.next();
								System.out.println("Enter bill amount");
								amo=sc.nextInt();
								if(type.get(j).equals("chequein") || type.get(j).equals("credit"))
								{
									dep=bal.get(j)-amo;
									System.out.println(bill+" bill payment successful");
								}
								else if(type.get(j).equals("savings"))
								{
									b[0]=new savings(accno.get(j),name.get(j),age.get(j),bal.get(j));
									dep=b[0].balance(bal.get(j))-amo;
									System.out.println(bill+" bill payment successful");
								}
								else if(type.get(j).equals("joint"))
								{
									System.out.println("Bills cannot be paid using joint account.\nChange either to chequein or credit account to use this feature");
								}
								bal.set(j, dep);
								System.out.println("Your remaining balance is "+bal.get(j));
								break;
						case 6: j=option(accno,am);//making changes to the account
								do
								{
									System.out.println("\nEnter the changes that you want to make\n1.Name\n2.Age\n3.Balance\n4.Account type\n0.Confirm and Exit\nEnter choice");
									ch=sc.nextInt();
									switch(ch)
									{
									case 1:System.out.println("Your current name is "+name.get(j)+"\nEnter the name you want to update ");//updating name
											name.set(j, sc.next());break;
									case 2:System.out.println("Your current age is "+age.get(j)+"\nEnter the age you want to update ");//updating age
											age.set(j, sc.nextInt());break;
									case 3:System.out.println("Your current balance is "+bal.get(j)+"\nEnter the balance you want to update");//updating balance
											bal.set(j, sc.nextInt());break;
									case 4:
											do{//updating account type
												System.out.println("Your current account type is "+type.get(j)+"\nEnter the account type you want to update to\n1.Savings\n2.Joint\n3.Chequein\n4.Credit\n0.Exiting the system\nEnter choice");
												ch=sc.nextInt();		

												switch(ch)
												{
												case 1:type.set(j,"savings");break;
												case 2:type.set(j, "joint");break;
												case 3:type.set(j, "chequein");break;
												case 4:type.set(j, "credit");break;
												case 0:System.out.println("Exit");break;
												
											}
											}while(ch!=0);break;
										
									case 0:System.out.println("Exiting the system");

									}
									
								}while(ch!=0);break;
						case 7: j=option(accno,am);//case to display user profile
								System.out.println("Name: "+name.get(j)+"\nAge: "+age.get(j)+"\nBalance: "+bal.get(j)+"\nAccount Type: "+type.get(j));
								
								
								
					
						}
						} while (ch!=0);
						break;
			
			}
			
			
			}while(ch!=0);
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
			for(i=0;i<accno.size();i++)
			{
					
					bw.write(accno.get(i)+","+name.get(i)+","+age.get(i)+","+bal.get(i)+","+type.get(i));//writing data to the file
					bw.newLine();
			}
			bw.close();
}

	
	static int option(ArrayList<Integer> list,int acc)//method to get the index of the search account
	{
		for(int i=0;i<list.size();i++)
		{
			if(acc==list.get(i))
			{
				return i;
			}
		}
		return -1;
	}

}


