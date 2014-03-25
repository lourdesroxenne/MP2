import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

import javax.swing.JOptionPane;
//import java.io.*;

public class Executer {

    Compile compiler;
    private List<Variable> variables;
    private List<String> statements;
    private StatementGrammar stategrammar;

    private EvaluateString eval;

    public Executer() {

        compiler = new Compile();
        compiler.readCode();
        compiler.compile();

        variables = compiler.getVariables();
        statements = compiler.getStatements();

        stategrammar = new StatementGrammar();
        eval = new EvaluateString();

    }

    public void execute() {
        for(int i=0; i<statements.size(); i++) {
            String statement = statements.get(i);

            if(stategrammar.isReadStatement(statement)) {
                read(statement);
            } else if(stategrammar.isPrintStatement(statement)) {
                print(statement);
            } else if(stategrammar.isAssignmentStatement(statement)) {
                assignment(statement);
            }
        }
    }

    public void read(String statement) {
        //since given naman nga sakto ang syntax 
        //maka-kuha ra dayon kung unsa nga variable ang i-read
        
        String[] tokens = statement.split(" ");
        String tempVar = tokens[1];
        String tempValue;

        tempValue = JOptionPane.showInputDialog( "Please input the value of " + tempVar);

        while(!isValidInput(tempValue,tempVar)) { //while invalid pa ang input

            stateValidInputError();
            tempValue = JOptionPane.showInputDialog( "Please input the value of " + tempVar);

        }

        //pag-set sa value sa variable
        variables.get(searchIndex(tempVar)).setValue(tempValue);  
        //System.out.println("Read "+tempVar+" -----------> "+tempValue);
        
        //this is not need na kay sa pag-compile pa lang daan 
        //kay ma-catch naman if not a variable
        //} else 
        //    stateVariableError(tempVar);
    }

    public void stateValidInputError(){
        JOptionPane.showMessageDialog(null,"Invalid Input... Try another input..");
        //error=true;
    }

    public boolean isNum(String str){
        return str.matches("\\d+");
    }

    public boolean isValidInput(String val,String var) { 
        //gi-change lang nako ang method name from "isValidInputError" to "isValidInput"
        //kay taas na kaayo. Same function pero nabali lang ang mga return value
        if(variables.get(searchIndex(var)).getType().equals("number")){
            if(isNum(val))
                return true;
        }
        else if(variables.get(searchIndex(var)).getType().equals("word"))
            return true;
        return false;
    }

    public int searchIndex(String str){
        //from 0 kay gichange to -1 kay naa man juy sulod ang 0 nga index
        int num=-1;
        for(int i=0;i<variables.size();i++) {
            if(str.equals(variables.get(i).getName())) {
                num=i;
            }
        }
        return num;
    }

    public void stateVariableError(String str){
        JOptionPane.showMessageDialog(null, str+"  is Not a Variable... Try Again");
        //error=true;
    }

/*****************************************************************************************/

    public void print(String statement) {
        //since given naman nga sakto ang syntax 
        //maka-kuha ra dayon kung unsa nga variable ang i-read
        
        String[] tokens = statement.split(" ");
        String tempVar = tokens[1];
        String tempValue;

        if(!isInitializedError(tempVar)){
            /*
            //gikuha nalang ang index diritso sa variable since naa naman kay searchindex nga method

            for(int i=0;i<variables.size();i++) {
                if(tempVar.equals(variables.get(i).getName())) {
                    System.out.println("Print "+""+variables.get(i).getName()+"-------->"+variables.get(i).getValue());
                }  
            }
            */ 

            tempValue = variables.get(searchIndex(tempVar)).getValue();  
            System.out.println(tempValue);
        } else {
            stateInitializedError(tempVar);
        }
              
        
    }

    public boolean isInitializedError(String var){
        if(variables.get(searchIndex(var)).getValue()!= null)
            return false;
        return true;
    }
    
    public void stateInitializedError(String var){
        JOptionPane.showMessageDialog(null,var+"  is not initialized.. try again..");
    }


    public void assignment(String statement) {
        String[] tokens = statement.split("=");
        String identifier = tokens[0];
        String expression = tokens[1];

        if(stategrammar.isIdentifier(expression)) { //isExpressionStoredVar
            int index = searchIndex(identifier); //index of identifier
            int index2 = searchIndex(expression); //index of expression
            String value = variables.get(index2).getValue();
            variables.get(index).setValue(value);
        } else if(isExpressionConcatenateString(expression)){
            variables.get(searchIndex(identifier)).setValue(eval.getConcaStringResult());
        } else if(stategrammar.isWord(expression)) { //isExpressionConcatenateString(expression)
            int index = searchIndex(identifier); //index of identifier
            variables.get(index).setValue(expression);
        } else if (stategrammar.isNumExpression(expression)) { 
            /*gitang-tang to nako ang isExpressionSingleNum kay ma-solve ra man if numExpression*/
            eval.evaluate(expression,compiler);
            String result = Long.toString(eval.getResult());
            variables.get(searchIndex(identifier)).setValue(result);
        }
    

      
    }

    public boolean isExpressionConcatenateString(String expression){
        try{
            if(eval.concatenateString(expression))
                return true;
            else
                return false;
        }catch(Exception ex){
            return false;
        }
    }

    public List<Variable> getVariables() {
        return variables;
    }









        public static void main(String[] args) {
                Executer a = new Executer();
                a.execute();

                for(int i=0; i<a.variables.size(); i++) {
                    System.out.println(a.variables.get(i).getName() + " " + a.variables.get(i).getValue());
                }
                //System.out.print("Enter something:");
                //String input = System.console().readLine();
        }

}