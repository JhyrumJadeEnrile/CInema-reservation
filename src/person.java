public abstract class person {
    private String name;
    private String email;

    // Constructor
    public person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract method
    public abstract void personalInfo();
}
