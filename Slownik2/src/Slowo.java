public class Slowo {

    String klucz;
    Slowo lewy;
    Slowo prawy;
    int wywazenie;
    Slowo tlumaczenie;

    public Slowo(String klucz)
    {
        this.klucz = klucz;
        lewy = null;
        prawy = null;
        wywazenie = 0;
    }


}
