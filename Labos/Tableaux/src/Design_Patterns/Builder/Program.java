package Design_Patterns.Builder;

import DP.Factory.PIZZATYPE;
import DP.Factory.PizzaFactory;

public class Program {
    static void main() {
        Pizza pizza = new Pizza.PizzaBuilder()
                .calzone()
                .salami()
                .fromages(FROMAGES.COMTE)
                .fromages(FROMAGES.EMMENTAL)
                .cheesyCrust()
                .build();

        Pizza quatreFromages = new Pizza.PizzaBuilder()
                .fromages(FROMAGES.EMMENTAL)
                .fromages(FROMAGES.COMTE)
                .fromages(FROMAGES.GORGONZOLA)
                .fromages(FROMAGES.PARMESAN)
                .pizzaSize(PIZZASIZE.LARGE)
                .build();

        Pizza hawaii = new Pizza.PizzaBuilder()
                .jambon()
                .ananas()
                .oignon()
                .build();

        System.out.println("pizza: " + pizza);
        System.out.println("quatreFromages: " + quatreFromages);

        Pizza proscuitto = PizzaFactory.PizzaMaker(PIZZATYPE.PROSCUITTO);
        System.out.println("proscuitto: " + proscuitto);

    }
}
