package com.lkx.springbootjedis.pojo;

/**
 * @author lee Cather
 * @date 2018-10-19 11:17
 * desc :
 */
public class HashPojo {
    public HashPojo() {
    }

    public HashPojo(Integer hash) {
        Hash = hash;
    }

    private Integer Hash;

    public Integer getHash() {
        return Hash;
    }

    public void setHash(Integer hash) {
        Hash = hash;
    }

    @Override
    public String toString() {
        return "HashPojo{" +
                "Hash=" + Hash +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashPojo hashPojo = (HashPojo) o;

        return Hash != null ? Hash.equals(hashPojo.Hash) : hashPojo.Hash == null;
    }

    @Override
    public int hashCode() {
        return Hash != null ? Hash%2 : 0;
    }
}
