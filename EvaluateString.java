import java.util.Stack;
import javax.swing.JOptionPane;
public class EvaluateString
{
	private  Compile instance;
	private long result;
	private String concaStringResult;
    public void evaluate(String expression,Compile instance)
    {
		this.instance=instance;
        char[] tokens = expression.toCharArray();
		//System.out.println("EXXXXXXXX: "+expression);
		
         // Stack for numbers: 'values'
        Stack<Long> values = new Stack<Long>();
 
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
			//System.out.println("tokens[i]: "+tokens[i]);
            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Long.parseLong(sbuf.toString()));
				i--;
            }
 
            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
			else if ((tokens[i] >= 'a' && tokens[i] <= 'z') || (tokens[i] >= 'A' && tokens[i] <= 'Z'))
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && (tokens[i] >= 'a' && tokens[i] <= 'z' || tokens[i] >= 'A' && tokens[i] <= 'Z'))
                    sbuf.append(tokens[i++]);
                i--;
				for(int h=0;h<instance.getVariables().size();h++){
					if(sbuf.toString().equals(instance.getVariables().get(h).getName())){
						values.push(Long.parseLong(instance.getVariables().get(h).getValue()));
					}
				}
			}
        }
 
        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
        // Top of 'values' contains result, return it
        result=values.pop();
    }
	
	public Long getResult(){
		return result;
	}
	

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    // A utility method to apply an operator 'op' on operands 'a' 
    // and 'b'. Return the result.
    public static long applyOp(char op, long b, long a)
    {
        switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0){
				JOptionPane.showMessageDialog(null,"Cannot divide zero.... try again");
			}
                
            return a / b;
        }
        return 0;
    }
	
	
	
	
	//........................... if"pota"+"sasa"---> expression duha or more na string concatenate
	
	public String getConcaStringResult(){
		return concaStringResult;
	}
 
	
	public boolean concatenateString(String expression){
		char [] tokens= expression.toCharArray();
		StringBuffer sbuf=new StringBuffer();
		for(int i=0,count=0;i<tokens.length;i++){
			if(tokens[i]=='"' && count==0){
				while(i<tokens.length && count==0){
					i++;
					if(tokens[i]=='"')
						count=1;
					else
						sbuf.append(tokens[i]);
				}
			}
			else if(tokens[i]=='+' && tokens[i+1]=='"'&&count==1)
				count=0;
			else 
				return false;
				
		}
		concaStringResult=sbuf.toString();
		return true;
	}
}