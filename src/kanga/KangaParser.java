package kanga;
import java.util.Vector;

import kanga.syntaxtree.*;


public class KangaParser implements KangaParserConstants {

  static final public Goal Goal() throws ParseException {
   NodeToken n0;
   Token n1;
   NodeToken n2;
   Token n3;
   IntegerLiteral n4;
   NodeToken n5;
   Token n6;
   NodeToken n7;
   Token n8;
   IntegerLiteral n9;
   NodeToken n10;
   Token n11;
   NodeToken n12;
   Token n13;
   IntegerLiteral n14;
   NodeToken n15;
   Token n16;
   StmtList n17;
   NodeToken n18;
   Token n19;
   NodeListOptional n20 = new NodeListOptional();
   Procedure n21;
   NodeToken n22;
   Token n23;
    n1 = jj_consume_token(MAIN);
               n0 = JTBToolkit.makeNodeToken(n1);
    n3 = jj_consume_token(LSQPAREN);
            n2 = JTBToolkit.makeNodeToken(n3);
    n4 = IntegerLiteral();
    n6 = jj_consume_token(RSQPAREN);
            n5 = JTBToolkit.makeNodeToken(n6);
    n8 = jj_consume_token(LSQPAREN);
            n7 = JTBToolkit.makeNodeToken(n8);
    n9 = IntegerLiteral();
    n11 = jj_consume_token(RSQPAREN);
             n10 = JTBToolkit.makeNodeToken(n11);
    n13 = jj_consume_token(LSQPAREN);
             n12 = JTBToolkit.makeNodeToken(n13);
    n14 = IntegerLiteral();
    n16 = jj_consume_token(RSQPAREN);
             n15 = JTBToolkit.makeNodeToken(n16);
    n17 = StmtList();
    n19 = jj_consume_token(END);
               n18 = JTBToolkit.makeNodeToken(n19);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      n21 = Procedure();
        n20.addNode(n21);
    }
     n20.nodes.trimToSize();
    n23 = jj_consume_token(0);
      n23.beginColumn++; n23.endColumn++;
      n22 = JTBToolkit.makeNodeToken(n23);
     {if (true) return new Goal(n0,n2,n4,n5,n7,n9,n10,n12,n14,n15,n17,n18,n20,n22);}
    throw new Error("Missing return statement in function");
  }

  static final public StmtList StmtList() throws ParseException {
   NodeListOptional n0 = new NodeListOptional();
   NodeSequence n1;
   NodeOptional n2;
   Label n3;
   Stmt n4;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOOP:
      case MOVE:
      case CALL:
      case ERROR:
      case PRINT:
      case JUMP:
      case CJUMP:
      case HSTORE:
      case HLOAD:
      case ALOAD:
      case ASTORE:
      case PASSARG:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
        n2 = new NodeOptional();
        n1 = new NodeSequence(2);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        n3 = Label();
           n2.addNode(n3);
        break;
      default:
        jj_la1[2] = jj_gen;
        ;
      }
        n1.addNode(n2);
      n4 = Stmt();
        n1.addNode(n4);
        n0.addNode(n1);
    }
     n0.nodes.trimToSize();
     {if (true) return new StmtList(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public Procedure Procedure() throws ParseException {
   Label n0;
   NodeToken n1;
   Token n2;
   IntegerLiteral n3;
   NodeToken n4;
   Token n5;
   NodeToken n6;
   Token n7;
   IntegerLiteral n8;
   NodeToken n9;
   Token n10;
   NodeToken n11;
   Token n12;
   IntegerLiteral n13;
   NodeToken n14;
   Token n15;
   StmtList n16;
   NodeToken n17;
   Token n18;
    n0 = Label();
    n2 = jj_consume_token(LSQPAREN);
            n1 = JTBToolkit.makeNodeToken(n2);
    n3 = IntegerLiteral();
    n5 = jj_consume_token(RSQPAREN);
            n4 = JTBToolkit.makeNodeToken(n5);
    n7 = jj_consume_token(LSQPAREN);
            n6 = JTBToolkit.makeNodeToken(n7);
    n8 = IntegerLiteral();
    n10 = jj_consume_token(RSQPAREN);
             n9 = JTBToolkit.makeNodeToken(n10);
    n12 = jj_consume_token(LSQPAREN);
             n11 = JTBToolkit.makeNodeToken(n12);
    n13 = IntegerLiteral();
    n15 = jj_consume_token(RSQPAREN);
             n14 = JTBToolkit.makeNodeToken(n15);
    n16 = StmtList();
    n18 = jj_consume_token(END);
               n17 = JTBToolkit.makeNodeToken(n18);
     {if (true) return new Procedure(n0,n1,n3,n4,n6,n8,n9,n11,n13,n14,n16,n17);}
    throw new Error("Missing return statement in function");
  }

  static final public Stmt Stmt() throws ParseException {
   NodeChoice n0;
   NoOpStmt n1;
   ErrorStmt n2;
   CJumpStmt n3;
   JumpStmt n4;
   HStoreStmt n5;
   HLoadStmt n6;
   MoveStmt n7;
   PrintStmt n8;
   ALoadStmt n9;
   AStoreStmt n10;
   PassArgStmt n11;
   CallStmt n12;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOOP:
      n1 = NoOpStmt();
        n0 = new NodeChoice(n1, 0);
      break;
    case ERROR:
      n2 = ErrorStmt();
        n0 = new NodeChoice(n2, 1);
      break;
    case CJUMP:
      n3 = CJumpStmt();
        n0 = new NodeChoice(n3, 2);
      break;
    case JUMP:
      n4 = JumpStmt();
        n0 = new NodeChoice(n4, 3);
      break;
    case HSTORE:
      n5 = HStoreStmt();
        n0 = new NodeChoice(n5, 4);
      break;
    case HLOAD:
      n6 = HLoadStmt();
        n0 = new NodeChoice(n6, 5);
      break;
    case MOVE:
      n7 = MoveStmt();
        n0 = new NodeChoice(n7, 6);
      break;
    case PRINT:
      n8 = PrintStmt();
        n0 = new NodeChoice(n8, 7);
      break;
    case ALOAD:
      n9 = ALoadStmt();
        n0 = new NodeChoice(n9, 8);
      break;
    case ASTORE:
      n10 = AStoreStmt();
        n0 = new NodeChoice(n10, 9);
      break;
    case PASSARG:
      n11 = PassArgStmt();
        n0 = new NodeChoice(n11, 10);
      break;
    case CALL:
      n12 = CallStmt();
        n0 = new NodeChoice(n12, 11);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return new Stmt(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public NoOpStmt NoOpStmt() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(NOOP);
               n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new NoOpStmt(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public ErrorStmt ErrorStmt() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(ERROR);
                n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new ErrorStmt(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public CJumpStmt CJumpStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   Reg n2;
   Label n3;
    n1 = jj_consume_token(CJUMP);
                n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Reg();
    n3 = Label();
     {if (true) return new CJumpStmt(n0,n2,n3);}
    throw new Error("Missing return statement in function");
  }

  static final public JumpStmt JumpStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   Label n2;
    n1 = jj_consume_token(JUMP);
               n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Label();
     {if (true) return new JumpStmt(n0,n2);}
    throw new Error("Missing return statement in function");
  }

  static final public HStoreStmt HStoreStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   Reg n2;
   IntegerLiteral n3;
   Reg n4;
    n1 = jj_consume_token(HSTORE);
                 n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Reg();
    n3 = IntegerLiteral();
    n4 = Reg();
     {if (true) return new HStoreStmt(n0,n2,n3,n4);}
    throw new Error("Missing return statement in function");
  }

  static final public HLoadStmt HLoadStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   Reg n2;
   Reg n3;
   IntegerLiteral n4;
    n1 = jj_consume_token(HLOAD);
                n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Reg();
    n3 = Reg();
    n4 = IntegerLiteral();
     {if (true) return new HLoadStmt(n0,n2,n3,n4);}
    throw new Error("Missing return statement in function");
  }

  static final public MoveStmt MoveStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   Reg n2;
   Exp n3;
    n1 = jj_consume_token(MOVE);
               n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Reg();
    n3 = Exp();
     {if (true) return new MoveStmt(n0,n2,n3);}
    throw new Error("Missing return statement in function");
  }

  static final public PrintStmt PrintStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   SimpleExp n2;
    n1 = jj_consume_token(PRINT);
                n0 = JTBToolkit.makeNodeToken(n1);
    n2 = SimpleExp();
     {if (true) return new PrintStmt(n0,n2);}
    throw new Error("Missing return statement in function");
  }

  static final public ALoadStmt ALoadStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   Reg n2;
   SpilledArg n3;
    n1 = jj_consume_token(ALOAD);
                n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Reg();
    n3 = SpilledArg();
     {if (true) return new ALoadStmt(n0,n2,n3);}
    throw new Error("Missing return statement in function");
  }

  static final public AStoreStmt AStoreStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   SpilledArg n2;
   Reg n3;
    n1 = jj_consume_token(ASTORE);
                 n0 = JTBToolkit.makeNodeToken(n1);
    n2 = SpilledArg();
    n3 = Reg();
     {if (true) return new AStoreStmt(n0,n2,n3);}
    throw new Error("Missing return statement in function");
  }

  static final public PassArgStmt PassArgStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   IntegerLiteral n2;
   Reg n3;
    n1 = jj_consume_token(PASSARG);
                  n0 = JTBToolkit.makeNodeToken(n1);
    n2 = IntegerLiteral();
    n3 = Reg();
     {if (true) return new PassArgStmt(n0,n2,n3);}
    throw new Error("Missing return statement in function");
  }

  static final public CallStmt CallStmt() throws ParseException {
   NodeToken n0;
   Token n1;
   SimpleExp n2;
    n1 = jj_consume_token(CALL);
               n0 = JTBToolkit.makeNodeToken(n1);
    n2 = SimpleExp();
     {if (true) return new CallStmt(n0,n2);}
    throw new Error("Missing return statement in function");
  }

  static final public Exp Exp() throws ParseException {
   NodeChoice n0;
   HAllocate n1;
   BinOp n2;
   SimpleExp n3;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HALLOCATE:
      n1 = HAllocate();
        n0 = new NodeChoice(n1, 0);
      break;
    case LT:
    case PLUS:
    case MINUS:
    case TIMES:
      n2 = BinOp();
        n0 = new NodeChoice(n2, 1);
      break;
    case t0:
    case t1:
    case t2:
    case s0:
    case s1:
    case a0:
    case a1:
    case a2:
    case a3:
    case a4:
    case a5:
    case a6:
    case a7:
    case s2:
    case s3:
    case s4:
    case s5:
    case s6:
    case s7:
    case s8:
    case s9:
    case s10:
    case s11:
    case t3:
    case t4:
    case t5:
    case t6:
    case INTEGER_LITERAL:
    case IDENTIFIER:
      n3 = SimpleExp();
        n0 = new NodeChoice(n3, 2);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return new Exp(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public HAllocate HAllocate() throws ParseException {
   NodeToken n0;
   Token n1;
   SimpleExp n2;
    n1 = jj_consume_token(HALLOCATE);
                    n0 = JTBToolkit.makeNodeToken(n1);
    n2 = SimpleExp();
     {if (true) return new HAllocate(n0,n2);}
    throw new Error("Missing return statement in function");
  }

  static final public BinOp BinOp() throws ParseException {
   Operator n0;
   Reg n1;
   SimpleExp n2;
    n0 = Operator();
    n1 = Reg();
    n2 = SimpleExp();
     {if (true) return new BinOp(n0,n1,n2);}
    throw new Error("Missing return statement in function");
  }

  static final public Operator Operator() throws ParseException {
   NodeChoice n0;
   NodeToken n1;
   Token n2;
   NodeToken n3;
   Token n4;
   NodeToken n5;
   Token n6;
   NodeToken n7;
   Token n8;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LT:
      n2 = jj_consume_token(LT);
                n1 = JTBToolkit.makeNodeToken(n2);
        n0 = new NodeChoice(n1, 0);
      break;
    case PLUS:
      n4 = jj_consume_token(PLUS);
                  n3 = JTBToolkit.makeNodeToken(n4);
        n0 = new NodeChoice(n3, 1);
      break;
    case MINUS:
      n6 = jj_consume_token(MINUS);
                   n5 = JTBToolkit.makeNodeToken(n6);
        n0 = new NodeChoice(n5, 2);
      break;
    case TIMES:
      n8 = jj_consume_token(TIMES);
                   n7 = JTBToolkit.makeNodeToken(n8);
        n0 = new NodeChoice(n7, 3);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return new Operator(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public SpilledArg SpilledArg() throws ParseException {
   NodeToken n0;
   Token n1;
   IntegerLiteral n2;
    n1 = jj_consume_token(SPILLEDARG);
                     n0 = JTBToolkit.makeNodeToken(n1);
    n2 = IntegerLiteral();
     {if (true) return new SpilledArg(n0,n2);}
    throw new Error("Missing return statement in function");
  }

  static final public SimpleExp SimpleExp() throws ParseException {
   NodeChoice n0;
   Reg n1;
   IntegerLiteral n2;
   Label n3;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case t0:
    case t1:
    case t2:
    case s0:
    case s1:
    case a0:
    case a1:
    case a2:
    case a3:
    case a4:
    case a5:
    case a6:
    case a7:
    case s2:
    case s3:
    case s4:
    case s5:
    case s6:
    case s7:
    case s8:
    case s9:
    case s10:
    case s11:
    case t3:
    case t4:
    case t5:
    case t6:
      n1 = Reg();
        n0 = new NodeChoice(n1, 0);
      break;
    case INTEGER_LITERAL:
      n2 = IntegerLiteral();
        n0 = new NodeChoice(n2, 1);
      break;
    case IDENTIFIER:
      n3 = Label();
        n0 = new NodeChoice(n3, 2);
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return new SimpleExp(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public Reg Reg() throws ParseException {
   NodeChoice n0;
   NodeToken n1;
   Token n2;
   NodeToken n3;
   Token n4;
   NodeToken n5;
   Token n6;
   NodeToken n7;
   Token n8;
   NodeToken n9;
   Token n10;
   NodeToken n11;
   Token n12;
   NodeToken n13;
   Token n14;
   NodeToken n15;
   Token n16;
   NodeToken n17;
   Token n18;
   NodeToken n19;
   Token n20;
   NodeToken n21;
   Token n22;
   NodeToken n23;
   Token n24;
   NodeToken n25;
   Token n26;
   NodeToken n27;
   Token n28;
   NodeToken n29;
   Token n30;
   NodeToken n31;
   Token n32;
   NodeToken n33;
   Token n34;
   NodeToken n35;
   Token n36;
   NodeToken n37;
   Token n38;
   NodeToken n39;
   Token n40;
   NodeToken n41;
   Token n42;
   NodeToken n43;
   Token n44;
   NodeToken n45;
   Token n46;
   NodeToken n47;
   Token n48;
   NodeToken n49;
   Token n50;
   NodeToken n51;
   Token n52;
   NodeToken n53;
   Token n54;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case a0:
      n2 = jj_consume_token(a0);
                n1 = JTBToolkit.makeNodeToken(n2);
        n0 = new NodeChoice(n1, 0);
      break;
    case a1:
      n4 = jj_consume_token(a1);
                n3 = JTBToolkit.makeNodeToken(n4);
        n0 = new NodeChoice(n3, 1);
      break;
    case a2:
      n6 = jj_consume_token(a2);
                n5 = JTBToolkit.makeNodeToken(n6);
        n0 = new NodeChoice(n5, 2);
      break;
    case a3:
      n8 = jj_consume_token(a3);
                n7 = JTBToolkit.makeNodeToken(n8);
        n0 = new NodeChoice(n7, 3);
      break;
    case a4:
      n10 = jj_consume_token(a4);
                 n9 = JTBToolkit.makeNodeToken(n10);
        n0 = new NodeChoice(n9, 4);
      break;
    case a5:
      n12 = jj_consume_token(a5);
                 n11 = JTBToolkit.makeNodeToken(n12);
        n0 = new NodeChoice(n11, 5);
      break;
    case a6:
      n14 = jj_consume_token(a6);
                 n13 = JTBToolkit.makeNodeToken(n14);
        n0 = new NodeChoice(n13, 6);
      break;
    case a7:
      n16 = jj_consume_token(a7);
                 n15 = JTBToolkit.makeNodeToken(n16);
        n0 = new NodeChoice(n15, 7);
      break;
    case t0:
      n18 = jj_consume_token(t0);
                 n17 = JTBToolkit.makeNodeToken(n18);
        n0 = new NodeChoice(n17, 8);
      break;
    case t1:
      n20 = jj_consume_token(t1);
                 n19 = JTBToolkit.makeNodeToken(n20);
        n0 = new NodeChoice(n19, 9);
      break;
    case t2:
      n22 = jj_consume_token(t2);
                 n21 = JTBToolkit.makeNodeToken(n22);
        n0 = new NodeChoice(n21, 10);
      break;
    case t3:
      n24 = jj_consume_token(t3);
                 n23 = JTBToolkit.makeNodeToken(n24);
        n0 = new NodeChoice(n23, 11);
      break;
    case t4:
      n26 = jj_consume_token(t4);
                 n25 = JTBToolkit.makeNodeToken(n26);
        n0 = new NodeChoice(n25, 12);
      break;
    case t5:
      n28 = jj_consume_token(t5);
                 n27 = JTBToolkit.makeNodeToken(n28);
        n0 = new NodeChoice(n27, 13);
      break;
    case t6:
      n30 = jj_consume_token(t6);
                 n29 = JTBToolkit.makeNodeToken(n30);
        n0 = new NodeChoice(n29, 14);
      break;
    case s0:
      n32 = jj_consume_token(s0);
                 n31 = JTBToolkit.makeNodeToken(n32);
        n0 = new NodeChoice(n31, 15);
      break;
    case s1:
      n34 = jj_consume_token(s1);
                 n33 = JTBToolkit.makeNodeToken(n34);
        n0 = new NodeChoice(n33, 16);
      break;
    case s2:
      n36 = jj_consume_token(s2);
                 n35 = JTBToolkit.makeNodeToken(n36);
        n0 = new NodeChoice(n35, 17);
      break;
    case s3:
      n38 = jj_consume_token(s3);
                 n37 = JTBToolkit.makeNodeToken(n38);
        n0 = new NodeChoice(n37, 18);
      break;
    case s4:
      n40 = jj_consume_token(s4);
                 n39 = JTBToolkit.makeNodeToken(n40);
        n0 = new NodeChoice(n39, 19);
      break;
    case s5:
      n42 = jj_consume_token(s5);
                 n41 = JTBToolkit.makeNodeToken(n42);
        n0 = new NodeChoice(n41, 20);
      break;
    case s6:
      n44 = jj_consume_token(s6);
                 n43 = JTBToolkit.makeNodeToken(n44);
        n0 = new NodeChoice(n43, 21);
      break;
    case s7:
      n46 = jj_consume_token(s7);
                 n45 = JTBToolkit.makeNodeToken(n46);
        n0 = new NodeChoice(n45, 22);
      break;
    case s8:
      n48 = jj_consume_token(s8);
                 n47 = JTBToolkit.makeNodeToken(n48);
        n0 = new NodeChoice(n47, 23);
      break;
    case s9:
      n50 = jj_consume_token(s9);
                 n49 = JTBToolkit.makeNodeToken(n50);
        n0 = new NodeChoice(n49, 24);
      break;
    case s10:
      n52 = jj_consume_token(s10);
                  n51 = JTBToolkit.makeNodeToken(n52);
        n0 = new NodeChoice(n51, 25);
      break;
    case s11:
      n54 = jj_consume_token(s11);
                  n53 = JTBToolkit.makeNodeToken(n54);
        n0 = new NodeChoice(n53, 26);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return new Reg(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public IntegerLiteral IntegerLiteral() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(INTEGER_LITERAL);
                          n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new IntegerLiteral(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public Label Label() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(IDENTIFIER);
                     n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new Label(n0);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public KangaParserTokenManager token_source;
  static JavaCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x48c10000,0x8c10000,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x7f3e,0x0,0x7f3e,0xff800000,0x0,0xff800000,0xff800000,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x80000,0x80000,0x80000,0x0,0xfffff,0x0,0xfffff,0x3ffff,};
   }

  /** Constructor with InputStream. */
  public KangaParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public KangaParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new KangaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public KangaParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new KangaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public KangaParser(KangaParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(KangaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[86];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 86; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}

class JTBToolkit {
   static NodeToken makeNodeToken(Token t) {
      return new NodeToken(t.image.intern(), t.kind, t.beginLine, t.beginColumn, t.endLine, t.endColumn);
   }
}
