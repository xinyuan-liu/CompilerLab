class QuickSort{
    public static void main(String[] str){
        A a;
        a=new A();
        
        System.out.println((a.a()).b());
    }
}
class A{
    public B a()
    {
        B b;
        b=new B();
        return b;
    }
}
class B{
    int a;
    public int b()
    {
        int c;
        a=1;
        c=3;
        return a+c;
    }
}

