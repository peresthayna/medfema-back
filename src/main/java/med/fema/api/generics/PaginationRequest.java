package med.fema.api.generics;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

@Data
@NoArgsConstructor
public class PaginationRequest {
	private int page = 0;
	private int pageSize = 20;
	private String order;
	private boolean ascending = true;
	
	public Pageable toPageable() {
		if(this.order != null && !this.order.isEmpty()) {
			return PageRequest.of(page, pageSize, ascending ? Direction.ASC : Direction.DESC, order);
		} else {
			return PageRequest.of(page, pageSize);
		}
	}
}
