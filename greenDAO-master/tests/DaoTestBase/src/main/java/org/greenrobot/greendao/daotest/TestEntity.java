package org.greenrobot.greendao.daotest;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * This entity is used by internal tests of greenDAO.
 * (This JavaDoc is defined in the generator project.)
 */
// This is another test comment, you could also apply annotations like this
@Entity
public class TestEntity {

    /**
     * JavaDoc test field
     */
    @Id
    private Long id;
    private int simpleInt;
    private Integer simpleInteger;

    @NotNull
    private String simpleStringNotNull;
    private String simpleString;

    @Index
    private String indexedString;

    @Index(unique = true)
    private String indexedStringAscUnique;
    private java.util.Date simpleDate;
    private Boolean simpleBoolean;
    private byte[] simpleByteArray;

    @Generated
    public TestEntity() {
    }

    public TestEntity(Long id) {
        this.id = id;
    }

    @Generated
    public TestEntity(Long id, int simpleInt, Integer simpleInteger, String simpleStringNotNull, String simpleString, String indexedString, String indexedStringAscUnique, java.util.Date simpleDate, Boolean simpleBoolean, byte[] simpleByteArray) {
        this.id = id;
        this.simpleInt = simpleInt;
        this.simpleInteger = simpleInteger;
        this.simpleStringNotNull = simpleStringNotNull;
        this.simpleString = simpleString;
        this.indexedString = indexedString;
        this.indexedStringAscUnique = indexedStringAscUnique;
        this.simpleDate = simpleDate;
        this.simpleBoolean = simpleBoolean;
        this.simpleByteArray = simpleByteArray;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * JavaDoc test getter
     */
    public int getSimpleInt() {
        return simpleInt;
    }

    public void setSimpleInt(int simpleInt) {
        this.simpleInt = simpleInt;
    }

    public Integer getSimpleInteger() {
        return simpleInteger;
    }

    /**
     * JavaDoc test setter
     */
    public void setSimpleInteger(Integer simpleInteger) {
        this.simpleInteger = simpleInteger;
    }

    /**
     * JavaDoc test getter and setter
     */
    @NotNull
    public String getSimpleStringNotNull() {
        return simpleStringNotNull;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    /**
     * JavaDoc test getter and setter
     */
    public void setSimpleStringNotNull(@NotNull String simpleStringNotNull) {
        this.simpleStringNotNull = simpleStringNotNull;
    }

    public String getSimpleString() {
        return simpleString;
    }

    public void setSimpleString(String simpleString) {
        this.simpleString = simpleString;
    }

    public String getIndexedString() {
        return indexedString;
    }

    public void setIndexedString(String indexedString) {
        this.indexedString = indexedString;
    }

    public String getIndexedStringAscUnique() {
        return indexedStringAscUnique;
    }

    public void setIndexedStringAscUnique(String indexedStringAscUnique) {
        this.indexedStringAscUnique = indexedStringAscUnique;
    }

    public java.util.Date getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(java.util.Date simpleDate) {
        this.simpleDate = simpleDate;
    }

    public Boolean getSimpleBoolean() {
        return simpleBoolean;
    }

    public void setSimpleBoolean(Boolean simpleBoolean) {
        this.simpleBoolean = simpleBoolean;
    }

    public byte[] getSimpleByteArray() {
        return simpleByteArray;
    }

    public void setSimpleByteArray(byte[] simpleByteArray) {
        this.simpleByteArray = simpleByteArray;
    }

}