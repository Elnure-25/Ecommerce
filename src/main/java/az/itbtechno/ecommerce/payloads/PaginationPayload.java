package az.itbtechno.ecommerce.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationPayload<T> {

    int pageSize;
    int currentPage;
    List<T> data;
}