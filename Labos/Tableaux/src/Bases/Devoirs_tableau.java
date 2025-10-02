package Bases;

public class Devoirs_tableau {
    public static void main(String[] args) {
        // v1
        char [] lettres = new char[26];
        for (int i = 0; i < 26; i++){
            lettres[i] = (char)('a'+i);
        }
        /* v1.1
        for (char c = 'a'; c <= 'z'; c++){
            lettres[i++] = c;
        } */
        // v2.1
        char [] lettresMaj = new char[lettres.length];
        for (int j = 0, i = lettres.length - 1 ; j < lettres.length; j++, i--){
            lettresMaj[j] = (char) (lettres[i] - 32);
        }
        //v2.2
        for (int k = 0; k < lettresMaj.length; k++){
            System.out.println(lettresMaj[k]);
        }
        int[][] tableau = new int[10][10];
        int k = 1;
        for (int i = 0; i < tableau.length; i++){
            for (int j = 0; j < tableau[i].length; j++){
                tableau[i][j] = k++;
            }
        }
        for (int i = 0; i < tableau.length; i++){
            for (int j = 0; j < tableau[i].length; j++){
                System.out.printf("%d ", tableau[i][j]);
            }
            System.out.println();
        }

        tableau[0][0] = 0;
        for (int i = 0; i < tableau.length; i++){
            for (int j = 0; j < tableau[i].length; j++){
                if (tableau[i][j] == 0){
                    continue;
                }
                int t = tableau[i][j];
                for (int l = i; l < tableau.length; l++){
                    for (int m = l + 1; m < tableau[i].length; m++){

                    }
                }
            }
        }


    }
}
// algorithme qui permet d'afficher les nombres premier < 100