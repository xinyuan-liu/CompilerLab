class QuickSort{
    public static void main(String[] str){
        int i;
        A a;
        a=new A();
        i=0;
        if(true&&((a.print())<5))
        {
            i=i+1;
            System.out.println(i);
        }
        else {i=i+1;}
    }
}
class A extends B{
    public B a()
    {
        B b;
        b=new B();
        return b;
    }
    public int b()
    {
        int ans;
        ans=5;
        ans=ans+ans;
        return ans;
    }
    public int print()
    {
        System.out.println(1);
        return 0;
    }
}
class B{
    int a;
    public int c()
    {
        return a;
    }
    public int b()
    {
        int c;
        a=1;
        c=3;
        return this.c();
    }
}

