import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Part two of
 * https://adventofcode.com/2020/day/4
 *
 * @author Nerijus
 */
public class Day04_2 extends Day04_1 {
    public static void main(String[] args) {
        System.out.println("Valid passport count: " + new Day04_2().getResult());
    }

    @Override
    boolean isValid(String[] fields) {
        return Arrays.stream(fields)
                .map(this::toField)
                .filter(f -> !"cid".equals(f.type))
                .filter(Field::isValid)
                .count() == 7;
    }

    private Field toField(String fieldData) {
        String type = fieldData.substring(0, 3);
        String value = fieldData.substring(4);
        switch (type) {
            case "cid":
                return new CidField(value);
            case "byr":
                return new BirthYearField(value);
            case "iyr":
                return new IssueYearField(value);
            case "eyr":
                return new ExpirationYearField(value);
            case "hgt":
                return new HeightField(value);
            case "hcl":
                return new HairColorField(value);
            case "ecl":
                return new EyeColorField(value);
            case "pid":
                return new PassportIdField(value);
            default:
                throw new IllegalStateException("Unknown field: " + fieldData);
        }
    }

    static abstract class Field {
        String type;
        String value;

        public Field(String type, String value) {
            this.type = type;
            this.value = value;
        }

        abstract boolean isValid();
    }

    static class CidField extends Field {
        public CidField(String value) {
            super("cid", value);
        }

        @Override
        boolean isValid() {
            return true;
        }
    }

    static class BirthYearField extends Field {
        public BirthYearField(String value) {
            super("byr", value);
        }

        @Override
        boolean isValid() {
            if (value.length() != 4) {
                return false;
            }
            int year = Integer.parseInt(value);
            return year >= 1920 && year <= 2002;
        }
    }

    static class IssueYearField extends Field {
        public IssueYearField(String value) {
            super("iyr", value);
        }

        @Override
        boolean isValid() {
            if (value.length() != 4) {
                return false;
            }
            int year = Integer.parseInt(value);
            return year >= 2010 && year <= 2020;
        }
    }

    static class ExpirationYearField extends Field {
        public ExpirationYearField(String value) {
            super("eyr", value);
        }

        @Override
        boolean isValid() {
            if (value.length() != 4) {
                return false;
            }
            int year = Integer.parseInt(value);
            return year >= 2020 && year <= 2030;
        }
    }

    static class HeightField extends Field {
        public HeightField(String value) {
            super("hgt", value);
        }

        @Override
        boolean isValid() {
            if (!value.endsWith("cm") && !value.endsWith("in")) {
                return false;
            }
            String unit = value.substring(value.length() - 2);
            int amount = Integer.parseInt(value.substring(0, value.length() - 2));
            if ("cm".equals(unit)) {
                return amount >= 150 && amount <= 193;
            } else {
                return amount >= 59 && amount <= 76;
            }
        }
    }

    static class HairColorField extends Field {
        public HairColorField(String value) {
            super("hcl", value);
        }

        @Override
        boolean isValid() {
            if (!value.startsWith("#") || value.length() != 7) {
                return false;
            }
            return value.matches("#[\\da-f]{6}");
        }
    }

    static class EyeColorField extends Field {
        public EyeColorField(String value) {
            super("ecl", value);
        }

        @Override
        boolean isValid() {
            return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value);
        }
    }

    static class PassportIdField extends Field {
        public PassportIdField(String value) {
            super("pid", value);
        }

        @Override
        boolean isValid() {
            return value.matches("\\d{9}");
        }
    }
}
