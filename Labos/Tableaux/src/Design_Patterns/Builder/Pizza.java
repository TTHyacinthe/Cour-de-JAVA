package Design_Patterns.Builder;

import java.util.HashSet;
import java.util.Set;

public class Pizza {
    private boolean cheesyCrust = false;
    private DP.Builder.PIZZASIZE pizzaSize;
    private boolean calzone = false;
    private boolean champignon = false;
    private boolean jambon = false;
    private boolean oignon = false;
    private boolean ananas = false;
    private boolean salami = false;
    private Set<FROMAGES> fromages = new HashSet<>();

    private Pizza(boolean cheesyCrust, DP.Builder.PIZZASIZE pizzaSize, boolean calzone, boolean champignon, boolean jambon, boolean oignon, boolean ananas, boolean salami, Set<FROMAGES> fromages) {
        this.cheesyCrust = cheesyCrust;
        this.pizzaSize = pizzaSize;
        this.calzone = calzone;
        this.champignon = champignon;
        this.jambon = jambon;
        this.oignon = oignon;
        this.ananas = ananas;
        this.salami = salami;
        this.fromages = fromages;
    }

    public String toString() {
        String mapizza = "Cette pizza contient :\n";
        if (champignon) mapizza += "\tdes champignons\n";
        if (jambon) mapizza += "\tdu jambon\n";
        if (oignon) mapizza += "\tdes oignons\n";
        if (ananas) mapizza += "\tdes ananas\n";
        if (salami) mapizza += "\tdu salami\n";
        if (fromages.contains(FROMAGES.GORGONZOLA)) mapizza += "\tdu gorgonzola\n";
        if (fromages.contains(FROMAGES.COMTE)) mapizza += "\tdu comté\n";
        if (fromages.contains(FROMAGES.EMMENTAL)) mapizza += "\tde l'emmental\n";
        if (fromages.contains(FROMAGES.MOZZARELA)) mapizza += "\tde la mozzarela\n";
        if (fromages.contains(FROMAGES.PARMESAN)) mapizza += "\tdu parmesan\n";
        if (cheesyCrust) mapizza += "\telle a une croute de fromage\n";
        if (calzone) mapizza += "\telle est fermée (calzone)\n";
        mapizza += "Sa taille est : ";
        if (pizzaSize == DP.Builder.PIZZASIZE.LARGE) mapizza += "large\n";
        if (pizzaSize == DP.Builder.PIZZASIZE.SMALL) mapizza += "small\n";
        if (pizzaSize == DP.Builder.PIZZASIZE.MEDIUM) mapizza += "medium\n";
        return mapizza;
    }

    public static class PizzaBuilder {
        private boolean cheesyCrust = false;
        private DP.Builder.PIZZASIZE pizzaSize = DP.Builder.PIZZASIZE.MEDIUM;
        private boolean calzone = false;
        private boolean champignon = false;
        private boolean jambon = false;
        private boolean oignon = false;
        private boolean ananas = false;
        private boolean salami = false;
        private Set<FROMAGES> fromages = new HashSet<>();

//        public PizzaBuilder() {
//            pizzaSize = PIZZASIZE.MEDIUM;
//        }

        public PizzaBuilder pizzaSize(DP.Builder.PIZZASIZE pizzaSize) {
            this.pizzaSize = pizzaSize;
            return this;
        }

        public PizzaBuilder cheesyCrust() {
            this.cheesyCrust = true;
            return this;
        }

        public PizzaBuilder calzone() {
            this.calzone = true;
            return this;
        }

        public PizzaBuilder champignon() {
            this.champignon = true;
            return this;
        }

        public PizzaBuilder jambon() {
            this.jambon = true;
            return this;
        }

        public PizzaBuilder oignon() {
            this.oignon = true;
            return this;
        }

        public PizzaBuilder ananas() {
            this.ananas = true;
            return this;
        }

        public PizzaBuilder salami() {
            this.salami = true;
            return this;
        }

        public PizzaBuilder fromages(FROMAGES fromage) {
            fromages.add(fromage);
            return this;
        }

        public Pizza build() {
            return new Pizza(cheesyCrust, pizzaSize, calzone, champignon, jambon, oignon, ananas, salami, fromages);
        }
    }
}
