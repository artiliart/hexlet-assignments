package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {
    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode" ,source = "vendorCode")
    public abstract Product toEntity(ProductDTO productDTO);

    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode" ,source = "vendorCode")
    public abstract Product toEntity(ProductCreateDTO productCreateDTO);

    @Mapping(target = "cost", source = "price")
    public abstract Product toEntity(ProductUpdateDTO productUpdateDTO);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode" ,source = "barcode")
    public abstract ProductDTO toProductDTO(Product product);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode" ,source = "barcode")
    public abstract ProductCreateDTO toProductCreateDTO(Product product);

    @Mapping(target = "price", source = "cost")
    public abstract ProductUpdateDTO toProductUpdateDTO(Product product);
}
