package encryptdecrypt;

public class Decrypt {
    public static String decrypt(String text, int key, String algorithm){
        String message="";
        if ("unicode".equals(algorithm)) {
            for (char el: text.toCharArray()){
                int ind = (int)el;
                if (ind >31 && ind<127) {
                    message +=ind-key>31?(char)(ind-key):(char)(ind-key+95);
                } else message +=el;
            }
        } else if ("shift".equals(algorithm)) { //A-65 Z-90 a-97 z-122
            for (char el : text.toCharArray()) {
                int ind = (int) el;
                if (ind > 64 && ind < 91) {
                    message += ind - key > 64 ? (char) (ind - key) : (char) (ind - key + 26);
                } else if (ind > 96 && ind < 123) {
                    message += ind - key > 96 ? (char) (ind - key) : (char) (ind - key + 26);
                }
                else message += el;
            }
        }
        return message;
    }
}