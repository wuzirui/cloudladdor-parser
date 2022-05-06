package ast;

import lexer.CL;
import org.antlr.v4.runtime.Token;

import java.util.List;

public interface ASTNode {
    public int getType();
    public List<ASTNode> getChildren();
    public void parse(CL lexer);
    public static final int tokenNode = 0, program = -1, programItem = -2, statement = -3, selectionStatement = -4,
        expressionStatement = -5, dataStatement = -6, dataItemList = -7, expression = -8, primaryExpression = -9,
        objLiteral = -10, objItemList = -11, objItem = -12, objPostfix = -13, objListTail = -14, arrayLiteral = -15,
        arrayLiteralTail = -16, expList = -17, expListTail = -18, iterationStatement = -19, forInit = -20, ep = -21,
        assignOperator = -22, leftPostfix = -23, compoundStatement = -24, stats = -25, stats_p = -26,
        expressionTail = -27, returnStatement = -28;
    public static final String[] symbolicNames = {"token", "program", "programItem", "statement", "selectionStatement",
        "expressionStatement", "dataStatement", "dataItemList", "expression", "primaryExpression", "objLiteral",
        "objItemList", "objItem", "objPostfix", "objListTail", "arrayLiteral", "arrayLiteralTail", "expList",
        "expListTail", "iterationStatement", "forInit", "E'", "assignOperator", "leftPostfix", "compoundStatement",
        "stats", "stats'", "expressionTail", "returnStatement"};

    default void printChildren(Token next) {
        System.out.printf("Current AST Node Type: %s, Next Token Type = %s, Text = '%s'\n",
                symbolicNames[-getType()], CL.VOCABULARY.getSymbolicName(next.getType()), next.getText());
        if (getChildren() == null)
            return;
        getChildren().forEach((ASTNode e) -> {
            System.out.println("parse " + e.getClass().getName());
        });
    }

    default void extendChildren(CL lexer) {
        if (getChildren() == null) return;
        getChildren().forEach((ASTNode e) -> {
            e.parse(lexer);
        });
        System.out.printf("Finish extend %s, nextToken = %s(text = %s)\n", this.toString(),
                CL.VOCABULARY.getSymbolicName(lexer.getToken().getType()), lexer.getToken().getText());
    }

    default void dispatchError(Token token) {
        System.out.printf("[ERROR] cannot parse %s(text = '%s') to node type: %s\n",
                CL.VOCABULARY.getSymbolicName(token.getType()), token.getText(), symbolicNames[-getType()]);
        System.out.println("\tCurrent expanding node:");
        System.out.println("\tType=" + getClass().getName());
        if (getChildren() != null) System.out.println("\tChildren: " + getChildren().toString());
        throw new RuntimeException();
    }
}
