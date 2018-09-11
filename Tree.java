public abstract class Tree {
    protected String description;
    private int line, column;

    protected Tree(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return description;
    }
}

abstract class Statement extends Tree {
    protected Statement(int line, int column) {
        super(line, column);
    }
}

class ModuleList<T> extends Tree {
    T module;
    ModuleList tail;
    protected ModuleList(int line, int column) {
        super(line, column);
        this.description = "ModuleList";
    }
}

class ProcedureHelper {
    String id;
    ArgumentHelper[] arguments;
    Statement[] statementList;
    protected ProcedureHelper(String id, ArgumentHelper arguments, Statement[] statementList) {
        this.id = id;
        this.arguments = arguments;
        this.statementList = statementList;
    }
}

class FunctionHelper {
    String id;
    ArgumentHelper[] arguments;
    Statement[] statementList;
    String type;
    protected FunctionHelper(String id, ArgumentHelper arguments, Statement[] statementList, String type) {
        this.id = id;
        this.arguments = arguments;
        this.statementList = statementList;
        this.type = type;
    }
}

class ArgumentHelper {
    String id;
    String type;
    protected ArgumentHelper(String id, String type) {
        this.id = id;
        this.type = type;
    }
}

abstract class Expression extends Tree {
    protected Expression(int line, int column) {
        super(line, column);
    }
}

class BinaryExpression extends Expression {
    Expression left;
    String operator;
    Expression right;
    protected BinaryExpression(int line, int column, Expression left, String operator, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.description = "Binary Expression  " + operator;
    }
}

class UnaryExpression extends Expression {
    String operator;
    Expression right;
    protected UnaryExpression(int line, int column, String operator, Expression right) {
        super(line, column);
        this.right = right;
        this.operator = operator;
        this.description = "Unary Expression  " + operator;
    }
}
