package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpListNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -17;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var r1 = Arrays.asList(CL.Function, CL.Sub, CL.Exclamation, CL.NumberLiteral, CL.BoolLiteral,
                CL.StringLiteral, CL.LBrack, CL.LBrace, CL.LParen, CL.Identifier);
        if (r1.contains(next.getType())) {
            children.add(new ExpressionNode());
            children.add(new ExpListTailNode());
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
