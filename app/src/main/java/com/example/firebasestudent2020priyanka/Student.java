package com.example.firebasestudent2020priyanka;

public class Student
{

        String unId;
        String email;
        String pass;
        String sname;
        String addr;
        String phone;
        float per;

        Student()
        {}

        public Student(String unId, String email, String pass, String sname, String addr, String phone, float per) {
            this.unId = unId;
            this.email = email;
            this.pass = pass;
            this.sname = sname;
            this.addr = addr;
            this.phone = phone;
            this.per = per;
        }

        public String toString()
        {
            return "ID : "+unId+ "  MAIL : "+email+"   PASSWORD : "+pass+"  NAME : "+sname+" ADDRESS : "+addr+"  PHONE : "+phone+"   PER : "+per;
        }

        public String getUnId() {
            return unId;
        }

        public void setUnId(String unId) {
            this.unId = unId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public float getPer() {
            return per;
        }

        public void setPer(float per) {
            this.per = per;
        }

}
