package web.culture.kakaoPay;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AmountVO {
	private Integer total, tax_free, vat, point, discount;
}
