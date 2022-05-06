package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ObjItemNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();
    @Override
    public int getType() {
        return -12;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var r1 = List.of(CL.Identifier);
        var r2 = List.of(CL.StringLiteral);
        var r3 = List.of(CL.LBrack);;
        if (r1.contains(next.getType())) {
            children.add(new TokenNode(CL.Identifier));
            children.add(new ObjPostfixNode());
        }
        else if (r2.contains(next.getType())) {
            children.add(new TokenNode(CL.StringLiteral));
            children.add(new TokenNode(CL.Colon));
            children.add(new ExpressionNode());
        }
        else if (r3.contains(next.getType())) {
            children.add(new TokenNode(CL.LBrack));
            children.add(new ExpressionNode());
            children.add(new TokenNode(CL.RBrack));
            children.add(new TokenNode(CL.Colon));
            children.add(new ExpressionNode());
        }
        else {
            dispatchError(next);
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
