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

class DeclarationStatement extends Statement{
    String id;
    String type;
    public DeclarationStatement(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Declaration " + this.id + " " + this.type;
    }
    
}
class ForStatement extends Statement{
    AssignmentStatement assignment;
    int integer;
    Statement[] statements;
    public ForStatement(int line, int column, AssignmentStatement assignment, int integer, Statement[] statements) {
        super(line, column);
        this.assignment =  assignment;
        this.integer = integer;
        this.statements = statements;
        this.description = "For " + integer;
    }
}
class WhileStatement extends Statement{
    Expression expression;
    Statement[] statements;
    public WhileStatement(int line, int column, Expression expression, Statement[] statements) {
        super(line, column);
        this.expression = expression;
        this.statements = statements;
        this.description = "While";
    }
}
class AssignmentStatement extends Statement{
    String id;
    Expression expression;
    public AssignmentStatement(int line, int column, String id, Expression expression) {
        super(line, column);
        this.id = id;
        this.expression = expression;
        this.description = "Assignment " + this.id;
    }
}
class IOStatement extends Statement{
    String id;
    Expression expression;
    public IOStatement(int line, int column, String id, Expression expression) {
        super(line, column);
        this.id = id;
        this.expression = expression;
        this.description = "IOStatement " + this.id;
    }
}
class FunctionCallStatement extends Statement{
    String id;
    Expression[] expressions;
    public FunctionCallStatement(int line, int column, String id, Expression[] expressions) {
        super(line, column);
        this.id = id;
        this.expressions = expressions;
        this.description = "FunctionCall " + this.id;
    }
}
class ConditionalStatement extends Statement{
    Expression expression;
    Statement[] statementsIfTrue;
    Statement[] statementsIfFalse;
    public ConditionalStatement(int line, int column, Expression expression, Statement[] statementsIfTrue, Statement[] statementsIfFalse) {
        super(line, column);
        this.expression = expression;
        this.statementsIfTrue = statementsIfTrue;
        this.statementsIfFalse = statementsIfFalse;
        this.description = "If";
    }
}

class ModuleList<T> extends Tree {
    T module;
    ModuleList tail;
    public ModuleList(int line, int column) {
        super(line, column);
        this.description = "ModuleList";
    }
}

class ProcedureHelper {
    String id;
    ArgumentHelper[] arguments;
    Statement[] statementList;
    public ProcedureHelper(String id, ArgumentHelper arguments, Statement[] statementList) {
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
    public FunctionHelper(String id, ArgumentHelper arguments, Statement[] statementList, String type) {
        this.id = id;
        this.arguments = arguments;
        this.statementList = statementList;
        this.type = type;
    }
}

class ArgumentHelper {
    String id;
    String type;
    public ArgumentHelper(String id, String type) {
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
    public BinaryExpression(int line, int column, Expression left, String operator, Expression right) {
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
    public UnaryExpression(int line, int column, String operator, Expression right) {
        super(line, column);
        this.right = right;
        this.operator = operator;
        this.description = "Unary Expression  " + operator;
    }
}

class LiteralExpression<T> extends Expression {
    T value;
    public LiteralExpression(int line, int column, T value) {
        super(line,column);
        this.value = value;
        this.description = value.toString();
    }
}

class IdExpression extends Expression {
    String id;
    public IdExpression(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "ID " + this.id;
    }
}

class FunctionCallExpression extends Expression {
    String id;
    ArgumentHelper[] arguments;
    public FunctionCallExpression(int line, int column, String id, ArgumentHelper[] arguments) {
        super(line, column);
        this.id = id;
        this.arguments = arguments;
        this.description = "FunctionCall " + this.id;
    }
}


