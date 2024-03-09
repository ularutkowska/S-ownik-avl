import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Drzewo ang = new Drzewo();
        Drzewo pl = new Drzewo();

        Slowo s1;
        Slowo s2;
        Slowo s3;
        Slowo s4;
        int w;
        String slowo1;
        String slowo2;
        Scanner sc = new Scanner(System.in);
        boolean s = false;
        while (!s) {
            System.out.println("1. Wczytaj z pliku");
            System.out.println("2. Zapisz");
            System.out.println("3. Wstaw słowo polskie");
            System.out.println("4. Wstaw słowo angielskie");
            System.out.println("5. Wyszukaj słowo polskie");
            System.out.println("6. Wyszukaj słowo angielskie");
            System.out.println("7. Usuń słowo polskie");
            System.out.println("8. Usuń słowo angielskie");
            System.out.println("9. Wyswietl");
            System.out.println("10. Wyjdz");
            System.out.println("Wybierz opcje ");

            w = sc.nextInt();
            switch (w) {
                case 1:
                    ArrayList<String> slowaangielskie = new ArrayList<>();
                    ArrayList<String> slowapolskie = new ArrayList<>();
                    int j = 0;
                    try (BufferedReader br = new BufferedReader(new FileReader("InTest20501.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] words = line.split(" ");
                            j++;
                            if (words.length == 2) {
                                slowaangielskie.add(words[0]);
                                slowapolskie.add(words[1]);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    for (int i = 0; i < j; i++) {
                        s1 = new Slowo(slowaangielskie.get(i));
                        s2 = new Slowo(slowapolskie.get(i));
                        s1.tlumaczenie = s2;
                        s2.tlumaczenie = s1;
                        ang.insert1(s1);
                        pl.insert1(s2);

                    }
                case 2:
                    zapisz1(ang);
                    break;
                case 3:
                    System.out.println("Podaj słowo polskie: ");
                    sc.nextLine();
                    slowo1 = sc.nextLine();
                    if(pl.search1(slowo1) != null)
                    {
                        System.out.println("Takie słowo już jest w słowniku.");
                    }else{
                        System.out.println("Podaj tłumaczenie: ");
                        slowo2 = sc.nextLine();

                        s1 = new Slowo(slowo2);
                        s2 = new Slowo(slowo1);
                        s1.tlumaczenie = s2;
                        s2.tlumaczenie = s1;
                        ang.insert1(s1);
                        pl.insert1(s2);}
                    break;
                case 4:
                    System.out.println("Podaj słowo angielskie: ");
                    sc.nextLine();
                    slowo1 = sc.nextLine();
                    if(ang.search1(slowo1) != null)
                    {
                        System.out.println("Takie słowo już jest w słowniku.");
                    }else{
                        System.out.println("Podaj tłumaczenie: ");
                        slowo2 = sc.nextLine();

                        s3 = new Slowo(slowo1);
                        s4 = new Slowo(slowo2);
                        s3.tlumaczenie = s4;
                        s4.tlumaczenie = s3;
                        ang.insert1(s3);
                        pl.insert1(s4);}
                    break;
                case 5:
                    System.out.println("Podaj słowo angielskie");
                    sc.nextLine();
                    slowo1 = sc.nextLine();
                    if(ang.search1(slowo1)==null)
                    {
                        System.out.println("Nie ma takiego słowa w słowniku.");
                    }else{
                        s1 = ang.search1(slowo1);
                        System.out.println(s1.tlumaczenie.klucz);}
                    break;
                case 6:
                    System.out.println("Podaj słowo polskie");
                    sc.nextLine();
                    slowo1 = sc.nextLine();
                    if(pl.search1(slowo1)==null)
                    {
                        System.out.println("Nie ma takiego słowa w słowniku.");
                    }else{
                        s1 = pl.search1(slowo1);
                        System.out.println(s1.tlumaczenie.klucz);}
                    break;
                case 7:
                    System.out.println("Podaj słowo polskie");
                    sc.nextLine();
                    slowo1 = sc.nextLine();
                    if(pl.search1(slowo1)==null)
                    {
                        System.out.println("Nie ma takiego słowa w słowniku.");
                    }else{
                        ang.delete1(pl.search1(slowo1).tlumaczenie.klucz);
                        pl.delete1(slowo1);}
                    break;
                case 8:
                    System.out.println("Podaj słowo angielskie");
                    sc.nextLine();
                    slowo1 = sc.nextLine();
                    if(ang.search1(slowo1)==null)
                    {
                        System.out.println("Nie ma takiego słowa w słowniku.");
                    }else{
                        pl.delete1(ang.search1(slowo1).tlumaczenie.klucz);
                        ang.delete1(slowo1);}
                    break;
                case 9:
                    ang.KLP1();
                    System.out.println();
                    pl.KLP1();
                    break;
                case 10:
                    s = true;
                    break;
                default:
                    System.out.println("Błąd");

            }


        }
    }
    public static void zapisz(Slowo c, FileWriter fw) throws IOException
    {
        if (c != null) {
            fw.write(c.klucz +" " + c.tlumaczenie.klucz + "\n");
            System.out.println();
            zapisz(c.lewy,fw);
            zapisz(c.prawy,fw);
        }


    }
    public static void zapisz1(Drzewo a) throws IOException
    {
        FileWriter fw = new FileWriter("Out20501.txt");
        zapisz(a.korzen,fw);
        fw.close();

    }
}