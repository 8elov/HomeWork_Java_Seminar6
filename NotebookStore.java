import java.util.*;

public class NotebookStore {

    public static void main(String[] args) {
        Set<Notebook> notebooks = createNotebooks();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:\n" +
                    "1 - Марка\n" +
                    "2 - ОЗУ\n" +
                    "3 - Объем ЖД\n" +
                    "4 - Операционная система\n" +
                    "5 - Цвет\n" +
                    "6 - Цена");

            int filterType = scanner.nextInt();
            Map<Integer, String> filters = new HashMap<>();

            while (filterType >= 1 && filterType <= 6) {
                String filterValue;
                switch (filterType) {
                    case 1:
                        System.out.print("Введите марку: ");
                        filterValue = scanner.next();
                        filters.put(filterType, filterValue);
                        break;
                    case 2:
                        System.out.print("Введите минимальный объем ОЗУ: ");
                        filterValue = scanner.next();
                        filters.put(filterType, filterValue);
                        break;
                    case 3:
                        System.out.print("Введите минимальный объем жесткого диска: ");
                        filterValue = scanner.next();
                        filters.put(filterType, filterValue);
                        break;
                    case 4:
                        System.out.print("Введите операционную систему: ");
                        filterValue = scanner.next();
                        filters.put(filterType, filterValue);
                        break;
                    case 5:
                        System.out.print("Введите цвет: ");
                        filterValue = scanner.next();
                        filters.put(filterType, filterValue);
                        break;
                    case 6:
                        System.out.print("Введите максимальную цену: ");
                        filterValue = scanner.next();
                        filters.put(filterType, filterValue);
                        break;
                    default:
                        System.out.println("Некорректный выбор.");
                        break;
                }
                System.out.println(
                        "Введите цифру, соответствующую необходимому критерию, или любую другую цифру, чтобы завершить:");
                filterType = scanner.nextInt();
            }

            Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);

            if (filteredNotebooks.size() > 0) {
                System.out.println("Результаты фильтрации:");
                for (Notebook notebook : filteredNotebooks) {
                    System.out.println(notebook.toString());
                }
            } else {
                System.out.println("Нет результатов, соответствующих указанным критериям.");
            }
        }
    }

    private static Set<Notebook> createNotebooks() {
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Lenovo", 8, 256, "Windows 10", "Black", 699.99));
        notebooks.add(new Notebook("Apple", 16, 512, "macOS", "Silver", 1299.99));
        notebooks.add(new Notebook("Dell", 16, 512, "Windows 10", "Black", 1199.99));
        notebooks.add(new Notebook("HP", 8, 512, "Windows 10", "White", 849.99));
        notebooks.add(new Notebook("Asus", 8, 256, "Windows 10", "Grey", 799.99));
        notebooks.add(new Notebook("Acer", 16, 512, "Windows 10", "Silver", 999.99));
        notebooks.add(new Notebook("MSI", 32, 1024, "Windows 10", "Black", 2499.99));
        notebooks.add(new Notebook("Huawei", 16, 512, "Windows 10", "Grey", 1099.99));
        notebooks.add(new Notebook("Razer", 16, 512, "Windows 10", "Black", 1799.99));
        notebooks.add(new Notebook("Samsung", 8, 256, "Windows 10", "Silver", 749.99));
        return notebooks;
    }

    private static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<Integer, String> filters) {
        Set<Notebook> filteredNotebooks = new HashSet<>(notebooks);
        Iterator<Notebook> iterator = filteredNotebooks.iterator();
        while (iterator.hasNext()) {
            Notebook notebook = iterator.next();
            for (Map.Entry<Integer, String> entry : filters.entrySet()) {
                int filterType = entry.getKey();
                String filterValue = entry.getValue();
                switch (filterType) {
                    case 1:
                        if (!notebook.getBrand().equalsIgnoreCase(filterValue)) {
                            iterator.remove();
                        }
                        break;
                    case 2:
                        if (notebook.getRam() < Integer.parseInt(filterValue)) {
                            iterator.remove();
                        }
                        break;
                    case 3:
                        if (notebook.getStorage() < Integer.parseInt(filterValue)) {
                            iterator.remove();
                        }
                        break;
                    case 4:
                        if (!notebook.getOs().equalsIgnoreCase(filterValue)) {
                            iterator.remove();
                        }
                        break;
                    case 5:
                        if (!notebook.getColor().equalsIgnoreCase(filterValue)) {
                            iterator.remove();
                        }
                        break;
                    case 6:
                        if (notebook.getPrice() > Double.parseDouble(filterValue)) {
                            iterator.remove();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return filteredNotebooks;
    }
}