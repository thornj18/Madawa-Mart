package org.pharmart.madawamart.models;

/**
 * Created by Tonny on 7/24/2016.
 */
public class DrugModel {
    public String brand_name;
    public String generic_name;
    public String cautions;
    public String contraindications;
    public String indications;
    public String storage;
    public String comments;

    public DrugModel(){}

    public DrugModel(String brand_name, String comments, String contraindications, String indications, String storage, String Cautions, String generic_name) {
        this.brand_name = brand_name;
        this.comments = comments;
        this.contraindications = contraindications;
        this.indications = indications;
        this.storage = storage;
        this.cautions = Cautions;
        this.generic_name = generic_name;
    }


    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public String getCautions() {
        return cautions;
    }

    public void setCautions(String cautions) {
        this.cautions = cautions;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



}
