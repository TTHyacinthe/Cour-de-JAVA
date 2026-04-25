package GestionDuPersonnel.Fonction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FonctionTest {
    @Test
    void testFonction() {

        Fonction fonction =
                new Fonction(
                        TypeFonction.MANAGER,
                        3500
                );

        assertEquals(
                TypeFonction.MANAGER,
                fonction.getTypeFonction()
        );

        assertEquals(
                3500,
                fonction.getBareme()
        );
    }


}