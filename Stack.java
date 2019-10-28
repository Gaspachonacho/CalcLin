package lab3;

public class Stack { // Ska ha metoder/funktioner för att kunna push-a o popp-a o peek-a o isEmpty 

	String[] stack;
	
	//konstruktör
	public Stack() {
		stack = new String[0];
	}
	
	//push. Den här lägger till en "längd" i stacken ifall vi väljer att pusha ett element i den. 
	public void push(String element){
		String[] hold = new String[stack.length+1];
		for(int i=0; i<stack.length; i++) {
			hold[i] = stack[i];
		}
		hold[stack.length] = element;
		stack = hold;
	}
	//pop
	public String pop() {
		if(stack.length == 0) {
			return null; 
		}
		String returnHold = stack[stack.length-1];
		
		String[] popArray = new String[stack.length-1];
		for(int i=0; i<stack.length -1; i++) {
			popArray[i] = stack[i];
		}
		stack = popArray;
		return returnHold;
	}
	
	//peek
	public String peek() {
		if (stack.length == 0) {
			return null;
		}
		else {
			String peek = stack[stack.length];
			return peek;
		}
		
	}
	//isEmpty
	public boolean isEmpty() {
		if(stack.length == 0) {
			return true;
		}
		return false;
	}
	
	
	

	
	
}
