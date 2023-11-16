package kr.kernel360.anabada.domain.comment.dto;

import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.global.utils.DateParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link kr.kernel360.anabada.domain.comment.entity.Comment}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindCommentDto {
	private Long id;
	private String content;
	private String memberNickname;
	private String createdDate;


	public static FindCommentDto of(Comment comment) {
		return FindCommentDto.builder()
			.id(comment.getId())
			.content(comment.getContent())
			.memberNickname(comment.getMember().getNickname())
			.createdDate(DateParser.dateTimeToString(comment.getCreatedDate()))
			.build();
	}
}