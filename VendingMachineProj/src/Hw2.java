import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** Carlo Rodriguez
 *  10/11/2017
 *  Object Oriented Design
 */

/** 
 * A product in the Vending Machine
 */
class Product {
	private String name; 
	private String cost; 
	private int quantity; 
	public Product next; 
	/**
	 * Construct a product object
	 * @param name: the name of the product
	 * @param cost: the cost of the product
	 */
	public Product(String name, String cost) {
		this.name = name; 
		this.cost = cost; 
		quantity = 10; 
		next = null;
		
	}
	/**
	 * Get the quantity of product		
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Set the quantity of product
	 * @param n: the quantity to set
	 */
	public void setQuantity(int n) {
		quantity = n; 
	}	
	/**
	 * Dispense product to user
	 */
	public void decQuantity() {
		quantity = quantity - 1; 
	}
	/**
	 * Get the cost of product
	 * @return the cost of product
	 */
	public String getCost() {
		return cost; 
	}
	/**
	 * Get the name of the product
	 * @return the name of product
	 */
	public String getName() {
		return name; 
	}
}

/**
 * Handles products to add, list, or find specific 
 * products
 */
class handleProduct {
	/**
	 * Adds a product to the vending machine
	 * @param machine: the vending machine
	 * @param product: the product to add
	 */
	static public void addProduct(VendingMachine machine, Product product) {
		Product n = machine.getHead(); 
		if (n == null) {
			machine.setHead(product);
			return; 
		}
		while(n.next != null) {
			n = n.next; 
		}
		n.next = product; 
		return; 
	}
	/**
	 * Lists all products in the vending machine
	 * @param machine: the vending machine
	 */
	static public void listProducts(VendingMachine machine) {
		Product n; 
		n = machine.getHead();
		while (n != null) {
			System.out.println(n.getName());
			n = n.next;
		}
	}
	/**
	 * Finds a specific product in the vending machine
	 * @param name: name of the product to find
	 * @param machine: the vending machine
	 * @return the product object searched for
	 */
	static public Product findProduct(String name, VendingMachine machine) {
		Product n;
		n = machine.getHead(); 
		while (n != null) {
			if (name.equals(n.getName())) {
				return n; 
			}
			else 
				n = n.next; 
		}
		return n; 
	}
	
	
}
/**
 * A vending machine that stores products and coins.
 */
class VendingMachine {
	private Product head = null; 
	private int dime = 0;
	private int nickel = 0;
	private int quarter = 0; 
	/**
	 * Gets the first product
	 * @return the first product
	 */
	public Product getHead() {
		return head; 
	}
	/**
	 * Sets the first product
	 * @param n: Product object to be stored
	 */
	public void setHead(Product n) {
		head = n; 
	}
	/**
	 * Sets the number of dimes
	 * @param n: The number of dimes to be set
	 */
	public void setDime(int n) {
		dime = n; 
	}
	/**
	 * Sets the number of nickels
	 * @param n: The number of nickels to be set
	 */
	public void setNickel(int n) {
		nickel = n; 
	}
	/**
	 * Sets the number of quarters
	 * @param n: The number of quarters to be set
	 */
	public void setQuarter(int n) {
		quarter = n; 
	}
	/**
	 * Gets the number of dimes
	 * @return the number of dimes
	 */
	public int getDime() {
		return dime; 
	}
	/**
	 * Gets the number of nickels
	 * @return the number of nickels
	 */
	public int getNickel() {
		return nickel; 
	}
	/**
	 * Gets the number of quarters
	 * @return the number of quarters
	 */
	public int getQuarter() {
		return quarter; 
	}
	/**
	 * Increments dimes based off of user input
	 * @param n: The amount of dimes entered into the machine
	 */
	public void incDime(int n) {
		dime = dime + n;
	}
	/**
	 * Increments nickels based off of user input
	 * @param n: The amount of nickels entered into the machine
	 */
	public void incNickel(int n) {
		nickel = nickel + n;
	}
	/**
	 * Increments quarters based off of user input
	 * @param n: The amount of quarters entered into the machine
	 */
	public void incQuarter(int n) {
		quarter = quarter + n; 
	}
	/**
	 * Decrements dimes for change
	 */
	public void decDime() {
		dime = dime - 1; 
	}
	/**
	 * Decrements nickels for change
	 */
	public void decNickel() {
		nickel = nickel - 1; 
	}
	/** 
	 * Decrements quarters for change
	 */
	public void decQuarter() {
		quarter = quarter - 1; 
	}
}
/**
 * A checker class to validate user input 
 */
class Checker {
	/**
	 * Checks if the machine has no coins
	 * @param machine: the vending machine
	 * @return true if there are no coins, or false if coins exist
	 */
	static public boolean noCoins(VendingMachine machine) {
		if ((machine.getDime() == 0) || (machine.getNickel() == 0) || 
			(machine.getQuarter() == 0))
			return true; 
		else 
			return false; 
	}
	/**
	 * Checks if the product is empty
	 * @param n: the product to check
	 * @return true if there are no products, and false if there are
	 * still units in the machine
	 */
	static public boolean isEmpty(Product n) {
		if (n.getQuantity() == 0)
			return true;
		else 
			return false; 
	}
	/**
	 * Checks if the product exists within the machine
	 * @param name: the name to look for
	 * @param machine: the vending machine
	 * @return true if that product is in the machine, and false if there
	 * is no such product
	 */
	static public boolean isExist(String name, VendingMachine machine) {
		Product n;
		n = handleProduct.findProduct(name, machine);
			if (n == null) 
				return false; 
			else
				return true; 
	}
	/**
	 * Checks to see if the user entered a valid money cost for the
	 * product (i.e., 1.25, 1.35, and not 1.345, 1.34)
	 * @param cost: a Float object that holds the cost entered in float
	 * @return: true if the cost is valid, and false if the cost is invalid
	 */
	static public boolean validCost(Float cost) {
		Float d = cost; 
		if (d.floatValue() < 0) {
			return false; 
		}
		BigDecimal x = new BigDecimal(d.toString());
		BigDecimal y = new BigDecimal("0.05");
		
		BigDecimal rem = x.remainder(y);
		if ((rem.floatValue() == 0.0))
		   return true; 
		else
		   return false; 
	}
}
/**
 * Handles product and coin restocking, as well as
 * dispensing products and giving change
 */
class Operator {
	/**
	 * Restocks the quantity of a product
	 * @param n: the product to restock
	 * @param choice: the amount to set the quantity to
	 */
	static public void restockQuantity(Product n, int choice) {
			n.setQuantity(choice);
			return; 
	}
	/**
	 * Restocks the coins into the machine. Sets 10 to each
	 * @param machine: the vending machine
	 */
	static public void restockCoins(VendingMachine machine) {
		machine.setNickel(10);
		machine.setDime(10);
		machine.setQuarter(10);	
	}
	/**
	 * Gives change to the user, and dispenses product from the machine
	 * @param machine: the vending machine
	 * @param sum: the sum of the money user inputed
	 * @param costf: the cost of the product in float
	 * @param nick: the number of nickels user inputed
	 * @param d: the number of dimes user inputed
	 * @param q: the number of quarters user inputed
	 * @param n: the product to dispense
	 * @param h: checks if the user is hydrated
	 * @return 1 if transaction is successful, or -1 if it is not
	 */
	static public int giveChangeandProduct(VendingMachine machine, float sum, float costf,
											int nick, int d, int q, Product n, int h) {
		Float sumObj = new Float(sum);
		Float costfObj = new Float(costf);
		BigDecimal sumB = new BigDecimal(sumObj.toString());
		BigDecimal costfB = new BigDecimal(costfObj.toString());
		sumB = sumB.subtract(costfB);
		sumB = sumB.setScale(2, RoundingMode.UNNECESSARY);
		machine.incNickel(nick);
		machine.incDime(d);
		machine.incQuarter(q);
		if (sumB.compareTo(BigDecimal.ZERO) == 0) {
			n.decQuantity();
			System.out.println("You collect your drink and your change.");
			System.out.println("You gave the exact cost. No change received.");
			if (h == 0)
				System.out.println("You gain the \"Hydrated\" buff!");
			return 1; 
		}
		else {
			BigDecimal quarter = new BigDecimal("0.25");
			BigDecimal dime = new BigDecimal("0.10");
			BigDecimal nickel = new BigDecimal("0.05");
			int qflag = 1; 
			int nflag = 1;
			int dflag = 1; 
			BigDecimal temp = new BigDecimal("0.00"); 
			int tempq = 0;
			int tempd = 0;
			int tempnick = 0; 
			while((temp.floatValue() != sumB.floatValue()) && (qflag == 1) && (machine.getQuarter() != 0))	{
				temp = temp.add(quarter);
				tempq = tempq + 1; 
				machine.decQuarter();
				if (temp.floatValue() > sumB.floatValue()) {
					qflag = 0; 
					tempq = tempq - 1; 
					temp = temp.subtract(quarter);
					machine.incQuarter(1);
				}
			}
			while((temp.floatValue() != sumB.floatValue()) && (dflag == 1) && (machine.getDime() != 0)) {
				temp = temp.add(dime);
				tempd = tempd + 1; 
				machine.decDime();
				if (temp.floatValue() > sumB.floatValue()) {
					dflag = 0;
					tempd = tempd - 1; 
					temp = temp.subtract(dime);
					machine.incDime(1);
				}
			}
			while((temp.floatValue() != sumB.floatValue()) && (nflag == 1) && (machine.getNickel() != 0)) {
				temp = temp.add(nickel);
				tempnick = tempnick + 1; 
				machine.decNickel();
				if (temp.floatValue() > sumB.floatValue()) {
					nflag = 0;
					tempnick = tempnick - 1; 
					temp = temp.subtract(nickel);
					machine.incNickel(1);
				}
			}
			if (temp.equals(sumB)) {
				n.decQuantity();
				System.out.println("You collect your drink and your change.");
				System.out.println("You receive " 
				+ tempnick + " nickel(s), " + tempd + " dime(s), and " + tempq + " quarter(s) back." );
				if (h == 0)
					System.out.println("You receive the \"Hydrated\" buff!");
				return 1; 
			}
			else {
				System.out.println("Not enough coins! Restock coins now! You receive "
								   + nick + " nickel(s), " + d + " dime(s), and " + q + " quarter(s) back.");
				if (h == 0)
					System.out.println("You remain dehydrated.");
				return -1; 
			}
		}
	}
}
	
/**
 * Main class of this project. 
 * Outputs to console and takes user input.
 */
public class Hw2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product n; 
		Scanner reader = new Scanner(System.in);
		String answer; 
		boolean check; 
		int choice; 
		String cost; 
		int flag1;
		int flag2;
		int hydrated = 0; 
		System.out.print("Welcome to the Vending Machine Text Adventure! "
				+ "Press Enter to begin!");
		reader.nextLine(); 
		do {
			flag1 = 1; 
			System.out.print("While stumbling through a barren wasteland, you "
					+ "start to become dehydrated. (Press Enter to continue)"); 
			reader.nextLine();
		
			System.out.print("In the distance, you see what seems to resemble..."
					+ "a person? Here? In the desert?");
			reader.nextLine();
		
			System.out.print("You approach the enigmatic person and they offer you a choice.");
			reader.nextLine();
		
			System.out.println("\"I will give you all of the tools necessary to create"
					+ " a vending machine. Do you accept my offer?\"");
			System.out.println("(Type Yes or No and then press Enter)");
			
			do {
				flag2 = 1; 
				answer = reader.next();
		
				if (answer.equals("No")) {
					flag1 = 0; 
					System.out.println("The person frowns and disappears into thin air."
							+ " You die in the desert due to dehydration.");
					System.out.print("Press Enter to restart.");
					reader.nextLine();
					reader.nextLine();
				}
				else if (answer.equals("Yes")) { 
					System.out.print("The person grins. \"Great.\", they say.");
					continue; 
				}
				else {
					flag2 = 0; 
					System.out.println("The person looks confused.");
					System.out.println("Try either Yes or No.");
				}
			}while(flag2 == 0);
		}while(flag1 == 0);
		
		VendingMachine machine = null;
		
		do {
			reader.nextLine();
			reader.nextLine();
			System.out.println("The person shows you a list of options: " );
			System.out.println("1) Build Vending Machine.");
			System.out.println("2) Restock Products.");
			System.out.println("3) Restock Coins.");
			System.out.println("4) Buy a drink."); 
			System.out.println("5) Add a Product.");
			System.out.println("6) Quit");
			System.out.println("(Please enter the # that corresponds with your choice.)");
			choice = reader.nextInt(); 
			switch (choice) {
				case 1: if (machine == null) {
							machine = new VendingMachine();
							System.out.println("Congratulations! You've made a vending machine!");
							System.out.println("The machine has 0 coins. You may want to restock before using the machine.");
						}
						else {
							System.out.println("You've already made one.");	
						}
						break; 
						
				case 2: if (machine != null) {
							if(machine.getHead() == null) {
								System.out.println("There are no products in the machine.");
								break;
							}
							System.out.println("Which product shall I restock? (Choose a product)");
							handleProduct.listProducts(machine);
							answer = reader.next();
							if (Checker.isExist(answer, machine) == false)
							{
								System.out.println("That product does not exist.");
								break;
							}
							n = handleProduct.findProduct(answer, machine);
							System.out.println("How much total do you want restocked? (Enter an Integer)"); 
							choice = reader.nextInt();
							if (choice < 0) {
								System.out.println("Not a valid quantity.");
								break; 
							}
							Operator.restockQuantity(n, choice);
							System.out.println("The product has been stocked to " + choice + " unit(s)!");
						}
						else
							System.out.println("There is no machine to restock");
						break; 
						
				case 3: if (machine != null) {
							Operator.restockCoins(machine);
							System.out.println("All coins have been restocked to 10 coins each.");
						}
						else
							System.out.println("There is no machine to restock");	
						break; 
						
				case 4: if (machine == null) {
							System.out.println("There is no vending machine to buy out of.");
							break; 
						}
						if (machine.getHead() == null) {
							System.out.println("The vending machine contains no products.");
							break; 
						}
						System.out.println("Which product do you want to buy?");
						handleProduct.listProducts(machine);
						answer = reader.next();
						if (Checker.noCoins(machine) == true) {
							System.out.println("Coins are dangerously low. OUT OF SERVICE! (Please restock coins.)");
							if (hydrated == 0)
								System.out.println("You remain dehydrated.");
							break; 
						}
						int nick;
						int d; 
						int q; 
						float costf; 
						float sum; 
						
						if (Checker.isExist(answer, machine) == true)	{
							n = handleProduct.findProduct(answer, machine);
							if (Checker.isEmpty(n) == true) {
								System.out.println("Out of stock!! Call the operator!!");
								if (hydrated == 0)
									System.out.println("You remain dehydrated.");
								break; 
							}
						}
						else {
							System.out.println("That product does not exist.");
							if (hydrated == 0)
								System.out.println("You remain dehydrated.");
							break; 
						}
							
						cost = n.getCost();
						costf = Float.parseFloat(cost);
						System.out.println("That product costs $" + cost + ".");
						System.out.println("How many nickels?");
						nick = reader.nextInt();
						System.out.println("How many dimes?");
						d = reader.nextInt();
						System.out.println("How many quarters?");
						q = reader.nextInt();
								
						sum = (float) ((nick * 0.05) + (d * 0.10) + (q * 0.25));
								
						if (sum < costf) {
							System.out.println("Insufficient amount of money given. You receive " 
									+ nick + " nickel(s), " + d + " dime(s), and " + q + " quarter(s) back.");
							if (hydrated == 0)
								System.out.println("You remain dehydrated.");
							break; 
						}
						else 
							flag1 = Operator.giveChangeandProduct(machine, sum, costf, nick, d, q, n, hydrated);
						if (flag1 == 1)
							hydrated = 1; 
						break; 
				
				case 5: if (machine != null) {
							System.out.println("Give the product a name.");
							answer = reader.next();
							check = Checker.isExist(answer, machine); 
							if (check == true) {
								System.out.println("That product already exists.");
								break; 
							}		
							System.out.println("Give the product a cost. (Multiple of 0.05)");
							cost = reader.next();
							Float fObj = new Float(Float.parseFloat(cost));
							check = Checker.validCost(fObj);
							if (check == false) {
								System.out.println("You entered an invalid cost. Start over.");
								break; 
							}
						
							Product product = new Product(answer, cost);
							handleProduct.addProduct(machine, product);
							System.out.println("You've added a product!");
							
						}
						else {
							System.out.println("There's no machine to put products into.");
						}			
			}
		}while (choice != 6);
		
		if (hydrated == 1) {
			System.out.print("\"Goodbye.\" the person says.");
			reader.nextLine();
			reader.nextLine();
			
			System.out.println("You thank the person. Fully hydrated, you are able to make your way");
			System.out.println("to a nearby city.");
			System.out.println("YOU WIN!!!!");
			reader.close();
		}
		else {
			System.out.print("\"Suit Yourself.\" the person says.");
			reader.nextLine();
			reader.nextLine();
			
			System.out.println("Without a sip of anything, you collapse to your death.");
			System.out.println("GAME OVER!!!!");
			reader.close();
		}
	}
}