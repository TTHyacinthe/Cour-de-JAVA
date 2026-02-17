package Design_Patterns.Factory;

import DP.Builder.FROMAGES;
import DP.Builder.PIZZASIZE;
import DP.Builder.Pizza;

public final class PizzaFactory {
    private PizzaFactory() {}

    public static Pizza PizzaMaker(DP.Factory.PIZZATYPE pizzatype) {
        return switch (pizzatype) {
            case HAWAII -> new Pizza.PizzaBuilder().jambon().ananas().oignon().build();
            case QFROMAGGI -> new Pizza.PizzaBuilder().fromages(FROMAGES.PARMESAN).fromages(FROMAGES.COMTE).fromages(FROMAGES.GORGONZOLA).fromages(FROMAGES.EMMENTAL).pizzaSize(PIZZASIZE.LARGE).build();
            case PROSCUITTO -> new Pizza.PizzaBuilder().jambon().build();
        };
    }
}
