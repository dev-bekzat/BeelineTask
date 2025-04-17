
import java.util.HashMap;

interface DataSizeFormatter {
    String formatDataSize(long bytes);
}

interface SomeDetecter {
    String detect(String x, String y, HashMap<String, String> hm);
}

public class Traffic implements DataSizeFormatter {

    @Override
    public String formatDataSize(long bytes) {
        StringBuilder result = new StringBuilder();
        long gb = bytes / (1024 * 1024 * 1024);
        bytes %= (1024 * 1024 * 1024);

        long mb = bytes / (1024 * 1024);
        bytes %= (1024 * 1024);

        long kb = bytes / 1024;

        if (gb > 0) result.append(gb).append("Gb, ");
        if (mb > 0) result.append(mb).append("Mb, ");
        if (kb > 0) result.append(kb).append("Kb");

        String output = result.toString().trim();
        if (output.endsWith(",")) {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }

    public String formatOutput(String in) {
        String[] tokens = in.split(" ");
        StringBuilder result = new StringBuilder();
        for (String token : tokens) {
            try {
                long number = Long.parseLong(token);
                result.append(formatDataSize(number)).append(" ");
            } catch (NumberFormatException e) {
                result.append(token).append(" ");
            }
        }
        return result.toString().trim();
    }
}

class SomeClass implements SomeDetecter {

    @Override
    public String detect(String x, String y, HashMap<String, String> hm) {
        String key = x + "," + y;
        return hm.getOrDefault(key, "");
    }
}

class Comparator {
    public boolean compareExpression(String exp) {
        String[] tokens = exp.trim().split(" ");
        if (tokens.length != 3) return false;

        try {
            double left = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double right = Double.parseDouble(tokens[2]);

            switch (operator) {
                case ">": return left > right;
                case "<": return left < right;
                case ">=": return left >= right;
                case "<=": return left <= right;
                case "==": return left == right;
                case "!=": return left != right;
                default: return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
