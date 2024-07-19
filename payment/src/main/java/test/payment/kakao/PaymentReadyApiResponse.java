package test.payment.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReadyApiResponse {

  // 결제 고유 번호
  private String tid;

  // qna : tms_result은 일부 가맹점에서 사용되는 특수 필드로 일반적인 경우 tms_result 값은 무시해 주셔도 무관합니다.
  @JsonProperty(value = "tms_result")
  private Boolean tmsResult;

  @JsonProperty(value = "created_at")
  private String createdAt;

  // 카카오톡 결제 페이지 Redirect URL
  @JsonProperty(value = "next_redirect_pc_url")
  private String nextRedirectPcUrl;

  @JsonProperty(value = "next_redirect_mobile_url")
  private String nextRedirectMobileUrl;

  @JsonProperty(value = "next_redirect_app_url")
  private String nextRedirectAppUrl;

  @JsonProperty(value = "android_app_scheme")
  private String androidAppScheme;

  @JsonProperty(value = "ios_app_scheme")
  private String iosAppScheme;

}