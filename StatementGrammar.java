

public class StatementGrammar extends Grammar {
	
	public static void main(String[] args) {
		StatementGrammar a = new StatementGrammar();
		boolean error = a.isAssignmentStatement("a = \"sdfA\"");
		System.out.println(error);
	}
	public String isProgramStatement(String line) {
		String errormessage = "";
		String statement = extractStatement(line);

		if(hasSemiColon(statement)) {
			statement = statement.substring(0, statement.length()-1);

			if(
				!isReadStatement(statement) && 
				!isPrintStatement(statement) && 
				!isAssignmentStatement(statement)
			) { //not a program statement
				errormessage = "error: not a statement";
			} 
		} else {
			errormessage = "error: ';' expected";
		}

		return errormessage;
	}

	public boolean isReadStatement(String statement) {
		/* <read statement> ::= read <identifier> */

		String[] tokens = statement.split(" ");
		
		if(tokens.length == 2) {
			if(!tokens[0].equals("read")) {
				return false;
			}

			if(!isIdentifier(tokens[1])) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	public boolean isPrintStatement(String statement) {
		/* <print statement> ::= print <identifier> */
		
		String[] tokens = statement.split(" ");
		
		if(tokens.length == 2) {
			if(!tokens[0].equals("print")) {
				return false;
			}

			if(!isIdentifier(tokens[1])) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	public boolean isAssignmentStatement(String statement) {
		/* <assignment statement> ::= <identifier> = <expression>
		   <expression>::= <number-expression> | <word> | <identifier> */

		statement = statement.replaceAll(" ", "");
		String[] tokens = statement.split("=");
		
		if(tokens.length == 2) {
			String identifier = tokens[0];
			String expression = tokens[1];

			if(!isIdentifier(identifier)) {
				return false;
			}

			if(!isNumExpression(expression) && !isWord(expression) && !isIdentifier(expression)) {
				return false;
			}


		} else {
			return false;
		}

		return true;
	}

	public boolean isNumExpression(String input) {
		//input = input.replaceAll(" ", ""); //to remove unnecessary spaces
		boolean syntax = true;
		int flag = 0; //flag for parenthesis
		String temp = "";


		if( input.charAt(input.length()-1) == '(' ||
		    isOperator(""+input.charAt(input.length()-1)) ) { //string ends with operator || (
			return false;
		}

		for(int i=0; i<input.length(); i++) {
			if (flag < 0) { //syntax error.unbalanced parenthesis
				System.out.println("unbalanced");
				return false;
			}

			if (input.charAt(i) == '(') {
				flag++;
			} else if(input.charAt(i) == ')') {
				flag--;
			}

			if ( i==0 && isOperator(input.charAt(i)+"")) { //syntax error. mag-una ang operator
				return false;
			} else if(i==0) {
			} else if (isOperator(input.charAt(i)+"") && isOperator(input.charAt(i-1)+"")) { //syntax error.sunod ang operator
				return false;
			} else if (isOperator(input.charAt(i)+"") && input.charAt(i) != '-' && input.charAt(i-1) == '(' ) { //syntax error.(+
				return false;
			} else if (isOperator(input.charAt(i-1)+"") && input.charAt(i) == ')' ) { //syntax error.+)
				return false;
			} 
		}

		if(flag != 0) {
			return false;
		}

		return true;
		//System.out.println(syntax);
	}//end of method: isNumExpression

	


/*
<program statement> ::= 	<read statement> | <print statement> | <assignment statement> ;
<read statement> ::= read <identifier>
<print statement> ::= print <identifier>
<assignment statement> ::= <identifier> = <expression>
<expression>::= <number-expression> | <word> | <identifier>
*/

}