public class Grammar {

	public static void main(String[] args) {
		//System.out.println(x);
		//int x;
		//int x;
		Grammar a = new Grammar();
	}

	public boolean hasSemiColon(String statement) {
		if(statement.charAt(statement.length()-1) == ';') {
			return true;
		} else {
			return false;
		}
	}

	/*Extracts the statement and ignore the comment in the line of code*/
	public String extractStatement(String line) {
		int temp = line.indexOf("//");
		String statement = "";
		String comment = "";
		if(temp != -1) {
			statement = line.substring(0,temp);
			comment = line.substring(temp+2);
		} else {
			statement = line;
		}
		statement = statement.trim();

		//System.out.println(statement);
		return statement;
	}

	/* Checks if a given string is an <identifier> */ 	
	public boolean isIdentifier(String token) {
		if(!isDataType(token) && !isReservedWord(token)) {
			if(token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/* Checks if a given string is an <data type> */ 	
	public boolean isDataType(String token) {
		if(token.equals("word") || token.equals("number")) {
			return true;
		} else {
			return false;
		}
	}


	/* Checks if a given string is a reserved word in C */ 	
	public boolean isReservedWord(String token) {
		if(
			token.equals("auto") || token.equals("else") ||         
			token.equals("long") || token.equals("switch") || 
			token.equals("break") || token.equals("enum") ||        
			token.equals("register") || token.equals("typedef") || 
			token.equals("case") || token.equals("extern") ||       
			token.equals("return") || token.equals("union") || 
			token.equals("char") || token.equals("float") ||        
			token.equals("short") || token.equals("unsigned") || 
			token.equals("const") || token.equals("for") ||          
			token.equals("signed") || token.equals("void") || 
			token.equals("continue") || token.equals("goto") ||        
			token.equals("sizeof") || token.equals("volatile") || 
			token.equals("default") || token.equals("if") ||           
			token.equals("static") || token.equals("while") || 
			token.equals("do") || token.equals("int") ||          
			token.equals("struct") || token.equals("_Packed") || 
			token.equals("double")
		) {
			return true;
		} else {
			return false;
		}

	}
}