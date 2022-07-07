package task1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var cats = Cat.makeCats(10);
        Printer.print(cats);

        // А сюда добавьте код, который будет сортировать коллекцию котов
        // используйте лямбда-выражения и ссылки на методы
        //cats.sort(?);
        //Printer.print(cats);

        while (true) {
            switch (askChoice()) {
                case 1:
                    cats.sort((o1, o2) -> o1.getBreed().toString().compareTo(o2.getBreed().toString()));
                    // Можно и так - (Cat::sortByBreed)
                    break;
                case 2:
                    cats.sort((o1, o2) -> {
                        if (o1.getName().equals(o2.getName()))
                            return o1.getAge() - o2.getAge();
                        return o1.getName().compareTo(o2.getName());
                    });
                    // (Cat::sortByName)
                    break;
                case 3:
                    Cat.Color color = askColor();
                    cats.removeIf(cat -> cat.getColor() == color);
                    // (Cat::isColor)
                    break;
                case 4:
                    cats.removeIf(cat -> cat.getName().length() == 5);
                    // (Cat::isLengthFive)
                    break;
                case 5:
                    return;
            }
            Printer.print(cats);
        }
    }

    private static int askChoice() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1 -> Sort by breed
                    2 -> Sort by name and age
                    3 -> Delete cats by chosen color
                    4 -> Delete cats which names' length is 5
                    5 -> Exit program
                    """);
            System.out.print("Your choice: ");
            try {
                String str = sc.nextLine().replaceAll("\\s+", "");
                return checkAndParseChoice(str);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int checkAndParseChoice(String str) {
        if (str.equals(""))
            throw new RuntimeException("Choice can't be empty!");
        int choice = Integer.parseInt(str);
        if (choice < 1 || choice > 5)
            throw new RuntimeException("Can't find this action");
        return choice;
    }

    private static Cat.Color askColor(){
        Cat.Color[] colors = Cat.Color.values();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1 -> TABBY
                    2 -> SILVER
                    3 -> GRAY
                    4 -> PEACH
                    5 -> GINGER
                    6 -> TORTIE
                    """);
            System.out.print("Choose color: ");
            try {
                String str = sc.nextLine().replaceAll("\\s+", "");
                int choice = checkColor(str);
                return colors[choice - 1];
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static int checkColor(String str) {
        if (str.equals(""))
            throw new RuntimeException("Choice can't be empty!");
        int choice = Integer.parseInt(str);
        if (choice < 1 || choice > 5)
            throw new RuntimeException("Can't find this action");
        return choice;
    }
}