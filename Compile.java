import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;


public class Compile {


	private List<String> linesofcode;
    private List<Variable> variables;
    private List<String> statements;

    private VariableGrammar vargrammar;
    private StatementGrammar stategrammar;


	public Compile() {

		linesofcode = new ArrayList<String>();
        variables = new ArrayList<Variable>();
        statements = new ArrayList<String>();

        vargrammar = new VariableGrammar();
        stategrammar = new StatementGrammar();

	}//end of CONSTRUCTOR

	public void readCode() {

        try {
        	BufferedReader br = new BufferedReader(new FileReader("input.in"));

            String line = br.readLine();
            while(line != null) {
                line = line.trim(); //to remove whitespaces at both ends of the string
                line = stategrammar.extractStatement(line);
                linesofcode.add(line);
                line = br.readLine();
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }

	} //end of method readCode

	public String compile() {

		int vopen = Collections.frequency(linesofcode, "begin vars");
		int vclose = Collections.frequency(linesofcode, "end vars");
		int sopen = Collections.frequency(linesofcode, "begin statements");
		int sclose = Collections.frequency(linesofcode, "end statements");

		String errormessage = "";

		//for "begin vars" statement
		if(vopen != 1) {
			if(vopen < 1) {
				errormessage += "'begin vars' not declared.\n";
			} else {
				errormessage += "'begin vars' declared more than once.\n";
			}

		} else {
			vopen = linesofcode.indexOf("begin vars");
		}

		//for "end vars" statement
		if(vclose != 1) {
			if(vclose < 1) {
				errormessage += "'end vars' not declared.\n";
			} else {
				errormessage += "'end vars' declared more than once.\n";
			}

		} else {
			vclose = linesofcode.indexOf("end vars");
		}

		//for "begin statements" statement
		if(sopen != 1) {
			if(sopen < 1) {
				errormessage += "'begin statements' not declared.\n";
			} else {
				errormessage += "'begin statements' declared more than once.\n";
			}

		} else {
			sopen = linesofcode.indexOf("begin statements");
		}

		//for "end statements" statement
		if(sclose != 1) {
			if(sclose < 1) {
				errormessage += "'end statements' not declared.\n";
			} else {
				errormessage += "'end statements' declared more than once.\n";
			}

		} else {
			sclose = linesofcode.indexOf("end statements");
		}

		// check for garbage code
		// 			before 'begin vars'
		// 			between 'end vars' and 'begin statements'
		// 			after 'end statements'
		for(int i=0; i<vopen; i++) {
			String line = linesofcode.get(i);

			if(!line.equals("")) {
				errormessage += "Unexpected code at line " + (i+1) + ".\n";
			}
		}

		for(int i=vclose+1; i<sopen; i++) {
			String line = linesofcode.get(i);

			if(!line.equals("")) {
				errormessage += "Unexpected code at line " + (i+1) +".\n";
			}
		}



		for(int i=sclose+1; i<linesofcode.size(); i++) {
			String line = linesofcode.get(i);

			if(!line.equals("")) {
				errormessage += "Unexpected code at line " + (i+1) + ".\n";
			}
		}


		if(errormessage.equals("")) {
			//check syntax of variable declaration 
			for(int i=vopen+1; i<vclose; i++) {
				String line = linesofcode.get(i);
				if(!line.equals("")) { //not whitespaces
					String error = vargrammar.isVariableDeclaration(line);
					if(error.equals("")) {
						errormessage += processVarDeclaration(line);
					} else {
						errormessage += error + " at line " + (i+1) + ".\n";
					}
				}
			}

			//check syntax of program statement
			for(int i=sopen+1; i<sclose; i++) {
				String line = linesofcode.get(i);
				if(!line.equals("")) { //not whitespaces
					String error = stategrammar.isProgramStatement(line);
					if(error.equals("")) {
						String errormsg = processStatement(line);
						if(!errormsg.equals("")) {
							errormessage += errormsg + " at line " + (i+1) + ".\n";
						}
					} else {
						errormessage += error + " at line " + (i+1) + ".\n";
					}
				}
			}
		}

		return errormessage;
	} //end of method compile

	public String processStatement(String statement) {

		statement = statement.substring(0, statement.length()-1);
		String errormessage = "";
		
		if(stategrammar.isAssignmentStatement(statement)) {
			StringTokenizer tokenize = new StringTokenizer(statement, "()+-*/= ");
			boolean isCorrect = true;
			while(tokenize.hasMoreTokens()) {
				String token = tokenize.nextToken();

				if(stategrammar.isIdentifier(token)) {
					if(!isDeclared(token)) {
						errormessage = "error: cannot find symbol - variable " + token;
						isCorrect = false;
					}
				}
			}
			if(isCorrect) {
				statements.add(statement);
			}
		} else {
			String tokens[] = statement.split(" ");

			if(!isDeclared(tokens[1])) {
				errormessage = "error: cannot find symbol - variable " + tokens[1];
			} else {
				statements.add(statement);
			}
		}

		return errormessage;

	} //end of method processStatement

	public boolean isDeclared(String var) {
		for(int i=0; i<variables.size(); i++) {
			if(variables.get(i).getName().equals(var)) {
				return true;
			}
		}
		return false;
	}

	public String processVarDeclaration(String declaration) {

		declaration = declaration.substring(0, declaration.length()-1);
		String tokens[] = declaration.split(" use as ");

		String errormessage = "";

		StringTokenizer tokenize = new StringTokenizer(tokens[0], ", ");
        while(tokenize.hasMoreTokens()) {
        	String var = tokenize.nextToken();
        	if(!variables.contains(var)) {
				variables.add(new Variable(var, tokens[1]));
			} else {
				errormessage = "error: variable x is already defined.\n";
			}
        }

        return errormessage;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public List<String> getStatements() {
		return statements;
	}









	public static void main(String[] args) {
		Compile instance = new Compile();
		instance.readCode();
		String error = instance.compile();

		if(error.equals("")) {
			//System.out.println("wazzzup");
		} else {
			System.out.println(error);
		}

		for(int i=0; i<instance.statements.size(); i++) {
			System.out.println(instance.statements.get(i));
		}
		//System.out.println(instance.compile());


	}

}