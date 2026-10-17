package TASK6;

public class Pair<K,V> {
    K key;
    V value;

    Pair(K key,V value){
        this.key=key;
        this.value=value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
    public void setKey(K key){
        this.key=key;
    }
    public void setValue(V value){
        this.value=value;
    }
    public Pair<V,K> swap(){
        return new Pair<V,K>(value,key);
    }
    @Override
    public String toString(){
        String key;
        String value;
        if(this.key==null){
            key=null;
        }else{
            key=this.key.toString();
        }
        if(this.value==null){
            value=null;
        }else{
            value=this.value.toString();
        }

        String ans="Pair{key="+key+", value=" +value+"}";
        return ans;
    }

    public static void main(String[] args) {
        final Pair<String, Integer> pair = new Pair<>("Age", 25);
        System.out.println(pair); // Ожидаем: Pair{key=Age, value=25}
        final Pair<Integer, String> swapped = pair.swap();
        System.out.println(swapped); // Ожидаем: Pair{key=25, value=Age}
        final Pair<Object, Object> nullPair = new Pair<>(null, null);
        System.out.println(nullPair); // Ожидаем: Pair{key=null, value=null}
        final Pair<Object, Object> nullSwapped = nullPair.swap();
        System.out.println(nullSwapped); // Ожидаем: Pair{key=null, value=null}
    }

}
