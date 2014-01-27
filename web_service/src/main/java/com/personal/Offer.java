package com.personal;

import java.util.Date;
import java.util.Map;

/**
 * @author smalik3
 * @date 1/26/14.
 */
public class Offer {

    private String id;
    private String type;
    private Date startDate;
    private Date endDate;
    private Map<String, String> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (attributes != null ? !attributes.equals(offer.attributes) : offer.attributes != null) return false;
        if (endDate != null ? !endDate.equals(offer.endDate) : offer.endDate != null) return false;
        if (id != null ? !id.equals(offer.id) : offer.id != null) return false;
        if (startDate != null ? !startDate.equals(offer.startDate) : offer.startDate != null) return false;
        if (type != null ? !type.equals(offer.type) : offer.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", attributes=" + attributes +
                '}';
    }
}
