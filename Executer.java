import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Executer {

    Compile compiler;
    private List<Variable> variables;
    private List<String> statements;
    private StatementGrammar stategrammar;


        public Executer() {
                compiler = new Compile();
                compiler.readCode();
                compiler.compile();

                variables = compiler.getVariables();
                statements = compiler.getStatements();

                stategrammar = new StatementGrammar();

        }

        public void execute() {

                for(int i=0; i<statements.size(); i++) {
                        String statement = statements.get(i);

                        if(stategrammar.isReadStatement(statement)) {
                                System.out.println("READ");
                        } else if(stategrammar.isPrintStatement(statement)) {
                                System.out.println("PRINT");
                        } else if(stategrammar.isAssignmentStatement(statement)) {

                        }

                }
        }











        public static void main(String[] args) {
                Executer a = new Executer();
                //a.execute();
                System.out.print("Enter something:");
                String input = System.console().readLine();
        }

}