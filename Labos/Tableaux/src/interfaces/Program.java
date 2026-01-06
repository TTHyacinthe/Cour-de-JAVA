package interfaces;

public class Program {
    static void main(){
        File f1 = new File();
        Pile p1 = new Pile();

        ContratFilePile c1 = new File();
        c1.push(1);
        c1 = f1;
        c1.push(2);

        c1 = p1;
        c1.push(3);
    }
}
