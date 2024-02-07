import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
public class Txtbase  {
    public static double CalcCur(double Cur1, double Cur2) { //Расчёт отношения
        double result = Cur1/Cur2;
        return result;
    }

    public static double Parse(List<String> list, String code) { //Поиск валюты
        double Cur = 0;
        for (String str : list) {
            if (str.startsWith(code)) {
                String toDouble = str.substring(4);
                Cur = Double.parseDouble(toDouble);
            }
        }
        if (Cur == 0) {
            return 0;
        }
        return Cur;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите код конвертируемой валюты (EUR, USD и т.д.):");
        String code1 = scan.next();

            try {
                Path Base = Path.of("Cur.txt"); //Открытие файла
                List<String> list = Files.readAllLines(Base); //Считывание файла в список
                double Cur1 = Parse(list, code1); //Поиск валюты

                if (Cur1 == 0) {
                    System.out.println("Код валюты не найден");
                } else {
                    System.out.println("Введите код валюты в которую необходимо сконвертировать (EUR, USD и т.д.):");
                    String code2 = scan.next();

                    double Cur2 = Parse(list,code2); //Поиск валюты
                    if (Cur2 == 0) {
                        System.out.println("Код валюты не найден");
                    } else {
                        double Result = CalcCur(Cur1,Cur2); //Расчет отношения
                        System.out.println("1 " + code1 + " равен " + Result + " " + code2);
                    }
                }

            } catch (IOException e) {
                System.out.println("Файл валют не обнаружен");
            }
    }
}


