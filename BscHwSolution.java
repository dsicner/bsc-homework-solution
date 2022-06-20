import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class BscHwSolution {


    public static final String QUIT = "quit";

    public static LinkedHashMap<String, Double> records = new LinkedHashMap<>();

    public static void main(String[] args) {

        readData();
    }

    public static void readData() {

        Scanner sc = new Scanner(System.in);

        String stringLine = "";

        while (stringLine != null) {
            stringLine = sc.nextLine();

            if (stringLine.equals(QUIT)) {
                sortAndPrintData();
                System.exit(0);
            }

            System.out.println(stringLine);
            String[] values = stringLine.split("\\s");

            if (values.length != 2) {
                sortAndPrintData();
                System.err.println("Invalid number of arguments");
                System.exit(1);
            }

            double weight = Double.parseDouble(values[0]);
            String postalCode = values[1];

            saveData(weight, postalCode);

        }

        sortAndPrintData();

    }

    public static void saveData(double weight, String postalCode) {

        if (records.containsKey(postalCode)) {
            records.put(postalCode, records.get(postalCode) + weight);
        } else {
            records.put(postalCode, weight);
        }

    }

    public static void sortAndPrintData() {

        Set<Map.Entry<String, Double>> companyFounderSet = records.entrySet();

        List<Map.Entry<String, Double>> companyFounderListEntry = new ArrayList<>(companyFounderSet);

        Collections.sort(companyFounderListEntry, (es1, es2) -> es2.getValue().compareTo(es1.getValue()));

        records.clear();

        for (Map.Entry<String, Double> map : companyFounderListEntry) {
            records.put(map.getKey(), map.getValue());
        }

        NumberFormat formatter = new DecimalFormat("#0.000");

        for (Map.Entry<String, Double> entry : records.entrySet()) {
            System.out.println(entry.getKey() + " " + formatter.format(entry.getValue()));
        }
    }

}
