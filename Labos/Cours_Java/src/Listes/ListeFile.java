package Listes;

import java.util.Optional;

public class ListeFile<T> {
    private T info = null;
    private ListeFile<T> next = null;

    public ListeFile(T info) {
        this.info = info;
    }

    public ListeFile() { }

    public void push(T element){
        if (element != null) {
            if (info == null) {
                info = element;
            } else {
                var temp = new ListeFile<>(element);
                var dernier = trouveDernier();
                dernier.next = temp;
            }
        }
    }

    public Optional<T> pop(){
        Optional<T> retour;
        if(next == null){
            retour = Optional.ofNullable(info);
            info =  null;
        }else {
            retour = Optional.ofNullable(info);
            info = next.info;
            next = next.next;
        }

        return retour;
    }

    private ListeFile<T> trouveLeReferent(ListeFile<T> references){
        ListeFile<T> p = this;
        while(p.next != references){
            p = p.next;
        }
        return p;
    }

    private ListeFile<T> trouveDernier(){
        ListeFile<T> p = this; // p représente le couple "info-next"
        while(p.next != null){
            p = p.next;
        }
        return p;
    }

    public int size(){
        ListeFile<T> p = this; // p représente le couple "info-next"
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
