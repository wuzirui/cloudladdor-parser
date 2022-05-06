package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimaryExpressionNode implements ASTNode {
    private final List<ASTNode> children = new ArrayList<>();
    @Override
    public int getType() {
        return -9;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        final var r1 = Arrays.asList(CL.NumberLiteral, CL.BoolLiteral, CL.StringLiteral);
        final var r2 = List.of(CL.LBrack);
        final var r3 = List.of(CL.LBrace);
        final var r4 = List.of(CL.LParen);
        final var r5 = List.of(CL.Identifier);

        if (r1.contains(next.getType())) {
            children.add(new TokenNode(next.getType()));
        }
        else if (r2.contains(next.getType())) {
            children.add(new ArrayLiteralNode());
        }
        else if (r3.contains(next.getType())) {
            children.add(new ObjLiteralNode());
        }
        else if (r4.contains(next.getType())) {
            children.add(new TokenNode(CL.LParen));
            children.add(new ExpressionNode());
            children.add(new TokenNode(CL.RParen));
        }
        else if (r5.contains(next.getType())) {
            children.add(new TokenNode(CL.Identifier));
        }
        else {
            dispatchError(next);
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
