package fithou.edu.vn.DoAnTotNghiep.supplier.query.getAllSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import fithou.edu.vn.DoAnTotNghiep.supplier.dto.SupplierDto;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAllSuppliersQueryHandler implements IRequestHandler<GetAllSuppliersQuery, Paginated<SupplierDto>> {

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    @Override
    public HandleResponse<Paginated<SupplierDto>> handle(GetAllSuppliersQuery query) throws Exception {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Supplier.class);
        var root = criteriaQuery.from(Supplier.class);
        var predicate = criteriaBuilder.conjunction();
        if (query.getKeyword() != null && !query.getKeyword().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + query.getKeyword() + "%"));
        }
        if (query.getSortField() == null || query.getSortField().isEmpty()) {
            query.setSortField("name");
        }
        if (query.getSortDir() == null || query.getSortDir().isEmpty()) {
            query.setSortDir("desc");
        }
        criteriaQuery.where(predicate);

        var sort = query.getSortDirection();
        criteriaQuery.orderBy(sort.isAscending() ? criteriaBuilder.asc(root.get(query.getSortField())) : criteriaBuilder.desc(root.get(query.getSortField())));
        var q = entityManager.createQuery(criteriaQuery);
        var totalElements = q.getResultList().size();
        q.setFirstResult((query.getPage() - 1) * query.getPageSize());
        q.setMaxResults(query.getPageSize());
        var suppliers = q.getResultList();
        var supplierDtos = suppliers.stream().map(supplier -> modelMapper.map(supplier, SupplierDto.class)).toList();
        var paginated = Paginated.<SupplierDto>builder()
                .page(query.getPage())
                .hasNext(totalElements > query.getPage() * query.getPageSize())
                .hasPrevious(query.getPage() > 1)
                .totalElements(totalElements)
                .pageSize(query.getPageSize())
                .totalPages((int) Math.ceil((double) totalElements / query.getPageSize()))
                .data(supplierDtos).totalElements(totalElements).build();
        return HandleResponse.ok(paginated);
    }
}
