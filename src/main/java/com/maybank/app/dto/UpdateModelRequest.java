package com.maybank.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModelRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 // The ID of the entity to update
	public Long id;
	// The new description to set
	private String description;
}