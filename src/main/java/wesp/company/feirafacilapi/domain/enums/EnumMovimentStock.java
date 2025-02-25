package wesp.company.feirafacilapi.domain.enums;

public enum EnumMovimentStock {
    INPUT("INPUT", 0),
    OUTPUT("OUTPUT", 1);

    private String key;
    private Integer value;

    EnumMovimentStock(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
