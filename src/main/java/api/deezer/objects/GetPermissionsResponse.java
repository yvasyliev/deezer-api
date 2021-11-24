package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

public class GetPermissionsResponse {
    @SerializedName("permissions")
    private Permissions permissions;

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "GetPermissionsResponse{" +
                "permissions=" + permissions +
                '}';
    }

    public static class Permissions {
        @SerializedName("basic_access")
        private Boolean basicAccess;

        @SerializedName("email")
        private Boolean email;

        @SerializedName("offline_access")
        private Boolean offlineAccess;

        @SerializedName("manage_library")
        private Boolean manageLibrary;

        @SerializedName("manage_community")
        private Boolean manageCommunity;

        @SerializedName("delete_library")
        private Boolean deleteLibrary;

        @SerializedName("listening_history")
        private Boolean listeningHistory;

        public Boolean getBasicAccess() {
            return basicAccess;
        }

        public void setBasicAccess(Boolean basicAccess) {
            this.basicAccess = basicAccess;
        }

        public Boolean getEmail() {
            return email;
        }

        public void setEmail(Boolean email) {
            this.email = email;
        }

        public Boolean getOfflineAccess() {
            return offlineAccess;
        }

        public void setOfflineAccess(Boolean offlineAccess) {
            this.offlineAccess = offlineAccess;
        }

        public Boolean getManageLibrary() {
            return manageLibrary;
        }

        public void setManageLibrary(Boolean manageLibrary) {
            this.manageLibrary = manageLibrary;
        }

        public Boolean getManageCommunity() {
            return manageCommunity;
        }

        public void setManageCommunity(Boolean manageCommunity) {
            this.manageCommunity = manageCommunity;
        }

        public Boolean getDeleteLibrary() {
            return deleteLibrary;
        }

        public void setDeleteLibrary(Boolean deleteLibrary) {
            this.deleteLibrary = deleteLibrary;
        }

        public Boolean getListeningHistory() {
            return listeningHistory;
        }

        public void setListeningHistory(Boolean listeningHistory) {
            this.listeningHistory = listeningHistory;
        }

        @Override
        public String toString() {
            return "Permissions{" +
                    "basicAccess=" + basicAccess +
                    ", email=" + email +
                    ", offlineAccess=" + offlineAccess +
                    ", manageLibrary=" + manageLibrary +
                    ", manageCommunity=" + manageCommunity +
                    ", deleteLibrary=" + deleteLibrary +
                    ", listeningHistory=" + listeningHistory +
                    '}';
        }
    }
}
