package kr.kernel360.anabada.domain.tradeoffer.dto;

import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTradeOfferResponse {
	private Long id;

	private String title;

	private String content;

	private String imagePath;

	public static UpdateTradeOfferResponse of(TradeOffer tradeOffer) {
		return UpdateTradeOfferResponse.builder()
			.id(tradeOffer.getId())
			.title(tradeOffer.getTitle())
			.content(tradeOffer.getContent())
			.imagePath(tradeOffer.getImagePath())
			.build();
	}
}
