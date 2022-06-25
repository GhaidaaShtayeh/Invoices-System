package com.example.invoices.model;

import com.example.invoices.utilite.Type;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "role")
public class Role {
    //public static enum Type {ROLE_USER, ROLE_SUPER_USER, ROLE_AUDITOR};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Type name;
    @Column(name = "description")
    private String description;


    @OneToMany
    private Set<Employee> employees;

   public Role(){}
    public Role(Type name , String description){
       this.name = name;
       this.description=description;
   }

    public String getDescription() { return description; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public void setDescription(String description) {
		this.description = description;
	}
	public Type getName() {
		return name;
	}
	public void setName(Type name) {
		this.name = name;
	}
	
	
}
