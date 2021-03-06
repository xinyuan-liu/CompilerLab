//
// Generated by JTB 1.3.2
//

package kanga.syntaxtree;

/**
 * Grammar production:
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
public class Reg implements Node {
   public NodeChoice f0;

   public String toString() {
	   return f0.choice.toString();
   }
   
   public Reg(NodeChoice n0) {
      f0 = n0;
   }

   public void accept(kanga.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(kanga.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(kanga.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(kanga.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

