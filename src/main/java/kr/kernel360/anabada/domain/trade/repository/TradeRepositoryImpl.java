package kr.kernel360.anabada.domain.trade.repository;

import static kr.kernel360.anabada.domain.category.entity.QCategory.*;
import static kr.kernel360.anabada.domain.member.entity.QMember.*;
import static kr.kernel360.anabada.domain.place.entity.QPlace.*;
import static kr.kernel360.anabada.domain.trade.entity.QTrade.*;
import static kr.kernel360.anabada.domain.tradeoffer.entity.QTradeOffer.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.kernel360.anabada.domain.place.entity.Place;
import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.QFindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.QStateCountDto;
import kr.kernel360.anabada.domain.trade.dto.StateCountDto;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import kr.kernel360.anabada.global.utils.OrderByNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeRepositoryImpl implements TradeRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<FindTradeDto> findTrades(TradeSearchCondition tradeSearchCondition, Place placeCondition, Pageable pageable) {
		List<FindTradeDto> content = queryFactory
			.select(new QFindTradeDto(
				trade.id,
				trade.tradeType,
				trade.tradeStatus,
				trade.deletedStatus,
				category.name,
				trade.title,
				trade.member.nickname,
				trade.createdDate,
				tradeOffer.member.nickname
			))
			.from(trade)
			.leftJoin(trade.category, category)
			.leftJoin(trade.member, member)
			.leftJoin(trade.tradeOffers, tradeOffer)
			.on(tradeOffer.tradeOfferStatus.eq(TradeOfferStatus.REQUEST_ACCEPTED))
			.leftJoin(tradeOffer.member, member)
			.leftJoin(trade.place, place)
			.where(
				tradeTypeEq(tradeSearchCondition.getTradeType()),
				categoryIdEq(tradeSearchCondition.getCategoryId()),
				tradeCreatedByContain(tradeSearchCondition.getCreatedBy()),
				tradeTitleContain(tradeSearchCondition.getTitle()),
				placeEq(placeCondition)
			)
			.orderBy(trade.tradeStatus.desc(), trade.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(trade.count())
			.from(trade)
			.leftJoin(trade.member, member)
			.where(
				tradeTypeEq(tradeSearchCondition.getTradeType()),
				categoryIdEq(tradeSearchCondition.getCategoryId()),
				tradeCreatedByContain(tradeSearchCondition.getCreatedBy()),
				tradeTitleContain(tradeSearchCondition.getTitle()),
				placeEq(placeCondition)
			)
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public FindTradeDto findTrade(Long tradeId) {
		return queryFactory
			.select(new QFindTradeDto(
				trade.id,
				trade.tradeType,
				trade.tradeStatus,
				trade.deletedStatus,
				category.name,
				trade.title,
				member.nickname,
				trade.createdDate,
				trade.content,
				trade.imagePath
			))
			.from(trade)
			.leftJoin(trade.category, category)
			.leftJoin(trade.member, member)
			.where(trade.id.eq(tradeId))
			.fetchOne();
	}

	@Override
	public Long countTradeOfferByTradeIdAndEmail(Long tradeId, String email) {
		return queryFactory
			.select(trade.count())
			.from(trade)
			.leftJoin(trade.tradeOffers, tradeOffer)
			.leftJoin(tradeOffer.member, member)
			.where(
				tradeOffer.trade.id.eq(tradeId)
					.and(member.email.in(email))
			)
			.fetchOne();
	}

	@Override
	public List<StateCountDto> countTradeByState() {
		return queryFactory
			.select(new QStateCountDto(
				trade.place.state,
				trade.count()
			))
				.from(trade)
				.groupBy(trade.place.state)
				.orderBy(OrderByNull.DEFAULT)
				.fetch();
	}

	private BooleanExpression tradeTypeEq(String tradeType) {
		return hasText(tradeType) ? trade.tradeType.eq(TradeType.valueOf(tradeType)) : null;
	}

	private BooleanExpression categoryIdEq(Long categoryId) {
		return categoryId != null ? trade.category.id.eq(categoryId) : null;
	}

	private BooleanExpression tradeTitleContain(String title) {
		return hasText(title) ? trade.title.contains(title) : null;
	}

	private BooleanExpression tradeCreatedByContain(String createdBy) {
		return hasText(createdBy) ? trade.member.nickname.contains(createdBy) : null;
	}

	private BooleanExpression placeEq(Place placeCondition) {
		return placeCondition != null ? trade.place.eq(placeCondition) : null;
	}
}
