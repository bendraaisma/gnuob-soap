package com.netbrasoft.gnuob.generic.product;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.netbrasoft.gnuob.generic.Type;

@Cacheable(value = false)
@Entity(name = Option.ENTITY)
@Table(name = Option.TABLE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlRootElement(name = Option.ENTITY)
public class Option extends Type {

   private static final long serialVersionUID = -4350389615614303733L;
   protected static final String ENTITY = "Option";
   protected static final String TABLE = "GNUOB_OPTIONS";

   @Column(name = "POSITION")
   private Integer position = 0;

   @Column(name = "VALUE", nullable = false)
   private String value;

   @Column(name = "DESCRIPTION")
   private String description;

   @Column(name = "DISABLED", nullable = false)
   private boolean disabled;

   public String getDescription() {
      return description;
   }

   @XmlTransient
   public Integer getPosition() {
      return position;
   }

   @XmlElement(name = "value", required = true)
   public String getValue() {
      return value;
   }

   @XmlElement(name = "disabled", required = true)
   public boolean isDisabled() {
      return disabled;
   }

   @Override
   public void prePersist() {
      return;
   }

   @Override
   public void preUpdate() {
      return;
   }

   @XmlElement(name = "description")
   public void setDescription(String description) {
      this.description = description;
   }

   public void setDisabled(boolean disabled) {
      this.disabled = disabled;
   }

   public void setPosition(Integer position) {
      this.position = position;
   }

   public void setValue(String value) {
      this.value = value;
   }
}