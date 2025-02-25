package wesp.company.feirafacilapi.domain.enums;

public enum EnumUserType {

    SELLER("SELLER", 0),
    COSTUMER("COSTUMER", 1);

    private String key;
    private Integer value;

    EnumUserType(String key, Integer value) {
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
