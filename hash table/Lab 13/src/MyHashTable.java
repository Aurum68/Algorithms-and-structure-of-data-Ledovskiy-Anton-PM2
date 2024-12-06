import java.util.Scanner;

public class MyHashTable {
    private class MyHashMap {
        private long key;
        private String value;
        
        MyHashMap(long key, String value){
            this.key = key;
            this.value = value;
        }
    
        String getValue(long key){
            if (key == this.key) {
                return this.value;
            }
            else{
                return null;
            }
        }
    
        long getKey(){
            return this.key;
        }
    }
    
    String[] hash_table = new String[(int) (Math.pow(2, 16))];
    Scanner scanner;
    private final String english = "abcdefghijklmnopqrstuvwxyz";
    private final String russian = "абвгдежзийклмнопрстуфхцчшщъыьэюя";

    public MyHashTable(Scanner scanner){
        this.scanner = scanner;
    }

    private MyHashMap hashFunction(String str){
        String buf = str.toLowerCase();
        long key = 0;
        for (int index = 0; index < buf.length(); index++) {
            char symbol = buf.charAt(index);
            long hash = 0;
            if (english.indexOf(symbol) != -1) {
                hash = (long) ((Character.getNumericValue(symbol) - Character.getNumericValue('a'))*Math.pow(58, index)); //English a
            }else if (russian.indexOf(symbol) != -1) {
                hash = (long) ((Character.getNumericValue(symbol) - Character.getNumericValue('а'))*Math.pow(58, index)); //Russian a
            }
            key += hash;
        }
        key = key % ((long) (Math.pow(2, 30)));
        return new MyHashMap(key, str);
    }

    public String[] createHashTable(){
        while (this.scanner.hasNext()) {
            String str = scanner.next();
            if (this.hash_table[this.hash_table.length - 1] != null){
                String[] buffer = this.hash_table.clone();
                this.hash_table = new String[buffer.length * 2];
                for (int index = 0; index < buffer.length; index++) {
                    this.hash_table[index] = buffer[index];
                }
            }
            MyHashMap hash = hashFunction(str);
            try{
                if (this.hash_table[(int) hash.getKey()] == null) {
                    this.hash_table[(int) hash.getKey()] = hash.getValue(hash.getKey());
                }
                else{
                    boolean flag = false;
                    for (int index = (int) hash.getKey()+1; index < this.hash_table.length; index++) {
                        if (this.hash_table[index] == null) {
                            this.hash_table[index] = hash.getValue(hash.getKey());
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        String[] buffer = this.hash_table.clone();
                        this.hash_table = new String[buffer.length * 2];
                        for (int index = 0; index < buffer.length; index++) {
                            this.hash_table[index] = buffer[index];
                        }
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e){
                String[] buffer = this.hash_table.clone();
                this.hash_table = new String[buffer.length * 2];
                for (int index = 0; index < buffer.length; index++) {
                    this.hash_table[index] = buffer[index];
                }
            }
            
        }
        return this.hash_table;
    }



}
