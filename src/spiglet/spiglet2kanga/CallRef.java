package spiglet.spiglet2kanga;

import spiglet.syntaxtree.Call;

public class CallRef extends VarRef{

	CallRef(Call call_) {
		super(Access.Call, 20000);
		call=call_;
	}
	Call call;
}
