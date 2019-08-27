package com.example.mainactivity.modal;

public class FarmarProfileCreationModel {

    private Integer id;
    private String firstNameEdit;
    private String secoundNameEdit;
    private String emailEdit;
    private Integer contactNumberEdit;

    public FarmarProfileCreationModel() {
    }

    public FarmarProfileCreationModel(Integer id, String firstNameEdit, String secoundNameEdit, String emailEdit, Integer contactNumberEdit) {
        this.id = id;
        this.firstNameEdit = firstNameEdit;
        this.secoundNameEdit = secoundNameEdit;
        this.emailEdit = emailEdit;
        this.contactNumberEdit = contactNumberEdit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstNameEdit() {
        return firstNameEdit;
    }

    public void setFirstNameEdit(String firstNameEdit) {
        this.firstNameEdit = firstNameEdit;
    }

    public String getSecoundNameEdit() {
        return secoundNameEdit;
    }

    public void setSecoundNameEdit(String secoundNameEdit) {
        this.secoundNameEdit = secoundNameEdit;
    }

    public String getEmailEdit() {
        return emailEdit;
    }

    public void setEmailEdit(String emailEdit) {
        this.emailEdit = emailEdit;
    }

    public Integer getContactNumberEdit() {
        return contactNumberEdit;
    }

    public void setContactNumberEdit(Integer contactNumberEdit) {
        this.contactNumberEdit = contactNumberEdit;
    }
}
