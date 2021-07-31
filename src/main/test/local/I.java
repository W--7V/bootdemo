package local;

public class I {

}


interface I1{void f();}
interface I2{int f(int i);}
interface I3{int f();}

class C{
	int f() {
		return 1;
	}
}

class C2 implements I1,I2{
	@Override
	public int f(int i) {
		return 1;
	}
	@Override
	public void f() {
		
	}
}

class C3 extends C implements I2{
	@Override
	public int f(int i) {
		return 0;
	}
}

class C4 extends C {
	public int f() {
		return 1;
	}
}