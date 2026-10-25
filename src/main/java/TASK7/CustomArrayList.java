package TASK7;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayList<E> implements CustomList<E> {
    private int size = 0;
    private E[] arr = (E[]) new Object[10];

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        size++;
        if (arr.length > size) {
            arr[size-1]=element;
        }else{
            arr=Arrays.copyOf(arr,(int) (arr.length*1.5));
            arr[size-1]=element;
        }
    }
    @Override
    public E get(int index){
        if (!(0<=index && index<size)){
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    @Override
    public E remove(int index){
        if (!(0<=index && index<size)){
            throw new IndexOutOfBoundsException();
        }
        E removed=arr[index];
        size--;
        System.arraycopy(arr,index+1,arr,index,size - index);
        arr[size]=null;
        return removed;
    }

    @Override
    public boolean isEmpty(){
        return (size == 0);
    }

    @Override
    public Iterator<E> iterator(){
        return new ArrIterator();
    }

    class ArrIterator implements Iterator<E>{
        private int index = 0;

        @Override
        public boolean hasNext(){
            return index < size;
        }

        @Override
        public E next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return arr[index++];
        }
    }

}
