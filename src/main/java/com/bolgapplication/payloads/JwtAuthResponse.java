package com.bolgapplication.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthResponse {
	private String token;
}
