package Listes;

import java.util.Optional;


public class ListePile<T> {
    private T info; // l'information
    private ListePile<T> next; // le pointeur vers l'info suivant

    public ListePile(T info) {
        this.info = info;
    }

    public ListePile() { }

    public void push(T element){
       if (element != null) {
           if (info == null) {
               info = element;
           } else {
               var temp = new ListePile<>(element);
               var dernier = trouveDernier();
               dernier.next = temp;
           }
       }
    }

    public Optional<T> pop(){
        if(next == null){
            var retour = Optional.ofNullable(info);
            info =  null;
            return retour;
        }
        var dernier = trouveDernier();
        var retour = Optional.of(dernier.info);
        var referent = trouveLeReferent(dernier);
        referent.next = null;
        if(referent == this){
            this.info = null;
        }
        return retour;
    }

    private ListePile<T> trouveLeReferent(ListePile<T> references){
        ListePile<T> p = this;
        while(p.next != references){
            p = p.next;
        }
        return p;
    }

    private ListePile<T> trouveDernier(){
        ListePile<T> p = this; // p représente le couple "info-next"
        while(p.next != null){
            p = p.next;
        }
        return p;
    }

    public int size(){
        ListePile<T> p = this; // p représente le couple "info-next"
        var nombre = 0;
        if(info != null){} nombre ++;
        while(p.next != null){
            p = p.next;
            nombre++;
        }

        return nombre;
    }

    public void clear(){
        info = null;
        next = null;
    }

}
