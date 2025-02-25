package wesp.company.feirafacilapi.domain.enums;

public enum EnumOrderStatus {
    PENDING("PENDING", 0),
    COMPLETED("COMPLETED", 1),
    REJECTED("REJECTED", 2);

    private String key;
    private Integer value;

    EnumOrderStatus(String key, Integer value) {
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
