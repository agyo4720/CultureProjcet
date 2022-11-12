package web.culture.kakaoPay;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CardVO {
	private String purchase_corp, purchase_corp_code;
	
	private String issuer_corp, issuer_corp_code;
	
	private String bin, card_type, install_month, approved_id, card_min;
	
	private String interest_free_install, card_item_code;
}
