package wesp.company.feirafacilapi.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

    public static Pageable createPageableRequest(int page, int size, String[] sort) {
        if (sort.length != 2) {
            throw new IllegalArgumentException("Sort must contain field and direction (e.g., 'name,asc')");
        }
        Sort sortConfig = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        return PageRequest.of(page, size, sortConfig);
    }
}
