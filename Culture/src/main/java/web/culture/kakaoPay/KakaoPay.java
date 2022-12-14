package web.culture.kakaoPay;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import web.culture.dto.SubmitDto;
import web.culture.entity.User;

@Slf4j
@Service
public class KakaoPay {
	private static final String HOST = "https://kapi.kakao.com";
	
	private KakaoPayReadyVO kakaoPayReadyVO;
	private KakaoPayApprovalVO kakaoPayApprovalVO;
	
	public String kakaoPayReady(HttpSession session) {
		RestTemplate restTemplate = new RestTemplate();
		
		User user = (User) session.getAttribute("user");
		SubmitDto dto = (SubmitDto) session.getAttribute("submitDto");
		
//		서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "73cafee970de795b7ef6cccf45a0c24c");
		headers.add("Aceept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content_Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
//		서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "1001");
		params.add("partner_user_id", user.getUserName());
		params.add("item_name", dto.getSubject());
		params.add("quantity", "1");
		params.add("total_amount", "" + dto.getPayment());
		params.add("tax_free_amount", "100");
		params.add("approval_url", "http://localhost/kakaoPaySuccess");
		params.add("cancel_url", "http://localhost/kakaoPayCancel");
		params.add("fail_url", "http://localhost/kakaoPaySuccessFail");
		
		HttpEntity<MultiValueMap<String, String>> body
			= new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			kakaoPayReadyVO = restTemplate.postForObject(
					new URI(HOST + "/v1/payment/ready")
					, body
					, KakaoPayReadyVO.class);
			
			log.info(">>> " + kakaoPayReadyVO);
			
			return kakaoPayReadyVO.getNext_redirect_pc_url();
		} catch(RestClientException e) {
			e.printStackTrace();
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
		return "/pay";
	}
	
	public KakaoPayApprovalVO kakaoPayInfo(String pg_token, HttpSession session) {
		log.info(">>> KakaoPayInfoVO");
		log.info("------------------");
		
		RestTemplate restTemplate = new RestTemplate();
		
		User user = (User) session.getAttribute("user");
		SubmitDto dto = (SubmitDto) session.getAttribute("submitDto");
		
//		서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "73cafee970de795b7ef6cccf45a0c24c");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
//		서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyVO.getTid());
		params.add("partner_order_id", "1001");
		params.add("partner_user_id", user.getUserName());
		params.add("pg_token", pg_token);
		params.add("total_amount", "" + dto.getPayment());
		
		HttpEntity<MultiValueMap<String, String>> body
			= new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			kakaoPayApprovalVO = restTemplate.postForObject
				(new URI(HOST + "/v1/payment/approve"), body
				, KakaoPayApprovalVO.class);
			
			log.info(">>> " + kakaoPayApprovalVO);
			
			return kakaoPayApprovalVO;
		} catch(RestClientException e) {
			e.printStackTrace();
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
