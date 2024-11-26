package com.manager.hotel.commons;

public class Constant {
    public static class Role {
        public static final String ADMIN = "ROLE_A";
        public static final String USER = "ROLE_U";
    }

    public static class DeleteFlg {
        public static final String DELETE = "1";
        public static final String NON_DELETE = "0";
    }

    public static class RoomType {
        public static final String DELUXE = "Deluxe";
        public static final String SUITE = "Suite";
        public static final String STANDARD = "Standard";
    }

    public static class OrderStatus {
        public static final String APPROVED = "Approved";
        public static final String REJECTED = "Rejected";
        public static final String PENDING = "Pending";
        public static final String EMPTY = "Empty";
    }
}
