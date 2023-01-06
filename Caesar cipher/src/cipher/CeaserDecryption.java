package cipher;

public class CeaserDecryption {
    private String text;
    private String language;
    private int key;
    private String rusAlp;
    private String rusAlpCaps;
    private String engAlp;
    private String engAlpCaps;
    private String ciphertext;

    public CeaserDecryption(String text, String language, int key){
        this.text = text;
        this.language = language;
        this.key = key;
    }

    public String dechiper(){
        switch (this.language) {
            case ("rus"):
                rusAlp = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                rusAlpCaps = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
                RusDechiper();
                break;
            case ("eng"):
                engAlp = "abcdefghijklmnopqrstuvwxyz";
                engAlpCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                EngDechiper();
                break;
            default:
                System.out.println("error");
                break;
        }
        return ciphertext;
    }

    public void RusDechiper(){
        int number;
        String res1;
        for(int i = 0; i < text.length(); i++)
        {
            String res = String.valueOf(text.charAt(i));
            if(rusAlp.contains(res)){
                number = (rusAlp.indexOf(res));
                int t = ((number - key) % 33);
                if(t < 0){t = t + 33;}
                res1 = String.valueOf(rusAlp.charAt(t));
                if(i != 0)
                    ciphertext += res1;
                else
                    ciphertext = res1;
            }
            else if(rusAlpCaps.contains(res)){
                number = (rusAlpCaps.indexOf(res));
                int t = ((number - key) % 33);
                if(t < 0){t = t + 33;}
                res1 = String.valueOf(rusAlpCaps.charAt(t));
                if(i != 0)
                    ciphertext += res1;
                else
                    ciphertext = res1;
            }
            else {
                if(i != 0)
                    ciphertext += res;
                else
                    ciphertext = res;
            }
        }
    }

    public void EngDechiper(){
        int number;
        String res1;
        for(int i = 0; i < text.length(); i++)
        {
            String res = String.valueOf(text.charAt(i));
            if(engAlp.contains(res)){
                number = (engAlp.indexOf(res));
                int t = ((number - key) % 26);
                if(t < 0){t = t + 26;}
                res1 = String.valueOf(engAlp.charAt(t));
                if(i != 0)
                    ciphertext += res1;
                else
                    ciphertext = res1;
            }
            else if(engAlpCaps.contains(res)){
                number = (engAlpCaps.indexOf(res));
                int t = ((number - key) % 26);
                if(t < 0){t = t + 26;}
                res1 = String.valueOf(engAlpCaps.charAt(t));
                if(i != 0)
                    ciphertext += res1;
                else
                    ciphertext = res1;
            }
            else {
                if(i != 0)
                    ciphertext += res;
                else
                    ciphertext = res;
            }
        }
    }
}
