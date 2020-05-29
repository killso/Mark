import java.util.Scanner;
public class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String txt = input.nextLine();
        System.out.print("Введите подстроку для поиска: ");
        String str = input.nextLine();
        System.out.print("Опция чувствительности к регистру? (0 - нет, 1 - да): ");
        int lettercase = input.nextInt();
        //Опция чувствительности к регистру
        if (lettercase == 0) {
            txt = txt.toUpperCase();
            str = str.toUpperCase();
        }
        long time = System.currentTimeMillis();
        //Префикс функция
        int massiv[] = new int[str.length()];
        Morris findStr = new Morris(txt, str);
        findStr.prefix(massiv);
        // Поиск
        int res = findStr.Morris1(massiv);
        long time1 = System.currentTimeMillis() - time;
        // Вывод результата
        if (res == -1) {
            System.out.println("Данная подстрока не содержится в тексте");
            return;
        } else {
            // Выводим на экран
            System.out.println("Найденная подстрока:");
            findStr.vivod(res);
        }
        time = System.currentTimeMillis();
        int indexJava = txt.indexOf(str);
        long time2 = System.currentTimeMillis() - time;

        System.out.println("\nРезультаты поиска:");
        System.out.println("indexJava: " + indexJava + ", timeJava: " + time2);
        System.out.println("indexMy: " + res + ", timeMy: " + time1);

        input.close();
    }
}






