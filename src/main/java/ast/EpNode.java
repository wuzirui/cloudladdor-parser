package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EpNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -21;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var r1 = List.of(CL.LBrack);
        var r2 = Arrays.asList(CL.Add, CL.Sub, CL.Mul, CL.Div, CL.GE, CL.GT, CL.LT, CL.LE, CL.Dot,
                CL.And, CL.Or, CL.Equal, CL.Mod);
        var r3 = Arrays.asList(CL.RParen, CL.Semi, CL.Comma, CL.LBrack, CL.RBrack);

        if (r1.contains(next.getType())) {
            children.add(new TokenNode(CL.LBrack));
            children.add(new ExpressionNode());
            children.add(new TokenNode(CL.RBrack));
        }
        else if (r2.contains(next.getType())) {
            children.add(new TokenNode(next.getType()));
            children.add(new ExpressionNode());
            children.add(new EpNode());
        }
        else if (r3.contains(next.getType())) {
            return;
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
