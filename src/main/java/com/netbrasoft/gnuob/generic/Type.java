package com.netbrasoft.gnuob.generic;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@Cacheable(value = false)
@MappedSuperclass
public abstract class Type implements Serializable {

   private static final long serialVersionUID = 7895247154381678321L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Version
   @Column(name = "VERSION")
   private int version;

   @Column(name = "CREATION")
   private Timestamp creation;

   @Column(name = "MODIFICATION")
   private Timestamp modification;

   @XmlTransient
   public Timestamp getCreation() {
      return creation;
   }

   @XmlAttribute(name = "id", required = false)
   public long getId() {
      return id;
   }

   @XmlTransient
   public Timestamp getModification() {
      return modification;
   }

   @XmlAttribute(name = "version", required = false)
   public int getVersion() {
      return version;
   }

   @Transient
   public abstract void prePersist();

   @PrePersist
   protected void prePersistType() {
      creation = new Timestamp(System.currentTimeMillis());
      modification = new Timestamp(System.currentTimeMillis());
      prePersist();
   }

   @Transient
   public abstract void preUpdate();

   @PreUpdate
   protected void preUpdateType() {
      modification = new Timestamp(System.currentTimeMillis());
      preUpdate();
   }

   public void setCreation(Timestamp creation) {
      this.creation = creation;
   }

   public void setId(long id) {
      this.id = id;
   }

   public void setModification(Timestamp modification) {
      this.modification = modification;
   }

   public void setVersion(int version) {
      this.version = version;
   }
}
