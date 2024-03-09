import java.io.FileWriter;
import java.io.IOException;

public class Drzewo {

    Slowo korzen;

    boolean z1;
    boolean z2;

    public Slowo search(Slowo a, String klucz) {
        if (a == null)
            return null;

        if (a.klucz.compareTo(klucz) == 0)
            return a;

        if (a.klucz.compareTo(klucz) < 0)
            return search(a.prawy, klucz);
        else return search(a.lewy, klucz);

    }

    public Slowo search1(String klucz)
    {
        return search(korzen, klucz);
    }





    public Slowo insert(Slowo a, Slowo b)
    {
        if(a == null)
        {
            z1 = false;
            return b;
        }
        if(b.klucz.compareTo(a.klucz) < 0)
        {
            a.lewy = insert(a.lewy, b);
            if(!z1)
                return rownowagainpr(a);
        }
        if(b.klucz.compareTo(a.klucz) > 0)
        {
            a.prawy = insert(a.prawy, b);
            if(!z1)
                return rownowagainle(a);
        }

        z1 = true;
        return a;
    }
    public void insert1(Slowo b)
    {
        if(search1(b.klucz)!=null)
            return;
        korzen = insert(korzen, b);
    }


    public Slowo rownowagainpr(Slowo a)
    {
        if(a.wywazenie == 1)
        {
            if(a.lewy.wywazenie == 1)
            {
                a = rotacjaprawo(a);
                a.prawy.wywazenie = 0;
            }else {
                a.lewy = rotacjalewo(a.lewy);
                a = rotacjaprawo(a);
                if (a.wywazenie == 0) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == 1) {
                    a.prawy.wywazenie = -1;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == -1) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 1;
                }

            }
            a.wywazenie = 0;
            z1 = true;
        }else if(a.wywazenie == 0)
        {
            a.wywazenie = 1;
            z1 = false;
        }else if(a.wywazenie == -1)
        {
            a.wywazenie = 0;
            z1 = true;
        }
        return a;
    }

    public Slowo rownowagainle(Slowo a)
    {
        if(a.wywazenie == -1)
        {
            if(a.prawy.wywazenie == -1)
            {
                a = rotacjalewo(a);
                a.lewy.wywazenie = 0;
            }else {
                a.prawy = rotacjaprawo(a.prawy);
                a = rotacjalewo(a);
                if (a.wywazenie == 0) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == 1) {
                    a.prawy.wywazenie = -1;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == -1) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 1;
                }

            }
            a.wywazenie = 0;
            z1 = true;
        }else if(a.wywazenie == 0)
        {
            a.wywazenie = -1;
            z1 = false;
        }else if(a.wywazenie == 1)
        {
            a.wywazenie = 0;
            z1 = true;
        }
        return a;
    }

    public static Slowo rotacjalewo(Slowo a)
    {
        Slowo b = a.prawy;
        a.prawy = b.lewy;
        b.lewy = a;
        a = b;
        return a;

    }
    public static Slowo rotacjaprawo(Slowo a)
    {
        Slowo b = a.lewy;
        a.lewy = b.prawy;
        b.prawy = a;
        a = b;
        return a;
    }

    public void KLP(Slowo a)
    {
        if(a != null)
        {
            System.out.println(a.klucz + "(" + a.wywazenie + ") ");
            KLP(a.lewy);
            KLP(a.prawy);

        }
    }
    public void KLP1()
    {
        KLP(korzen);
    }

    public void delete1(String klucz)
    {
        if(search1(klucz) == null)
        {
            System.out.println("Nie ma takiego klucza.");
            return;
        }
        korzen = delete(klucz, korzen);
    }
    public Slowo delete(String klucz, Slowo a)
    {
        if(klucz.compareTo(a.klucz) > 0)
        {
            a.prawy = delete(klucz, a.prawy);
            if(!z2)
                return rownowagadpr(a);
        }else if(klucz.compareTo(a.klucz) < 0)
        {
            a.lewy = delete(klucz, a.lewy);
            if(!z2)
                return rownowagadle(a);
        }else if (a.prawy == null || a.lewy == null) {
            {
                z2 = false;
                if (a.prawy == null)
                    a = a.lewy;
                else a = a.prawy;
            }
        }else {
            Slowo b = m(a.lewy);
            a.klucz = b.klucz;
            a.lewy = delete(b.klucz, a.lewy);
            if(!z2)
                return rownowagadle(a);
        }

        return a;
    }

    public Slowo m(Slowo a)
    {
        Slowo b = a;
        while(b.prawy != null)
            b = b.prawy;
        return b;
    }
    public Slowo rownowagadpr(Slowo a)
    {
        if(a.wywazenie == 1)
        {
            if(a.lewy.wywazenie >= 0)
            {
                a = rotacjaprawo(a);
                if(a.prawy.wywazenie == 0)
                {
                    a.wywazenie = 1;
                    a.prawy.wywazenie = -1;
                    z2 = true;
                }else {
                    a.wywazenie = 0;
                    a.prawy.wywazenie = 0;

                }
            }else
            {
                a.lewy = rotacjalewo(a.lewy);
                a = rotacjaprawo(a);

                if(a.wywazenie == 0)
                {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;
                }
                else if(a.wywazenie == 1)
                {
                    a.prawy.wywazenie = -1;
                    a.lewy.wywazenie = 0;
                }else {
                    a.lewy.wywazenie = -1;
                    a.prawy.wywazenie = 0;
                }
                a.wywazenie = 0;
            }
            return a;
        }
        if(a.wywazenie == 0)
        {
            a.wywazenie = 1;
            z2 = true;
            return a;
        }
        if(a.wywazenie == -1)
        {
            a.wywazenie = 0;
            return a;
        }

        return a;

    }
    public Slowo rownowagadle(Slowo a)
    {
        if(a.wywazenie == -1)
        {
            if(a.prawy.wywazenie <= 0)
            {
                a = rotacjalewo(a);
                if(a.wywazenie == -1)
                {
                    a.wywazenie = 0;
                    a.lewy.wywazenie = 0;
                    z2 = true;
                }else {
                    a.wywazenie = 1;
                    a.lewy.wywazenie = -1;

                }
            }else
            {
                a.prawy = rotacjaprawo(a.prawy);
                a = rotacjalewo(a);

                if(a.wywazenie == 0)
                {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;
                }
                else if(a.wywazenie == -1)
                {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 1;
                }else {
                    a.lewy.wywazenie = 0;
                    a.prawy.wywazenie = -1;
                }
                a.wywazenie = 0;
            }
            return a;
        }
        if(a.wywazenie == 0)
        {
            a.wywazenie = -1;
            z2 = true;
            return a;
        }
        if(a.wywazenie == 1)
        {
            a.wywazenie = 0;
            return a;
        }

        return a;
    }
}
