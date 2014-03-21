public class StatementGrammar extends Grammar {
	
	public static void main(String[] args) {
		StatementGrammar a = new StatementGrammar();
		String error = a.isProgramStatement("print x;//yes");
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

		String[] tokens = statement.split("=");
		
		if(tokens.length == 2) {
			//return true;
		} else {
			return false;
		}

		return true;
	}


/*
<program statement> ::= 	<read statement> | <print statement> | <assignment statement> ;
<read statement> ::= read <identifier>
<print statement> ::= print <identifier>
<assignment statement> ::= <identifier> = <expression>
<expression>::= <number-expression> | <word> | <identifier>
*/

}