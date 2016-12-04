package spiglet.spiglet2kanga;

import java.util.*;

public class ISA {
	static public ISAConfig Config=new ISAConfig();
}

class ISAConfig {
	public String[] RegName={"s0","s1","s2","s3","s4","s5","s6","s7","t0","t1","t2","t3","t4","t5","t6","t7","t8","t9","a0","a1","a2","a3","v0"};
	public int [] ArgReg={18,19,20,21};
	public int [] CalleeSaveReg={0,1,2,3,4,5,6,7};
	public int [] CallerSaveReg={8,9,10,11,12,13,14,15,16,17};
	public int GPRegNum=22;
	public String [] MemAccReg={"v0","v1"};
	
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
