package web.culture.kakaoPay;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReadyResponse {
	private String tid;
	private String next_redirect_pc_url;
	private String partner_order_id;
}
