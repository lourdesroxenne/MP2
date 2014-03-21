import java.util.StringTokenizer;

public class VariableGrammar extends Grammar {


	/* Checks if a given string is an <variable declaration> */ 
	public String isVariableDeclaration(String line) {
		String errormessage = "";
		String declaration = extractStatement(line);

		if(hasSemiColon(declaration)) {
			//remove semicolon
			declaration = declaration.substring(0, declaration.length()-1);
			String tokens[] = declaration.split(" use as ");
			if(tokens.length == 2) {
 				if(!isIdentifierList(tokens[0])) {
 					errormessage = "error: <identifier> expected";
 				}

 				if(!isDataType(tokens[1])) {
 					errormessage = "error: not a statement";
 				}
			} else { // (tokens.length!=2)
				errormessage = "error: not a statement";
			}
		} else { //syntax error
			errormessage = "error: ';' expected";
		}

		return errormessage;
	}

	/* Checks if a given string is an <identifier list> */ 
	public boolean isIdentifierList(String line) {
		StringTokenizer tokenize = new StringTokenizer(line, " ,", true);

		int state = 0;
		while(tokenize.hasMoreTokens()) {
			String token = tokenize.nextToken();

			if(token.equals(" ")) {
				//do nothing
			} else if(state == 0) {
				if(isIdentifier(token)) {
					state = 1;
				} else {
					state = -1;
				}
			} else if(state == 1) {
				if(token.equals(",")) {
					state = 0;
				} else {
					state = -1;
				}
			} else if(state == -1) {
				break;
			}
		}

		if(state == 1) {
			return true;
		} else {
			return false;
		}

	}
}