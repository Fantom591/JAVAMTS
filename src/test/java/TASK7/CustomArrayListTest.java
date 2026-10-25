package TASK7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class CustomArrayListTest {
    CustomList<Integer> list;

    @BeforeEach
    void setUp(){
        list=new CustomArrayList<>();
    }

    @AfterEach
    void setDown(){
        list=null;
    }

    @Test
    void newListIsEmpty(){
        assertThat(list).isEmpty();
    }

    @Test
    void addSeveralElementsPreservesOrder() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertThat(list.size()).isEqualTo(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void addMoreThanTenElem() {
        for (int i=0;i<11;i++){
            list.add(i);
        }
        for(int i=0;i<11;i++){
            assertThat(list.get(i)).isEqualTo(i);
        }
    }

    @Test
    void isGetCorrect(){
        for (int i=0;i<5;i++){
            list.add(i);
        }
        assertThat(list.get(0)).isEqualTo(0);
        assertThat(list.get(2)).isEqualTo(2);
        assertThat(list.get(4)).isEqualTo(4);
    }

    static Arguments[] dataForTest5(){
        return new Arguments[]{Arguments.of(-1),Arguments.of(2)};
    }

    @ParameterizedTest
    @MethodSource("dataForTest5")
    void uncorrectDataInGetRemove(int index){
        for (int i=0;i<2;i++){
            list.add(i);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(index));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(index));
    }

    static Arguments[] dataForTest6(){
        return new Arguments[]{Arguments.of(0),Arguments.of(2),Arguments.of(4)};
    }

    @ParameterizedTest
    @MethodSource("dataForTest6")
    void deletingFirstMidLast(int index){
        for (int i=0;i<5;i++){
            list.add(i);
        }
        assertThat(list.remove(index)).isEqualTo(index);
        int ind=0;
         for(int i=0;i<5;i++){
             if (i==index) continue;
             assertThat(list.get(ind)).isEqualTo(i);
             ind++;
         }
    }

    @Test
    void addNull(){
        assertThrows(NullPointerException.class,()->list.add(null));
    }

    @Test
    void checkCorrectOrderInIterator(){
        for (int i=0;i<5;i++){
            list.add(i);
        }
        int index=0;
        for(Integer elem:list){
            assertThat(elem).isEqualTo(index);
            index++;
        }
    }

    @Test
    void checkNextThrow(){
        list.add(1);
        list.add(2);

        Iterator<Integer> it = list.iterator();

        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isFalse();

        assertThrows(NoSuchElementException.class, () -> it.next());
    }








}