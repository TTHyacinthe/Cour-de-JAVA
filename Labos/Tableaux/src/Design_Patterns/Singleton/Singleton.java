package DP.Singleton;

public final class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private String code = "Secret";

    public String getCode() {
        return code;
    }

    public void setCode(String code1, String code2) {
        if (code.equals(code1)) {
            this.code = code2;
        }
    }
}

class Program {
    static void main() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2; // = Singleton.getInstance();

        s2 = appelMethode();
        System.out.println(s1);
        System.out.println(s2);

        System.out.println("Code = " + s1.getCode());
        s1.setCode("Secret", "Geheim");
        System.out.println("Code = " + s2.getCode());
    }

    private static Singleton appelMethode() {
        return Singleton.getInstance();
    }
}