package com.zyjd.tijia.entity;


public class File {
    private int id;
    private Integer upload_ppl;
    private User upload_ppl_detail;
    private String created;
    private String modified;
    private String source;
    private String filename;
    private String file_catagory;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUpload_ppl_detail() {
        return upload_ppl_detail;
    }

    public void setUpload_ppl_detail(User upload_ppl_detail) {
        this.upload_ppl_detail = upload_ppl_detail;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFile_catagory() {
        return file_catagory;
    }

    public void setFile_catagory(String file_catagory) {
        this.file_catagory = file_catagory;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUpload_ppl() {
        return upload_ppl;
    }

    public void setUpload_ppl(int upload_ppl) {
        this.upload_ppl = upload_ppl;
    }

    public static class UploadPplDetailBean {
        /**
         * id : 1
         * username : 3-1175
         * name : 陈蒙
         */

        private int id;
        private String username;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
