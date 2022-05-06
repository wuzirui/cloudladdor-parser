package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayLiteralTailNode implements ASTNode {

    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -16;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        final var r = Arrays.asList(CL.Function, CL.Sub, CL.Exclamation, CL.NumberLiteral, CL.BoolLiteral,
            CL.StringLiteral, CL.LBrack, CL.LBrace, CL.LParen, CL.Identifier);
        if (next.getType() == CL.RBrack) {
            children.add(new TokenNode(CL.RBrack));
        }
        else if (r.contains(next.getType())) {
            children.add(new ExpListNode());
            children.add(new TokenNode(CL.RBrack));
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
