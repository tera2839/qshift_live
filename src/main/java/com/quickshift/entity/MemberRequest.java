package com.quickshift.entity;

import jakarta.persistence.Entity;
<<<<<<< HEAD
=======
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "memberRequest")
public class MemberRequest {
	
	@Id
<<<<<<< HEAD
=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "calendar_id", referencedColumnName = "id")
	private Calendar calendar;
	
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "timeplan_id", referencedColumnName = "id")
	private Timeplan timeplan;
	
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "id")
	private Store store;
}