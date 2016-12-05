package spiglet.spiglet2kanga;

import java.util.*;

public class ISA {
	
	static public ISAConfig Config=new RISCV();
}

class MIPS extends ISAConfig {
	MIPS() {
		RegName=new String[] {"s0","s1","s2","s3","s4","s5","s6","s7","t0","t1","t2","t3","t4","t5","t6","t7","t8","t9","a0","a1","a2","a3","v0","v1"};
		ArgReg=new int[]{18,19,20,21};
		CalleeSaveReg=new int[]{0,1,2,3,4,5,6,7};
		CallerSaveReg=new int[]{8,9,10,11,12,13,14,15,16,17};
		GPRegNum=22;
		RetValReg=22;
		MemAccReg=new String[]{"v0","v1"};
	}
}

class RISCV extends ISAConfig {
	RISCV() {
		RegName=new String[] {"s0","s1","s2","s3","s4","s5","s6","s7","s8","s9","t0","t1","t2","t3","t4","t5","t6","a0","a1","a2","a3","a4","a5","a6","a7", "s10","s11"};
		ArgReg=new int[]{17,18,19,20,21,22,23,24};
		CalleeSaveReg=new int[]{0,1,2,3,4,5,6,7,8,9};
		CallerSaveReg=new int[]{10,11,12,13,14,15,16};
		GPRegNum=25;
		RetValReg=25;
		MemAccReg=new String[]{"s10","s11"};
	}
}

class ISAConfig {
	public String[] RegName;
	public int [] ArgReg;
	public int [] CalleeSaveReg;
	public int [] CallerSaveReg;
	public int GPRegNum;
	public int RetValReg;
	public String [] MemAccReg;
	
	public boolean isCalleeSave(int RegN) {
		int l=CalleeSaveReg.length;
		for(int i=0;i<l;i++) {
			if(CalleeSaveReg[i]==RegN)
				return true;
		}
		return false;
	}
	
	public boolean isCallerSave(int RegN) {
		int l=CallerSaveReg.length;
		for(int i=0;i<l;i++) {
			if(CallerSaveReg[i]==RegN)
				return true;
		}
		return false;
	}
}
