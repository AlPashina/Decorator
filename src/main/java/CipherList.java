import java.security.GeneralSecurityException;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class CipherList implements List {
    private List CipherCopyList;
    private String Key;

    CipherList(List listA, String key) {
        CipherCopyList = new ArrayList();
        this.Key = key;
        for (int i = 0; i < listA.size(); i++) {
            byte[] output = (listA.get(i)).toString().getBytes(StandardCharsets.UTF_8);
            CipherCopyList.add(Base64.getEncoder().encodeToString(output));
        }
    }

    private void setKey(String key){
        this.Key=key;
    }

    private String getKey(){
        return Key;
    }

    @Override
    public int size() {
        return CipherCopyList.size();
    }

    @Override
    public boolean isEmpty() {
        return CipherCopyList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
            String enc=Base64.getEncoder().encodeToString(o.toString().getBytes(StandardCharsets.UTF_8));
            return CipherCopyList.contains(enc);
    }

    @Override
    public Iterator iterator() {
        return new CipherListIterator(CipherCopyList.listIterator());
    }

    @Override
    public Object[] toArray() {
        return CipherCopyList.stream().map((d) -> {
            List newList=new ArrayList();
            for (int i = 0; i < CipherCopyList.size(); i++) {
                byte[] decode = Base64.getDecoder().decode(CipherCopyList.get(i).toString());
                String s = new String(decode, StandardCharsets.UTF_8);
                newList.add(s);
            }
           return newList;
        }).toArray();
    }

    @Override
    public boolean add(Object o) {
        byte[] output = o.toString().getBytes(StandardCharsets.UTF_8);
        return  CipherCopyList.add(Base64.getEncoder().encodeToString(output));
    }

    @Override
    public boolean remove(Object o) {
        byte[] output = o.toString().getBytes(StandardCharsets.UTF_8);
        return  CipherCopyList.remove(Base64.getEncoder().encodeToString(output));
    }

    @Override
    public boolean addAll(Collection c) {
        Collection enc = (Collection) c.stream().map((d) -> {
            byte[] output = d.toString().getBytes(StandardCharsets.UTF_8);
            d=(Base64.getEncoder().encodeToString(output));
                return d;
        }).collect(Collectors.toCollection(ArrayList::new));

        return CipherCopyList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return CipherCopyList.addAll(index,c);
    }

    @Override
    public void clear() {
        CipherCopyList.clear();
    }

    @Override
    public Object get(int index) {
            Scanner in = new Scanner(System.in);
            System.out.println("\n Введите пароль для получения элемента");
            if (in.nextLine().equals(this.getKey())){

                byte[] decode = Base64.getDecoder().decode(CipherCopyList.get( index).toString());
                String s = new String(decode, StandardCharsets.UTF_8);
                return s;
            }
            else
                return (CipherCopyList.get(index));
    }

    @Override
    public Object set(int index, Object element) {
        byte[] output = (element).toString().getBytes(StandardCharsets.UTF_8);
        String el= Base64.getEncoder().encodeToString(output);
            return CipherCopyList.set(index, el);
    }

    @Override
    public void add(int index, Object element) {
        byte[] output = (element).toString().getBytes(StandardCharsets.UTF_8);
        String el= Base64.getEncoder().encodeToString(output);
        CipherCopyList.add(index, el);

    }

    @Override
    public Object remove(int index) {
        return CipherCopyList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
            return CipherCopyList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
            return CipherCopyList.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return new CipherListIterator(CipherCopyList.listIterator());
    }

    @Override
    public ListIterator listIterator(int index) {
        return new CipherListIterator(CipherCopyList.listIterator(index));
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return CipherCopyList.subList(fromIndex, toIndex).stream().map(s -> {
            byte[] output = (s).toString().getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(output);
        }).toList();
    }

    @Override
    public boolean retainAll(Collection c) {
        Collection enc = (Collection) c.stream().map((d) -> {
            byte[] output = (d).toString().getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(output);
        }).collect(Collectors.toCollection(ArrayList::new));

        return CipherCopyList.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        Collection enc = (Collection) c.stream().map((d) -> {
            byte[] output = (d).toString().getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(output);
        }).collect(Collectors.toCollection(ArrayList::new));

        return CipherCopyList.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
       Collection encCollection = (Collection) c.stream().map((a) -> {
           byte[] output = (a).toString().getBytes(StandardCharsets.UTF_8);
           return Base64.getEncoder().encodeToString(output);
       }).collect(Collectors.toCollection(ArrayList::new));

        return CipherCopyList.containsAll(encCollection);
    }

    @Override
    public Object[] toArray(Object[] a) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВведите пароль для получения расшифрованного массива");
        String userInput=in.nextLine();
    return CipherCopyList.stream().map((d) -> {
        if (userInput.equals(this.getKey())) {
            byte[] decode = Base64.getDecoder().decode(d.toString());
            return new String(decode, StandardCharsets.UTF_8);
        }
        else
            return d;
        }).toArray();
    }

    public void print(){
        Scanner in = new Scanner(System.in);
        System.out.println("\nВведите пароль для расшифровки списка");
        String key=in.nextLine();
            for (int i=0;i<CipherCopyList.size(); i++){
                if (key.equals(this.getKey())){
                    byte[] decode = Base64.getDecoder().decode(CipherCopyList.get(i).toString());
                    String s = new String(decode, StandardCharsets.UTF_8);
                    System.out.println(s);
                }
                else
                    System.out.println(CipherCopyList.get(i).toString());
            }
    }



//-------------

    private class CipherListIterator  implements ListIterator {

        private final ListIterator iterator;

        public CipherListIterator(ListIterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Object next() {
                return iterator.next();
        }

        @Override
        public boolean hasPrevious() {
            return iterator.hasPrevious();
        }

        @Override
        public Object previous() {
                return (Object) iterator.previous();
        }

        @Override
        public int nextIndex() {
            return iterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return iterator.previousIndex();
        }

        @Override
        public void remove() {
            iterator.remove();
        }

        @Override
        public void set(Object o) {
                iterator.set(o);
        }

        @Override
        public void add(Object o) {
                iterator.add(o);

        }
    }
}
