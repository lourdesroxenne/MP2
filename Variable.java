public class Variable {

    private String varname;
    private String vartype;
    private String value;

    public Variable(String a_varname, String a_vartype) {
            varname = a_varname;
            vartype = a_vartype;
            value = null;
    }

    public void setName(String a_varname) {
    	varname = a_varname;
    }

    public void setType(String a_vartype) {
    	vartype = a_vartype;
    }

    public void setValue(String a_value) {
    	value = a_value;
    }

    public String getName() {
    	return varname;
    }

    public String getType() {
    	return vartype;
    }

    public String getValue() {
    	return value;
    }
}