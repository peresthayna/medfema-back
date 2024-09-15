package med.fema.api.generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {
	private List<T> items;
	private boolean hasNext;
	private long totalElements;
	private int totalPages;
}
