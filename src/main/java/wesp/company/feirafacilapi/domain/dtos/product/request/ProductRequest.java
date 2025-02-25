package wesp.company.feirafacilapi.domain.dtos.product.request;

public record ProductRequest (String code, String name, int price, int quantity,  String imageUrl, String description){
}
