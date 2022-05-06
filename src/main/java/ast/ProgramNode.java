package ast;

import lexer.CL;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramNode implements ASTNode {
    private List<ASTNode> children = null;

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        final List<Integer> r1 = Arrays.asList(CL.If, CL.Function, CL.Break, CL.Continue, CL.For, CL.While, CL.Return,
                CL.NumberLiteral, CL.BoolLiteral, CL.StringLiteral, CL.LBrace, CL.LBrack, CL.LParen, CL.Identifier,
                CL.Import, CL.Export, CL.Let);
        final List<Integer> r2 = List.of(CL.EOF);
        children = new ArrayList<ASTNode>();

        Token next = lexer.getToken();
        if (next == null) {
            System.out.println("Parse start");
            next = lexer.nextToken();
        }
        if (r1.contains(next.getType())) {
            children.add(new ProgramItemNode());
            children.add(new ProgramNode());
        }
        else if (r2.contains(next.getType())) {
            System.out.println("EOF hit");
            return;
        }
        else {
            dispatchError(next);
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
