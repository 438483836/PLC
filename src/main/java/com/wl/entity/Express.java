package com.wl.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author jianghc
 * @create 2017-05-20 13:39
 **/
@Entity
@Table(name = "t_express")
public class Express implements Serializable {
    private static final long serialVersionUID = -719136099599363658L;

    private Integer id;

    private String barCode;

    private String code16;

    private String consignorName;

    private String consignorAddress;

    private String consignorZip;

    private String consigneeName;

    private String consigneeAddress;

    private String consigneeZip;

    private Integer exitCode;//闸口号

    private Integer groupId;

    private Integer weight;

    private byte[] hex;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCode16() {
        return code16;
    }

    public void setCode16(String code16) {
        this.code16 = code16;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress;
    }

    public String getConsignorZip() {
        return consignorZip;
    }

    public void setConsignorZip(String consignorZip) {
        this.consignorZip = consignorZip;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeZip() {
        return consigneeZip;
    }

    public void setConsigneeZip(String consigneeZip) {
        this.consigneeZip = consigneeZip;
    }

    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }

    @Column(name="weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Column(name="hex")
    public byte[] getHex() {
        return hex;
    }

    public void setHex(byte[] hex) {
        this.hex = hex;
    }

    public Express(String barCode, String code16, Integer groupId) {
        this.barCode = barCode;
        this.code16 = code16;
        this.groupId = groupId;
    }

    public Express() {
    }
}
