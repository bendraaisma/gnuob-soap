package com.netbrasoft.gnuob.generic.order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.netbrasoft.gnuob.generic.Type;
import com.netbrasoft.gnuob.generic.customer.Address;

@Entity(name = Shipment.ENTITY)
@Table(name = Shipment.TABLE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlRootElement(name = Shipment.ENTITY)
public class Shipment extends Type {

    private static final long serialVersionUID = 7122488386952479304L;
    protected static final String ENTITY = "Shipment";
    protected static final String TABLE = "GNUOB_SHIPMENTS";

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true, optional = false)
    private Address address;

    public Shipment() {

    }

    @XmlElement(name = "address", required = true)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
