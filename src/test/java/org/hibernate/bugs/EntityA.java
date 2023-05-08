package org.hibernate.bugs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ENTITY_A")
public class EntityA {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Integer id;

	@JoinColumn(name = "PARENT")
	@ManyToOne
	EntityA parent;

	@OneToMany(mappedBy = "parent")
	List<EntityA> children = new ArrayList<>();

	@JoinColumn(name = "ENTITY_A")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	List<EntityB> listOfEntitiesB = new ArrayList<>();
}