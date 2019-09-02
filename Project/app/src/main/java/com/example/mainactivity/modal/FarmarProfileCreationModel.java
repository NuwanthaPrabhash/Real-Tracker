package com.example.mainactivity.modal;

public class FarmarProfileCreationModel {

    private Integer id;
    private String imageId;
    private String firstNameEdit;
    private String secoundNameEdit;
    private String emailEdit;
    private Integer contactNumberEdit;
    private String createUsernameEdit;
    private Integer createPasswordEdit;

    public FarmarProfileCreationModel() {
    }

    public FarmarProfileCreationModel(Integer id, String imageId, String firstNameEdit, String secoundNameEdit, String emailEdit, Integer contactNumberEdit, String createUsernameEdit, Integer createPasswordEdit) {
        this.id = id;
        this.imageId = imageId;
        this.firstNameEdit = firstNameEdit;
        this.secoundNameEdit = secoundNameEdit;
        this.emailEdit = emailEdit;
        this.contactNumberEdit = contactNumberEdit;
        this.createUsernameEdit = createUsernameEdit;
        this.createPasswordEdit = createPasswordEdit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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

    public String getCreateUsernameEdit() {
        return createUsernameEdit;
    }

    public void setCreateUsernameEdit(String createUsernameEdit) {
        this.createUsernameEdit = createUsernameEdit;
    }

    public Integer getCreatePasswordEdit() {
        return createPasswordEdit;
    }

    public void setCreatePasswordEdit(Integer createPasswordEdit) {
        this.createPasswordEdit = createPasswordEdit;
    }
}
