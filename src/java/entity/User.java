/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The class User is where all the data related to the user is stored in order
 * to signIn and signUp
 *
 * @author Javier, Emil, Imanol, Fran
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="user",schema="esport_six")
@NamedQueries({
        @NamedQuery(name = "findUserByMail",query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.id = :id")
})
@XmlRootElement  
public class User implements Serializable{

    /**
     * Attributes for the user
     */
    private String name, passwd, phone, email, address;

    /**
     * Id field for User entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * UserType field for the User entity
     */
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    public User() {
    }

    public User(String name, String passwd, String phone, String email, String address, UserType userType) {
        this.name = name;
        this.passwd = passwd;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     *
     *
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Getter of the name attribute
     *
     * @return the value of the name attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the name attribute
     *
     * @param name the value to set to the name attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the passwd attribute
     *
     * @return the value of the passwd attribute
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Setter of the passwd attribute
     *
     * @param passwd the value to set to the passwd attribute
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * Getter of the phone attribute
     *
     * @return the value of the phone attribute
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter of the phone attribute
     *
     * @param phone the value to set to the phone attribute
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter of the email attribute
     *
     * @return the value of the email attribute
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email attribute
     *
     * @param email the value to set to the email attribute
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of the address attribute
     *
     * @return the value of the address attribute
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter of the address attribute
     *
     * @param address the value to set to the address attribute
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.passwd);
        hash = 71 * hash + Objects.hashCode(this.phone);
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.address);
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.userType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.passwd, other.passwd)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.userType != other.userType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", passwd=" + passwd + ", phone=" + phone + ", email=" + email + ", address=" + address + ", id=" + id + ", userType=" + userType + '}';
    } 
}
