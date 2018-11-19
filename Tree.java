import java.util.ArrayList;

public abstract class Tree {
    protected String description;
    public int line, column;

    protected Tree(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public abstract boolean semanticTest(VariableTable variableTable);

    public void semanticError(String message) {
        System.out.println(
                "Semantic Error \"" + message + "\" in line=: " + (line + 1) + " and column: " + (column + 1) + " ");
    }

    @Override
    public String toString() {
        return description;
    }

    public Tree[] getChildren() {
        return null;
    }
}

class ListClass<T> extends Tree {
    ArrayList<T> elements;

    public ListClass() {
        super(0, 0);
        this.elements = new ArrayList<T>();
        this.description = "List";
    }

    public ListClass(int line, int column, T element) {
        super(line, column);
        this.elements = new ArrayList<T>();
        this.elements.add(element);
        this.description = element instanceof StatementClass ? "StatementList" : "ExpressionList";
    }

    public void Add(T element) {
        if (element != null) {
            this.elements.add(0, element);
            this.line = ((Tree) element).line;
            this.column = ((Tree) element).column;
        }
    }

    public ArrayList<T> getList() {
        if (this.elements == null) {
            return new ArrayList<T>();
        }
        return this.elements;
    }

    public Tree[] getChildren() {
        Object[] a = elements.toArray();
        Tree[] c = new Tree[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = (Tree) a[i];
        }
        return c;
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }
}

abstract class StatementClass extends Tree {
    protected StatementClass(int line, int column) {
        super(line, column);
    }
}

class DeclarationStatement extends StatementClass {
    String id;
    String type;

    public DeclarationStatement(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Declaration " + this.id + " " + this.type;
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (variableTable.variableExists(id)) {
            semanticError("This variable id: " + id + " already exists");
            return false;
        }
        variableTable.addToTable(id, type);
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return null;
    }
}

class ForStatement extends StatementClass {
    AssignmentStatement assignment;
    int integer;
    ListClass<StatementClass> statements;

    public ForStatement(int line, int column, AssignmentStatement assignment, int integer,
            ListClass<StatementClass> statements) {
        super(line, column);
        this.assignment = assignment;
        this.integer = integer;
        this.statements = statements;
        this.description = "For " + integer;
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { assignment, statements };
    }
}

class WhileStatement extends StatementClass {
    Expression expression;
    ListClass<StatementClass> statements;

    public WhileStatement(int line, int column, Expression expression, ListClass<StatementClass> statements) {
        super(line, column);
        this.expression = expression;
        this.statements = statements;
        this.description = "While";
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!this.expression.getType(variableTable).equals("boolean")) {
            semanticError("Expression in while statement is not of type boolean");
            return false;
        }
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression, statements };
    }
}

class AssignmentStatement extends StatementClass {
    String id;
    Expression expression;

    public AssignmentStatement(int line, int column, String id, Expression expression) {
        super(line, column);
        this.id = id;
        this.expression = expression;
        this.description = "Assignment " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            semanticError("Variable of id: " + id + " was not declared");
            return false;
        }
        String typeLeft = variableTable.getIdType(id);
        String typeRight = this.expression.getType(variableTable);
        if (!typeLeft.equals(typeRight)) {
            semanticError("Cannot assign expression of type: " + typeRight + " to expression of type " + typeLeft);
            return false;
        }
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression };
    }
}

class ReadStatement extends StatementClass {
    String id;

    public ReadStatement(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "Read " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            semanticError("Variable of id: " + id + " does not exist.");
            return false;
        }
        if (!variableTable.getIdType(id).equals("string")) {
            semanticError("Variable of id: " + id + " is not a String.");
            return false;
        }
        return true;
    }
}

class WriteStatement extends StatementClass {
    Expression expression;

    public WriteStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
        this.description = "Write";
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression };
    }
}

class FunctionCallStatement extends StatementClass {
    String id;
    ListClass<Expression> expressions;

    public FunctionCallStatement(int line, int column, String id, ListClass<Expression> expressions) {
        super(line, column);
        this.id = id;
        this.expressions = expressions;
        this.description = "FunctionCall " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Expression> temporaryExpressions = this.expressions.getList();
        for (int i = 0; i < temporaryExpressions.size(); i++) {
            types.add(temporaryExpressions.get(i).getType(variableTable));
        }
        if (!variableTable.functionExists(this.id, types)) {
            semanticError("The function with id: " + this.id + " and these arguments: " + types.toString()
                    + " does not exist.");
            return false;
        }
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expressions };
    }
}

class ConditionalStatement extends StatementClass {
    Expression expression;
    ListClass<StatementClass> statementsIfTrue;
    ListClass<StatementClass> statementsIfFalse;

    public ConditionalStatement(int line, int column, Expression expression, ListClass<StatementClass> statementsIfTrue,
            ListClass<StatementClass> statementsIfFalse) {
        super(line, column);
        this.expression = expression;
        this.statementsIfTrue = statementsIfTrue;
        this.statementsIfFalse = statementsIfFalse;
        this.description = "If";
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!this.expression.getType(variableTable).equals("boolean")) {
            semanticError("The expression in the if statement is not a boolean");
            return false;
        }
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression, statementsIfTrue, statementsIfFalse };
    }
}

class ModuleListClass<T> extends Tree {
    T module;
    ModuleListClass tail;

    public ModuleListClass(int line, int column, ModuleListClass tail, T module) {
        super(line, column);
        this.module = module;
        this.tail = tail;
        this.description = "ModuleListClass";
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    @Override
    public Tree[] getChildren() {
        if (tail == null) {
            Tree[] children = { ((Tree) module) };
            return children;
        } else {
            Tree[] t = tail.getChildren();
            Tree[] children = new Tree[1 + t.length];
            children[0] = ((Tree) module);
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }
}

class ProcedureHelper extends Tree {
    String id;
    ListClass<ArgumentHelper> arguments;
    ListClass<StatementClass> statements;

    public ProcedureHelper(int line, int column, String id, ListClass<ArgumentHelper> arguments,
            ListClass<StatementClass> statements) {
        super(line, column);
        this.id = id;
        if (arguments == null) {
            this.arguments = new ListClass<ArgumentHelper>();
        } else {
            this.arguments = arguments;
        }
        this.statements = statements;
        this.description = "Procedure " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<ArgumentHelper> temporaryArguments = this.arguments.getList();
        for (int i = 0; i < temporaryArguments.size(); i++) {
            types.add(temporaryArguments.get(i).type);
            names.add(temporaryArguments.get(i).id);
        }
        if (variableTable.functionExists(this.id, types)) {
            semanticError("The procedure with id: " + this.id + " and these arguments: " + types.toString()
                    + " already exists.");
            return false;
        }
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j))) {
                    semanticError(
                            "There was a duplicate argument: " + names.get(i) + " in this: " + this.id + " procedure");
                    return false;
                }
            }
        }
        variableTable.addToTable(this.id, "", temporaryArguments);
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { arguments, statements };
    }
}

class FunctionHelper extends Tree {
    String id;
    ListClass<ArgumentHelper> arguments;
    ListClass<StatementClass> statements;
    String type;

    public FunctionHelper(int line, int column, String id, ListClass<ArgumentHelper> arguments,
            ListClass<StatementClass> statements, String type) {
        super(line, column);
        this.id = id;
        this.arguments = arguments;
        this.statements = statements;
        this.type = type;
        this.description = "Function " + this.id + " " + this.type;
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<ArgumentHelper> temporaryArguments = this.arguments.getList();
        for (int i = 0; i < temporaryArguments.size(); i++) {
            types.add(temporaryArguments.get(i).type);
            names.add(temporaryArguments.get(i).id);
        }
        if (variableTable.functionExists(this.id, types)) {
            semanticError("The function with id: " + this.id + " and these arguments: " + types.toString()
                    + " already exists.");
            return false;
        }
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j))) {
                    semanticError(
                            "There was a duplicate argument: " + names.get(i) + " in this: " + this.id + " function");
                    return false;
                }
            }
        }
        variableTable.addToTable(this.id, this.type, temporaryArguments);
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { arguments, statements };
    }
}

class ArgumentHelper extends Tree {
    String id;
    String type;
    boolean byValue;

    public ArgumentHelper(int line, int column, String id, String type, boolean byValue) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Argument " + this.id + " " + this.type;
        this.byValue = byValue;
    }

    public ArgumentHelper(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Argument " + this.id + " " + this.type;
        this.byValue = true;
    }

    public boolean equals(ArgumentHelper argumentHelper) {
        return this.type.equals(argumentHelper.type);
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

}

abstract class Expression extends Tree {
    protected Expression(int line, int column) {
        super(line, column);
    }

    public abstract String getType(VariableTable variableTable);
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

    public String getType(VariableTable variableTable) {
        String type = right.getType(variableTable);
        return type == left.getType(variableTable) ? type : "error";
    }

    public boolean semanticTest(VariableTable variableTable) {
        String leftType = this.left.getType(variableTable);
        String rightType = this.right.getType(variableTable);
        if (!leftType.equals(rightType)) {
            semanticError("Type: " + leftType + " is not comapatible with: " + rightType);
            return false;
        }
        // TODO check if types are compatible with operator
        return true;
    }

    public String resultingType(String a, String b, String operator) {
        // TODO finish resulting types
        return "";
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { left, right };
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

    public String getType(VariableTable variableTable) {
        return right.getType(variableTable);
    }

    public boolean semanticTest(VariableTable variableTable) {
        String rightType = this.right.getType(variableTable);
        if (this.operator.equals("-")) {
            if (!(rightType.equals("integer") || rightType.equals("double"))) {
                semanticError("Operator - cannot be used with non numerical type: " + rightType);
                return false;
            }
        } else {
            if (!(rightType.equals("boolean"))) {
                semanticError("Operator NOT cannot be used with non boolean type: " + rightType);
                return false;
            }
        }
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { right };
    }
}

class LiteralExpression<T> extends Expression {
    T value;

    public LiteralExpression(int line, int column, T value) {
        super(line, column);
        this.value = value;
        this.description = value.toString();
    }

    public String getType(VariableTable variableTable) {
        if (Integer.class.isInstance(value)) {
            return "integer";
        } else if (Character.class.isInstance(value)) {
            return "char";
        } else if (String.class.isInstance(value)) {
            return "string";
        } else if (Boolean.class.isInstance(value)) {
            return "boolean";
        }
        return "error";
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }
}

class IdExpression extends Expression {
    String id;

    public IdExpression(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "ID " + this.id;
    }

    public String getType(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            return "error";
        }
        return variableTable.getIdType(id);
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            semanticError("This variable with id: " + id + " does not exist");
            return false;
        }
        return true;
    }
}

class FunctionCallExpression extends Expression {
    String id;

    ListClass<Expression> expressions;

    public FunctionCallExpression(int line, int column, String id, ListClass<Expression> expressions) {
        super(line, column);
        this.id = id;
        this.expressions = expressions;
        this.description = "FunctionCall " + this.id;
    }

    public String getType(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Expression> temporaryExpressions = this.expressions.getList();
        for (int i = 0; i < temporaryExpressions.size(); i++) {
            types.add(temporaryExpressions.get(i).getType(variableTable));
        }
        return variableTable.getFunctionType(this.id, types);
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Expression> temporaryExpressions = this.expressions.getList();
        for (int i = 0; i < temporaryExpressions.size(); i++) {
            types.add(temporaryExpressions.get(i).getType(variableTable));
        }
        if (!variableTable.functionExists(this.id, types)) {
            semanticError("The function with id: " + this.id + " and these arguments: " + types.toString()
                    + " does not exist.");
            return false;
        }
        return true;
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expressions };
    }
}

class VariableTable {
    ArrayList<IdType> idTable;
    ArrayList<FunctionType> functionTable;
    ArrayList<VariableTable> subTables;

    public VariableTable() {
        this.idTable = new ArrayList();
        this.functionTable = new ArrayList();
        this.subTables = new ArrayList();
    }

    // TODO throw error if already exists ****optional*****
    public void addToTable(IdType variable) {
        this.idTable.add(variable);
    }

    public void addToTable(FunctionType function) {
        this.functionTable.add(function);
    }

    public void addToTable(String id, String type) {
        this.idTable.add(new IdType(id, type.toLowerCase()));
    }

    public void addToTable(String id, String type, ArrayList<ArgumentHelper> arguments) {
        this.functionTable.add(new FunctionType(id, type.toLowerCase(),
                arguments == null ? new ArrayList<ArgumentHelper>() : arguments));
    }

    public boolean variableExists(String id) {
        boolean exists = false;
        for (int i = 0; i < this.idTable.size(); i++) {
            if (this.idTable.get(i).getId() == id) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public boolean functionExists(String id, ArrayList<String> argumentTypes) {
        boolean exists = false;
        for (int i = 0; i < this.functionTable.size(); i++) {
            FunctionType currentFunction = this.functionTable.get(i);
            if (currentFunction.getId().equals(id)) {
                ArrayList<String> currentArguments = currentFunction.getTypes();
                if (currentArguments.size() == argumentTypes.size()) {
                    boolean argumentsAreEqual = true;
                    for (int j = 0; j < currentArguments.size(); j++) {
                        if (!currentArguments.get(j).equals(argumentTypes.get(j))) {
                            argumentsAreEqual = false;
                            break;
                        }
                    }
                    if (argumentsAreEqual) {
                        exists = true;
                        break;
                    }
                }
            }
        }
        return exists;
    }

    public String getIdType(String id) {
        String type = null;
        for (int i = 0; i < this.idTable.size(); i++) {
            if (this.idTable.get(i).getId() == id) {
                type = this.idTable.get(i).getType();
                break;
            }
        }
        return type;
    }

    public String getFunctionType(String id, ArrayList<String> argumentTypes) {
        for (int i = 0; i < this.functionTable.size(); i++) {
            FunctionType currentFunction = this.functionTable.get(i);
            if (currentFunction.getId().equals(id)) {
                ArrayList<String> currentArguments = currentFunction.getTypes();
                if (currentArguments.size() == argumentTypes.size()) {
                    boolean argumentsAreEqual = true;
                    for (int j = 0; j < currentArguments.size(); j++) {
                        if (!currentArguments.get(j).equals(argumentTypes.get(j))) {
                            argumentsAreEqual = false;
                            break;
                        }
                    }
                    if (argumentsAreEqual) {
                        return currentFunction.getType();
                    }
                }
            }
        }
        return "error";
    }
}

class IdType {
    String id, type;

    public IdType(String id, String type) {
        this.id = id;
        this.type = type.toLowerCase();
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }
}

class FunctionType {
    String id, type;
    ArrayList<ArgumentHelper> arguments;

    public FunctionType(String id, String type, ArrayList<ArgumentHelper> arguments) {
        this.id = id;
        this.type = type.toLowerCase();
        this.arguments = arguments;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public ArrayList<ArgumentHelper> getArguments() {
        return this.arguments;
    }

    public ArrayList<String> getTypes() {
        // TODO check by reference or by value
        ArrayList<String> types = new ArrayList<String>();
        for (int i = 0; i < this.arguments.size(); i++) {
            types.add(this.arguments.get(i).type);
        }
        return types;
    }
}
