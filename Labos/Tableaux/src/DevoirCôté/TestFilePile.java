package DevoirCôté;

public class TestFilePile {
    public static void main(String[] args){
        System.out.println("======== TEST PILE ========");
        Pile pile = new Pile(3);
        pile.push(10);
        pile.push(20);
        pile.push(30);
        pile.push(40);
        System.out.println(pile);
        System.out.println("pop : " + pile.pop().orElse(-1));
        System.out.println(pile);
        System.out.println("======== TEST FILE ========");
        File file = new File(3);
        file.push(10);
        file.push(20);
        file.push(30);
        file.push(40);
        System.out.println(file);
        System.out.println("pop : " + file.pop().orElse(-1));
        System.out.println(file);
    }
}
