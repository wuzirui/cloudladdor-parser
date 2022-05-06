package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForInitNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -20;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        final var r1 = Arrays.asList(CL.NumberLiteral, CL.BoolLiteral, CL.StringLiteral,
                CL.LBrack, CL.LBrace, CL.LParen, CL.Identifier);
        if (next.getType() == CL.Let) {
            children.add(new DataStatementNode());
        }
        else if (r1.contains(next.getType())) {
            children.add(new ExpressionStatementNode());
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
