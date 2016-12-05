package kanga.kanga2RISCV;

import kanga.syntaxtree.*;
import kanga.visitor.*;

public class Translator extends DepthFirstVisitor {
	public String output;
	int SlotNum;
	public void visit(Goal n) {
		output="\t.text\n"
				+ "\t.global\tmain\n"
				+ "main:\n";
		SlotNum=n.f5.getValue()+1;
		
		output+="\taddi\tsp,sp,-"+SlotNum*4+"\n"
				+ "\tsw\tra,"+(SlotNum-1)*4+"(sp)\n";
		
		n.f10.accept(this);
		output+="\tlw\tra,"+(SlotNum-1)*4+"(sp)\n"
				+ "\taddi\tsp,sp,"+SlotNum*4+"\n"
				+ "\tjr\tra\n\n";
		n.f12.accept(this);	
	}
	
	public void visit(Procedure n) {
		output+="\t.text\n"
				+ "\t.global\t"+n.f0+"\n"
				+ n.f0+":\n";
		
		SlotNum=n.f5.getValue()+1;

		
		output+="\taddi\tsp,sp,-"+SlotNum*4+"\n"
				+ "\tsw\tra,"+(SlotNum-1)*4+"(sp)\n";
		
		n.f10.accept(this);
		
		output+="\tlw\tra,"+(SlotNum-1)*4+"(sp)\n"
				+ "\taddi\tsp,sp,"+SlotNum*4+"\n"
				+ "\tjr\tra\n\n";
	}
	
	public void visit(NoOpStmt n) {
		output+="\taddi\tzero,zero,0\n";
	}
	
	public void visit(ErrorStmt n) {
		output+="\tjal\t_error\n";
	}
	
	public void visit(CJumpStmt n) {
		output+="\tbeqz\t"+n.f1+","+n.f2+"\n";
	}
	
	public void visit(JumpStmt n) {
		output+="\tj\t"+n.f1+"\n";
	}
	
	public void visit(HStoreStmt n) {
		output+="\tsw\t"+n.f3+","+n.f2+"("+n.f1+")\n";
	}
	
	public void visit(HLoadStmt n) {
		output+="\tlw\t"+n.f1+","+n.f3+"("+n.f2+")\n";
	}
	
	public void visit(MoveStmt n) {
		String rd=n.f1.toString();
		if(n.f2.f0.choice instanceof HAllocate) {
			HAllocate alct=(HAllocate) n.f2.f0.choice;
			Node arg=alct.f1.f0.choice;
			if(arg instanceof Reg){
				output+="\tmv\ts10,"+arg+"\n";//TODO ABI-dependent
			} else {
				output+="\tli\ts10,"+arg+"\n";
			}
			output+="\tjal\t_hallocate\n"
					+ "\tmv\t"+rd+",s10\n";
		} else if(n.f2.f0.choice instanceof BinOp) {
			BinOp b=(BinOp) n.f2.f0.choice;
			Node oprd2=b.f2.f0.choice;
			String Oprd2=b.f2.f0.choice.toString();
			String op=b.f0.toString();
			String instr="";
			if(op.equals("LT")) {
				if(oprd2 instanceof Reg) {
					instr="slt";
				} else {
					instr="slti";
				}
			} else if(op.equals("PLUS")) {
				if(oprd2 instanceof Reg) {
					instr="add";
				} else {
					instr="addi";
				}
			} else if(op.equals("MINUS")) {
				if(oprd2 instanceof Reg) {
					instr="sub";
				} else {
					instr="addi";
					Oprd2="-"+Oprd2;
				}
			} else if(op.equals("TIMES")) {
				instr="mul";
				if(oprd2 instanceof Reg) {
					
				} else {
					output+="\tli\ts10,"+Oprd2+"\n";
					Oprd2="s10";
				}
			}
			output+="\t"+instr+"\t"+rd+","+b.f1+","+Oprd2+"\n";
		} else if(n.f2.f0.choice instanceof SimpleExp) {
			SimpleExp s=(SimpleExp) n.f2.f0.choice;
			if(s.f0.choice instanceof Reg) {
				output+="\tmv\t"+rd+","+s.f0.choice+"\n";
			} else if(s.f0.choice instanceof IntegerLiteral){
				output+="\tli\t"+rd+","+s.f0.choice+"\n";
			} else if(s.f0.choice instanceof Label){
				output+="\tla\t"+rd+","+s.f0.choice+"\n";
			}
		} 
	}
	public void visit(PrintStmt n) {
		Node arg=n.f1.f0.choice;
		if(arg instanceof Reg){
			output+="\tmv\ts10,"+arg+"\n";//TODO ABI-dependent
		} else {
			output+="\tli\ts10,"+arg+"\n";
		}
		output+="\tjal\t_print\n";
	}
	public void visit(ALoadStmt n) {
		output+="\tlw\t"+n.f1+","+(SlotNum-2-n.f2.f1.getValue())*4+"(sp)\n";
	}
	
	public void visit(AStoreStmt n) {
		output+="\tsw\t"+n.f2+","+(SlotNum-2-n.f1.f1.getValue())*4+"(sp)\n";
	}
	public void visit(PassArgStmt n) {
		output+="\tsw\t"+n.f2+","+(-1-n.f1.getValue())*4+"(sp)\n";
	}
	public void visit(CallStmt n) {
		Node target=n.f1.f0.choice;
		if(target instanceof Reg) {
			output+="\tjalr\t"+target+"\n";
		} else {
			output+="\tjal\t"+target+"\n";
		}
	}
	
	public void visit(Label n) {
		output+=n+":";
	}
}
