class QuickSort{
    public static void main(String[] str){
        int i;
        i=0;
        while(i<5)
        {
            i=i+1;
            System.out.println(i);
        }
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

