package Estruturas;

public class FilaPrioridade<X extends Comparable<X>> {
    private ListaEncadeadaSimplesDesordenada<X> lista;

    public FilaPrioridade() {
        this.lista = new ListaEncadeadaSimplesDesordenada<X>();
    }

    public void guarde(X info) {
        if (info == null) return;
        
        try {
            // se ta vazia colocar no inicio.
            if (this.lista.getTamanho() == 0) {
                this.lista.guardeNoInicio(info);
                return;
            }
    
            // se for menor que o atual menor, colocar no inicio.
            if (info.compareTo(this.lista.getPrimeiro()) < 0) {
                this.lista.guardeNoInicio(info);
                return;
            }
            
            // se for maior que o atual maior, colocar no final.
            if (info.compareTo(this.lista.getUltimo()) > 0) {
                this.lista.guardeNoFinal(info);
                return;
            }
    
            // se não for nem o menor nem o maior de todos, procurar no meio a posição
            for (int i = 0; i < this.lista.getTamanho(); i++) {
                if (info.compareTo(this.lista.get(i)) <= 0) { // <= is the key change
                    this.lista.guardeEm(i, info); // coloca na posição i
                    return;
                }
            }
            this.lista.guardeNoFinal(info);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public X desenfilere(){
        try {
            X ret = this.lista.getPrimeiro();
            this.lista.removaPrimeiro();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTamanho(){
        return this.lista.getTamanho();
    }

    @Override
    public String toString() {
        return this.lista.toString();
    }

    
}