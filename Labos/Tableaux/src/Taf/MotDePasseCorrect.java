package Taf;

import java.util.function.Predicate;

public class MotDePasseCorrect {
    private static final Predicate<String> notNull = pwd -> pwd != null;
    private static final Predicate<String> notEmpty = pwd -> pwd != null && !pwd.trim().isEmpty();
    private static final Predicate<String> longeurMin  = pwd -> {
        return pwd.length() >= 10;
    };
    private static final Predicate<String> lettreMin  = pwd -> {
        return pwd.matches(".*[a-z].*");
    };
    private static final Predicate<String> lettreMaj  = pwd -> pwd != null && pwd.matches(".*[A-Z].*");
    private static final Predicate<String> caractereSpecial = pwd -> pwd != null && pwd.matches(".*[-+!@#$%^&*.,?].*");
    private static final Predicate<String> motDePasseValide = notNull.and(notEmpty)
            .and(lettreMaj.and(lettreMin))
            .and(longeurMin)
            .and(caractereSpecial);

    public static boolean valide(String motDePasse) {
        return motDePasseValide.test(motDePasse);
}
}
