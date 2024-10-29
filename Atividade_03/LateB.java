//Isabella Arao 9265732, Marina Fagundes 9265405

class A {
    private int m;
    private int n;

    public A(int mIn, int nIn) {
        m = mIn;
        n = nIn;
    }

    public void m1() {
        m = m + n;
    }

    public int getM() { //recuperar variavel privada 
        return m;
    }

    public int getN() {//recuperar variavel privada 
        return n;
    }

    public void setM(int value) {//modificar a variável privada 
        m = value;
    }

    @Override //sobrescrever toString 
    public String toString() {
        return "A = (" + m + ", " + n + ")";
    }
}

public class B extends A {//B herda de A 

    public B(int mIn, int nIn) { 
        super(mIn, nIn); // Chamando o construtor da superclasse para inicializar m e n
    }

    @Override //sobrescrever m1 para classe B 
    public void m1() { 
        int tempm = getM();
        int tempn = getN();
        int tempR =  tempm - tempn;
        setM(tempR); 
    }
    
    @Override //sobrescrever toString 
    public String toString() {
        return "B = (" + getM() + ", " + getN() + ")";
    }

    public static void main(String[] args) {
        A a = new A(1, 2);
        A b = new B(1, 2);
        System.out.println(a + " " + b);//print antes da modificacao 
        a.m1(); //modificar os valores que serao printados 
        b.m1();
        System.out.println(a + " " + b);
    }
}