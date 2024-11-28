package Estruturas1;

public class Pilha<X>{

    private ListaEncadeadaSimplesDesordenada l;
    
    public Pilha(){
        l = new ListaEncadeadaSimplesDesordenada<X>();
    }

    public void guardeUmItem(X x){
        if(x == null){
            throw new IllegalArgumentException("Não é permitido guardar nulo");
        }
        try {
            l.guardeNoInicio(x);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public X recupereUmItem() throws Exception{
        if(l.getPrimeiro() == null){
            throw new Exception("Pilha vazia");
        }

        X primeiro = (X)l.getPrimeiro();
        l.removaPrimeiro();
        return primeiro;
    }

    public void removaUmItem() throws Exception{
        if(l.getPrimeiro() == null){
            throw new Exception("Pilha vazia");
        }
        l.removaPrimeiro();
    }

    public boolean isCheia(){
        return false;
    }

    public boolean isVazia(){
        try {
            if(l.getPrimeiro()!=null){
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public int getTamanho(){
        return l.getTamanho();
    }

    public boolean equals(Pilha another){
        if(another == null){
            return false;
        }
        if(another == this){
            return true;
        }
        return this.l.equals(another.l);
    }

    public String toString(){
        return l.toString();
    }

    public int hashCode(){
        return l.hashCode();
    }

    public Pilha<X> clone(){
        Pilha<X> clone = new Pilha<X>();
        clone.l = (ListaEncadeadaSimplesDesordenada) this.l.clone();
        return clone;
    }

    public Pilha(Pilha modelo) throws Exception{
        if(modelo == null){
            throw new Exception("Modelo ausente");
        }
        this.l = new ListaEncadeadaSimplesDesordenada(modelo.l);
    }

    public void inverta() throws Exception{
        ListaEncadeadaSimplesDesordenada<X> l2 = new ListaEncadeadaSimplesDesordenada<X>();
        int count = 1;
        while(!this.isVazia()){
            X x = this.recupereUmItem();
            l2.guardeNoInicio(x);
            System.out.println(count++);
        }
        this.l = l2;
    }

}