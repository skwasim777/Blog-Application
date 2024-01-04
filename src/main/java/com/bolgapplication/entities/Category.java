package com.bolgapplication.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ctogeries")
@NoArgsConstructor
@Setter
@Getter
public class Category {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer categoryId;
		private String categoryTitle;
		private String categoryDesc;
}
