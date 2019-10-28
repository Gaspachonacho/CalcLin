package lab3;

import lincalc.LinCalcJohn;

import java.util.Arrays;
import java.util.Scanner;



public class LinCalc {

    public static void printArray(String[] array){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        // Replace the last ", " with "]"
        sb.replace(sb.length() - 2, sb.length(), "]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression;

        double result;

        System.out.print("Enter expression: ");
        expression = in.nextLine();
        do {
            result = evaluate(expression);
            System.out.println("Result was: " + result);
            System.out.print("Enter expression: ");
        } while (!"".equals(expression = in.nextLine()));
    }
	
    public static double calc(String[] lexedPostfix) {
        return LinCalcJohn.calc(lexedPostfix);
    }
	
    public static String[] toPostfix(String[] inputData) {
    	
    	
    	
    	Stack stack = new Stack();
    	
    	
    	String operatorer = "()~+-*/";
    	String[] postfixUttryck = new String[inputData.length];
    	int place = 0; 
    	
    	for(int i = 0; i<inputData.length; i++) {
    		char current = inputData[i].charAt(0);
    		
    		if(memberOf(current,operatorer)){
    			if(stack.isEmpty()) {
    				stack.push(inputData[i]);
    			}
    			else if(prio(inputData[i]) > prio(stack.peek())) {
    				stack.push(inputData[i]);
    			}
    			else if(prio(inputData[i]) == prio(stack.peek())) {
    				stack.push(inputData[i]);
    			}
    			else if(prio(inputData[i]) < prio(stack.peek())) {
    				while(!stack.isEmpty() || prio(inputData[i]) < prio(stack.peek())) {
    					postfixUttryck[place] = stack.pop();
    					place++;
    				}
    				stack.push(inputData[i]);
    			}
    		}
    		else {
    			postfixUttryck[place] = inputData[i];
    			place++;
    		}
    	}		
    	while(!stack.isEmpty()) {
    		postfixUttryck[place] = stack.pop();
    		place++;
    		
    	}
    	printArray(postfixUttryck);
    	String[] facit = LinCalcJohn.toPostfix(inputData);
    	printArray(facit);
        return LinCalcJohn.toPostfix(inputData); 
    
		//return postfixUttryck;
    }
    
    
    public static double evaluate(String expression) {
        String[] lexedInfix = lex(expression);
        String[] lexedPostfix = toPostfix(lexedInfix);
        return calc(lexedPostfix);
    }
	
    
    //////////////////////////////////////////////////////////////////////// LEX DOWN BELOW
    
    public static String[] lex(String expr) {
    	
    	expr = expr.replaceAll("\\s", ""); //Omvandlar alla mellanslag till ingenting
    	
    	String operander = "0123456789";
    	String operatorer = "()~+-*/";
    	String[] uttryck = new String[expr.length()];
    	int place = 0; 
    	String hold = ""; //
    	char lastCurrent = '€';
    	String[] facit = LinCalcJohn.lex(expr);
    	//printArray(facit);
    	
    	
    for(int i=0; i<expr.length(); i++) {	
    	char current = expr.charAt(i);				
    		
    		if (memberOf(current, operatorer)) { // Kollar current operatororerer	
    			if (hold == "") {	
    				if (place == 0 && current == '-') { // Om expression-et börjar med en minus operand, skall den ersättas med Tilde
    					current = '~';		
    				}else if(memberOf(lastCurrent, operatorer) && current == '-') { //om det finns en operator som t.ex * innan minus tecknet => tilde
    					current = '~';
    				}
    				
    				uttryck [place] = hold + current;
    				place++;
    			}  			
    			else {
        			place++;
                	hold = "";
                	uttryck [place] = hold + current;
                	place++;
    			}
    		}
    		else{ // Kollar current operand
    		hold = hold + current;
    		uttryck [place] = hold;
    		} 	
    		
    	lastCurrent = current;
    }
    	
    
    //HK
    
        int countNulls = 0;

        for (int i = 0; i < uttryck.length; i++) { // Räkna alla Nulls
            if (uttryck[i] == null) {
                countNulls++;
            }
        }
        
        // Ny array utan Nulls
        String[] utanNull = new String[uttryck.length - countNulls];
        for (int i = 0, j = 0; i < uttryck.length; i++) {
            if (uttryck[i] != null) {
                utanNull[j] = uttryck[i];
                j++;
            }
        }
    	
    	
    	
        //printArray(utanNull);
    	//System.out.println(Arrays.toString(uttryck));
    	return utanNull;

    }

    public static boolean memberOf(char c,String z) {
    	if(c == '€') {
    		return false;
    	}
		for(int i = 0; i < z.length(); i++) {
		char current = z.charAt(i); 
			if (current == c) {
				return true;
			}		
		}	
		return false;
	}
    
    public static int prio(String c) {
    	switch (c) {
    	case "+":
    	case "-":
    		return 1;
    	case "*":
    	case "/":
    		return 2;
    	}
    	return 0;
    }
    
}
