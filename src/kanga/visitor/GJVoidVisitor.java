//
// Generated by JTB 1.3.2
//

package kanga.visitor;
import java.util.*;

import kanga.syntaxtree.*;

/**
 * All GJ void visitors must implement this interface.
 */

public interface GJVoidVisitor<A> {

   //
   // GJ void Auto class visitors
   //

   public void visit(NodeList n, A argu);
   public void visit(NodeListOptional n, A argu);
   public void visit(NodeOptional n, A argu);
   public void visit(NodeSequence n, A argu);
   public void visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> "MAIN"
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> "["
    * f5 -> IntegerLiteral()
    * f6 -> "]"
    * f7 -> "["
    * f8 -> IntegerLiteral()
    * f9 -> "]"
    * f10 -> StmtList()
    * f11 -> "END"
    * f12 -> ( Procedure() )*
    * f13 -> <EOF>
    */
   public void visit(Goal n, A argu);

   /**
    * f0 -> ( ( Label() )? Stmt() )*
    */
   public void visit(StmtList n, A argu);

   /**
    * f0 -> Label()
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> "["
    * f5 -> IntegerLiteral()
    * f6 -> "]"
    * f7 -> "["
    * f8 -> IntegerLiteral()
    * f9 -> "]"
    * f10 -> StmtList()
    * f11 -> "END"
    */
   public void visit(Procedure n, A argu);

   /**
    * f0 -> NoOpStmt()
    *       | ErrorStmt()
    *       | CJumpStmt()
    *       | JumpStmt()
    *       | HStoreStmt()
    *       | HLoadStmt()
    *       | MoveStmt()
    *       | PrintStmt()
    *       | ALoadStmt()
    *       | AStoreStmt()
    *       | PassArgStmt()
    *       | CallStmt()
    */
   public void visit(Stmt n, A argu);

   /**
    * f0 -> "NOOP"
    */
   public void visit(NoOpStmt n, A argu);

   /**
    * f0 -> "ERROR"
    */
   public void visit(ErrorStmt n, A argu);

   /**
    * f0 -> "CJUMP"
    * f1 -> Reg()
    * f2 -> Label()
    */
   public void visit(CJumpStmt n, A argu);

   /**
    * f0 -> "JUMP"
    * f1 -> Label()
    */
   public void visit(JumpStmt n, A argu);

   /**
    * f0 -> "HSTORE"
    * f1 -> Reg()
    * f2 -> IntegerLiteral()
    * f3 -> Reg()
    */
   public void visit(HStoreStmt n, A argu);

   /**
    * f0 -> "HLOAD"
    * f1 -> Reg()
    * f2 -> Reg()
    * f3 -> IntegerLiteral()
    */
   public void visit(HLoadStmt n, A argu);

   /**
    * f0 -> "MOVE"
    * f1 -> Reg()
    * f2 -> Exp()
    */
   public void visit(MoveStmt n, A argu);

   /**
    * f0 -> "PRINT"
    * f1 -> SimpleExp()
    */
   public void visit(PrintStmt n, A argu);

   /**
    * f0 -> "ALOAD"
    * f1 -> Reg()
    * f2 -> SpilledArg()
    */
   public void visit(ALoadStmt n, A argu);

   /**
    * f0 -> "ASTORE"
    * f1 -> SpilledArg()
    * f2 -> Reg()
    */
   public void visit(AStoreStmt n, A argu);

   /**
    * f0 -> "PASSARG"
    * f1 -> IntegerLiteral()
    * f2 -> Reg()
    */
   public void visit(PassArgStmt n, A argu);

   /**
    * f0 -> "CALL"
    * f1 -> SimpleExp()
    */
   public void visit(CallStmt n, A argu);

   /**
    * f0 -> HAllocate()
    *       | BinOp()
    *       | SimpleExp()
    */
   public void visit(Exp n, A argu);

   /**
    * f0 -> "HALLOCATE"
    * f1 -> SimpleExp()
    */
   public void visit(HAllocate n, A argu);

   /**
    * f0 -> Operator()
    * f1 -> Reg()
    * f2 -> SimpleExp()
    */
   public void visit(BinOp n, A argu);

   /**
    * f0 -> "LT"
    *       | "PLUS"
    *       | "MINUS"
    *       | "TIMES"
    */
   public void visit(Operator n, A argu);

   /**
    * f0 -> "SPILLEDARG"
    * f1 -> IntegerLiteral()
    */
   public void visit(SpilledArg n, A argu);

   /**
    * f0 -> Reg()
    *       | IntegerLiteral()
    *       | Label()
    */
   public void visit(SimpleExp n, A argu);

   /**
    * f0 -> "a0"
    *       | "a1"
    *       | "a2"
    *       | "a3"
    *       | "a4"
    *       | "a5"
    *       | "a6"
    *       | "a7"
    *       | "t0"
    *       | "t1"
    *       | "t2"
    *       | "t3"
    *       | "t4"
    *       | "t5"
    *       | "t6"
    *       | "s0"
    *       | "s1"
    *       | "s2"
    *       | "s3"
    *       | "s4"
    *       | "s5"
    *       | "s6"
    *       | "s7"
    *       | "s8"
    *       | "s9"
    *       | "s10"
    *       | "s11"
    */
   public void visit(Reg n, A argu);

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public void visit(IntegerLiteral n, A argu);

   /**
    * f0 -> <IDENTIFIER>
    */
   public void visit(Label n, A argu);

}

