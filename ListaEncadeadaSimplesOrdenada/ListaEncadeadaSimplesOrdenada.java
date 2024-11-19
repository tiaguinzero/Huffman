package ListaEncadeadaSimplesOrdenada;

public class ListaEncadeadaSimplesOrdenada<X extends Comparable<X>> implements Cloneable {
    private class No implements Cloneable {
        private X info;
        private No prox;

        public No(X i, No p) {
            this.info = i;
            this.prox = p;
        }

        public No(X i) {
            this.info = i;
            this.prox = null;
        }

        public X getInfo() {
            return this.info;
        }

        public No getProx() {
            return this.prox;
        }

        public void setInfo(X i) {
            this.info = i;
        }

        public void setProx(No p) {
            this.prox = p;
        }

        @Override
        public String toString() {
            return this.info.toString();
        }

        
        @Override
        public boolean equals (Object obj)
        {
            if (obj==this) return true;
            if (obj==null) return false;
            if (obj.getClass()!=this.getClass()) return false;
            
            if (!((No)obj).info.equals(this.info)) return false;
            
            return true;
        }
        
        @Override
        public int hashCode ()
        {
            int ret=1;
            
            ret=2*ret+this.info.hashCode();
        
            if (ret<0) ret=-ret;
            
            return ret;
        }
        
        public No(No model) throws Exception {
            if (model == null)
                throw new Exception("Modelo Ausente");
            if (model.info instanceof Cloneable) {
                this.info = new Clonador<X>().clone(model.info);
            } else
                this.info = model.info;
        }
        
        public Object clone ()
        {
            No ret=null;
            
            try
            {
                ret = new No (this);
            }
            catch (Exception erro)
            {} 

            return ret;
        }

    }

    private No primeiro = null;

    public ListaEncadeadaSimplesOrdenada() {

    }

    public int getTamanho() {
        int size = 0;
        No atual = primeiro;
        while (atual != null) {
            size++;
            atual = atual.getProx();
        }
        return size;
    }
    
    public X getPrimeiro() throws Exception {
        if (primeiro == null) {
            throw new Exception("Lista vazia");
        }
        return primeiro.getInfo();
    }
    

    public void guarde(X i) throws Exception {
        if (i == null) throw new Exception("Informação ausente");

        No novoNo = new No(i);
        
        if (primeiro == null || i.compareTo(primeiro.getInfo()) <= 0) {
            novoNo.setProx(primeiro);
            primeiro = novoNo;
            return;
        }
        
        No atual = primeiro;
        while (atual.getProx() != null && i.compareTo(atual.getProx().getInfo()) > 0) {
            atual = atual.getProx();
        }
        novoNo.setProx(atual.getProx());
        atual.setProx(novoNo);
    }

    public void remova(X i) throws Exception {
        if (i == null) throw new Exception("Informação ausente");

        if (primeiro == null) return;

        if (primeiro.getInfo().equals(i)) {
            primeiro = primeiro.getProx();
            return;
        }

        No atual = primeiro;
        while (atual.getProx() != null && !atual.getProx().getInfo().equals(i)) {
            atual = atual.getProx();
        }

        if (atual.getProx() != null) {
            atual.setProx(atual.getProx().getProx());
        }
    }
}
