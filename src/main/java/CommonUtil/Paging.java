package CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paging {
    private int startIndex;
    private int maxPerPage;
    private long totalRecord;
}
