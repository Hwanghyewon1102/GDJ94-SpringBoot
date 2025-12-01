package com.winter.app.product;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
	private Long productsNum;
	private String productsName;
	private String productsContents;
	private String productsCategory;
	private BigDecimal productsRate;
	private byte productsSale;
}
