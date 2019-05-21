package com.gpch.login.model;

public class projectEnum {
    public enum Roles {
        ADMIN(1, "ADMIN"),
        USER (2, "USER");

        private int id;
        private String role;

        Roles(int id, String role) {
            this.id = id;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

}
