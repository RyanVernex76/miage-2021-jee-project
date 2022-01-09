package fr.pantheonsorbonne.ufr27.miage.exception;

public class PassengerNotFoundException extends Exception {
    public static class AccessDenied extends Throwable {
        public String connexionIssue() {
            return " Probleme de login";
        }
    }
}
