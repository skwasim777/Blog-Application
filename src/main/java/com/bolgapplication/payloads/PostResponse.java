package com.bolgapplication.payloads;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bolgapplication.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private Long totalElement;
	private int totalPages;
	private boolean lastPage;
}
