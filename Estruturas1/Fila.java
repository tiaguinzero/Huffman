package Estruturas1;


public class Fila <X>{

    private ListaEncadeadaSimplesDesordenada l;

    public Fila(){
        l = new ListaEncadeadaSimplesDesordenada<X>();
    }

    public void guardeUmItem(X x) throws Exception{
        if(x == null){
            throw new Exception("Não é permitido guardar nulo");
        }
        l.guardeNoFinal(x);
    }

    public X recupereUmItem() throws Exception{
        try{
            X primeiro = (X)l.getPrimeiro();
            return primeiro;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public X desenfilere(){
        try{
            X primeiro = (X)l.getPrimeiro();
            l.removaPrimeiro();
            return primeiro;
        }
        catch(Exception e){
            return null;
        }
    }

    public void removaUmItem() throws Exception{
        try{
            l.removaPrimeiro();
        }
        catch(Exception e){
            throw new Exception("Fila vazia");
        }
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

    
    public boolean equals(Fila another){
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

    public Fila(Fila modelo) throws Exception{
        if(modelo == null){
            throw new Exception("Modelo ausente");
        }
        this.l = new ListaEncadeadaSimplesDesordenada(modelo.l);
    }

    public Object clone(){
        Fila ret = null;
        try{
            ret = new Fila(this);
        }
        catch(Exception e){}
        return ret;
    }

    public int getTamanho(){
        return l.getTamanho();
    }

    public void inverta() throws Exception {
        Pilha<X> pilha = new Pilha<>();
        while (!this.isVazia()) {
            pilha.guardeUmItem(this.desenfilere());
        }
        while (!pilha.isVazia()) {
            this.guardeUmItem(pilha.recupereUmItem());
        }
    }    


}
